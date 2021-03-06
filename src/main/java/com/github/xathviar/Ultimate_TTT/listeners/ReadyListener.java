package com.github.xathviar.Ultimate_TTT.listeners;

import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.EventListener;

/**
 * Prints "Bot is ready!" if the bot is connected to discord
 */
public class ReadyListener implements EventListener {

    public void onEvent(Event event) {
        if (event instanceof ReadyEvent) {
            System.out.println("Bot is ready!");
        }
    }
}
