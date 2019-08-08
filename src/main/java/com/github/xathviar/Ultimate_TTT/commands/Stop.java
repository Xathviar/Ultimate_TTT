package com.github.xathviar.Ultimate_TTT.commands;

import com.github.xathviar.Ultimate_TTT.PlayField;
import com.github.xathviar.Ultimate_TTT.Player;
import com.github.xathviar.Ultimate_TTT.Tile;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Stop extends ListenerAdapter {
    private final PlayField field;
    private final char prefix;

    /**
     * Constructor for Challenge
     *
     * @param field  the PlayField which will be used
     * @param prefix the prefix which will be listened to
     */
    public Stop(PlayField field, char prefix) {
        this.field = field;
        this.prefix = prefix;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();    //This is the MessageChannel that the message was sent to.
        String msg = message.getContentDisplay();       //get msg as String
        boolean isBot = author.isBot(); //Determines whether user is a bot or not

        if (!isBot && msg.startsWith(prefix + "stop")) {
            if (field.isActiveGame()) {
                if (field.getPlayer1().getUser().equals(author) || field.getPlayer2().getUser().equals(author)) {
                    field.setActiveGame(false);
                    field.setMessage(null);
                    field.setTurn(null);
                    channel.sendMessage("Successfully stopped the game").queue();
                }
            } else {
                channel.sendMessage("There is no Running Game").queue();
            }
        }
    }
}
