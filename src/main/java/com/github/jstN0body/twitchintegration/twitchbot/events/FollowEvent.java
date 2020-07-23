package com.github.jstN0body.twitchintegration.twitchbot.events;

import com.github.jstN0body.datamanager.column.StringColumn;
import com.github.jstN0body.datamanager.database.Database;
import com.github.jstN0body.twitchintegration.Main;
import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.pubsub.events.FollowingEvent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FollowEvent {

    private Database followers;

    public FollowEvent(SimpleEventHandler eventHandler, Main plugin) {
        if (plugin.getConfig().getString("followerlist").equals("C:/PATH-TO-SERVER/plugins/TwitchIntegration/followers.txt")) {
            System.out.println("please change the 'followerlist' path in the config.yml");
            return;
        }

        FileConfiguration config = plugin.getConfig();

        List<StringColumn> columns = new ArrayList<>();
        for (String id : config.getStringList("channelid")) {
            StringColumn column = new StringColumn(id, config.getString("followerlist"));
            column.loadData(new File(config.getString("followerlist") + id + ".column"));
            columns.add(column);
        }

        this.followers = new Database((StringColumn[]) columns.toArray());

        eventHandler.onEvent(FollowingEvent.class, this::onFollow);
    }

    public void onFollow(FollowingEvent event) {
        if (!EnableCommand.integrationEnabled) return;
        if (followers.getStringColumn(event.getChannelId()).contains(event.getData().getUserId())) return;

        followers.getStringColumn(event.getChannelId()).addData(event.getData().getUserId());
        Bukkit.broadcastMessage(ChatColor.BOLD + "" + ChatColor.GREEN + event.getData().getDisplayName() + ChatColor.RESET + " has followed!!!");
    }
}
