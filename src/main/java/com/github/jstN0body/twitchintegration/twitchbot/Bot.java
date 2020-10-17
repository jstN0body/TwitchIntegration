package com.github.jstN0body.twitchintegration.twitchbot;

import com.github.jstN0body.twitchintegration.Main;
import com.github.jstN0body.twitchintegration.twitchbot.events.*;
import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.philippheuer.events4j.simple.SimpleEventHandler;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import com.github.twitch4j.chat.TwitchChat;
import com.github.twitch4j.pubsub.TwitchPubSub;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.ArrayList;
import java.util.List;

public class Bot {

    public Bot(Main plugin) {
        FileConfiguration config = plugin.getConfig();

        List<OAuth2Credential> credentials = new ArrayList<>();
        for (String credential : config.getStringList("oauth")) {
            OAuth2Credential oauth = new OAuth2Credential("twitch", credential);
            credentials.add(oauth);
        }

        TwitchClient twitchClient = TwitchClientBuilder.builder()
                .withEnablePubSub(true)
                .withEnableChat(true)
                .withChatAccount(credentials.get(0))
                .build();

        TwitchChat twitchChat = twitchClient.getChat();
        List<String> channels = config.getStringList("channel");
        for (String channel : channels) {
            twitchChat.joinChannel(channel);
        }

        List<String> ids = config.getStringList("channelid");
        TwitchPubSub twitchPubSub = twitchClient.getPubSub();
        for (int i = 0 ; i < credentials.size() ; i++) {
                twitchPubSub.listenForCheerEvents(credentials.get(i), ids.get(i));
                twitchPubSub.listenForSubscriptionEvents(credentials.get(i), ids.get(i));
                twitchPubSub.listenForChannelPointsRedemptionEvents(credentials.get(i), ids.get(i));
        }

        SimpleEventHandler eventHandler = twitchChat.getEventManager().getEventHandler(SimpleEventHandler.class);
        SimpleEventHandler pubSubHandler = twitchClient.getEventManager().getEventHandler(SimpleEventHandler.class);

        new TwitchRaidEvent(eventHandler);
        new CheerEvent(pubSubHandler, plugin);
        new SubscribeEvent(pubSubHandler);
        new ChannelPointRedeem(pubSubHandler, plugin);
    }
}