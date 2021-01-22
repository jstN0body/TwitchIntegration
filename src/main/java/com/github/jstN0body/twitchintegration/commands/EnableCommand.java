package com.github.jstN0body.twitchintegration.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class EnableCommand implements CommandExecutor {

    public static boolean integrationEnabled = false;

    public EnableCommand() {
        Bukkit.getPluginCommand("twitchmc").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String alias, String[] args) {
        if (args[0].equalsIgnoreCase("enable")) {
            integrationEnabled = true;
            Bukkit.broadcastMessage("Twitch Integration Enabled.");
        } else if (args[0].equalsIgnoreCase("disable")) {
            integrationEnabled = false;
            Bukkit.broadcastMessage("Twitch Integration Disabled.");
        } else {
            sender.sendMessage("Please insert a valid argument after the command.");
        }
        return true;
    }
}
