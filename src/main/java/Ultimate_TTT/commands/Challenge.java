package Ultimate_TTT.commands;

import Ultimate_TTT.PlayField;
import Ultimate_TTT.Player;
import Ultimate_TTT.Tile;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Challenge extends ListenerAdapter {
    PlayField field;
    char prefix;

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
            if (message.getMentionedUsers().size() != 0)  {
                if (field.isActiveGame()) {
                    channel.sendMessage("There is already a running game!").queue();
                } else {
                    field.setPlayer1(new Player(Tile.X, author));
                    field.setPlayer2(new Player(Tile.O, message.getMentionedUsers().get(0)));
                    field.setActiveGame(true);
                    field.setActivePlayer(field.getPlayer1());
                    channel.sendMessage("Successfully started the Game.").queue();
                    try {
                        field.printField(channel);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.sendMessage("With \'.[1-9]\' you can select the Tic-Tac-Toe you want to play in. If you " +
                            "selected it you can then select \'.[1-9]\' to place it there.").queue();
                }
            }else {
                channel.sendMessage("Please mention someone!").queue();
            }
        }
    }

}
