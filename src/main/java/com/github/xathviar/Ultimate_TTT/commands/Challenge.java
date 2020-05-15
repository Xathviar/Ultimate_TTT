package com.github.xathviar.Ultimate_TTT.commands;

import com.github.xathviar.Ultimate_TTT.PlayField;
import com.github.xathviar.Ultimate_TTT.Player;
import com.github.xathviar.Ultimate_TTT.Tile;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Handles the .ttt <@Player> Command, which challenges a player
 */
public class Challenge extends ListenerAdapter {
    private final PlayField field;
    private final char prefix;

    /**
     * Constructor for Challenge
     *
     * @param field  the PlayField which will be used
     * @param prefix the prefix which will be listened to
     */
    public Challenge(PlayField field, char prefix) {
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

        if (!isBot && msg.startsWith(prefix + "ttt")) {
            if (message.getMentionedUsers().size() != 0) {
                if (field.isActiveGame()) {
                    channel.sendMessage("There is already a running game!").queue();
                } else {
                    field.setChannel(channel);
                    field.setPlayer1(new Player(Tile.X, author));
                    field.setPlayer2(new Player(Tile.O, message.getMentionedUsers().get(0)));
                    field.setActiveGame(true);
                    field.setActivePlayer(field.getPlayer1());
                    channel.sendMessage("Successfully started the Game.").queue();
                    field.printField();
                    channel.sendMessage("With '" + prefix + "[1-9]' you can select the Tic-Tac-Toe you want to play in. If you " +
                            "selected it you can then select '" + prefix + "[1-9]' to place it there.").queue();
                    channel.sendMessage("`Current turn: `" + field.getActivePlayer().getUser().getAsMention()).queue();
                }
            } else {
                channel.sendMessage("Please mention someone!").queue();
            }
        }
    }

}
