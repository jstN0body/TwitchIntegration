package com.github.jstN0body.twitchintegration;

import com.github.jstN0body.twitchintegration.commands.EnableCommand;
import com.github.jstN0body.twitchintegration.twitchbot.Bot;
import com.github.jstN0body.twitchintegration.twitchbot.events.HoldChickenNugget;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfig();


        new EnableCommand(this);
        new HoldChickenNugget(this);
        new Bot(this);
    }
}