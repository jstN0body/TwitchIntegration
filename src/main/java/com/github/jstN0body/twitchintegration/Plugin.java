package com.github.jstN0body.twitchintegration;

import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.jstN0body.twitchintegration.twitchbot.Bot;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


public class Plugin extends JavaPlugin {

    public static FileConfiguration config;

    @Override
    public void onEnable() {
        this.saveDefaultConfig();


        new EnableCommand();
        new Bot(this);

        config = this.getConfig();
    }
}