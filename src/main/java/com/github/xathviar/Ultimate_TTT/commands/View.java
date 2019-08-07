package com.github.xathviar.Ultimate_TTT.commands;

import com.github.xathviar.Ultimate_TTT.PlayField;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Prints the Field if the Player wants to
 */
public class View extends ListenerAdapter {
    private final PlayField field;
    private final char prefix;

    /**
     * Constructor for View
     * @param field the PlayField which will be used
     * @param prefix the prefix which will be listened to
     */
    public View(PlayField field, char prefix) {
        this.field = field;
        this.prefix = prefix;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        String msg = message.getContentDisplay();       //get msg as String
        boolean isBot = author.isBot(); //Determines whether user is a bot or not

        if (!isBot && msg.equalsIgnoreCase(prefix + "sout")) {
                field.printField();
            /*
            if (event.isFromType(ChannelType.PRIVATE)) {
                channel.sendMessage("[PM] " + event.getAuthor().getName() + ": " + msg).queue();
                System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                        event.getMessage().getContentDisplay());
            } else {
                System.out.printf("[%s][%s] %s: %s\n", event.getGuild().getName(),
                        event.getTextChannel().getName(), event.getMember().getEffectiveName(),
                        event.getMessage().getContentDisplay());
            }
             */
        }
    }
}
