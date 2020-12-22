package com.github.jstN0body.twitchintegration.commands;

import com.github.jstN0body.twitchintegration.Main;
import com.github.jstN0body.twitchintegration.twitchbot.Actions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class NetherCommand implements CommandExecutor {

    private final Main plugin;

    public NetherCommand(Main plugin) {
        this.plugin = plugin;

        Bukkit.getPluginCommand("nether").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Actions actions = new Actions(plugin);
        actions.switchDimensions(plugin.getConfig());
        return true;
    }
}
