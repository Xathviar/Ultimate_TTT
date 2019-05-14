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
                    channel.sendMessage("Successfully started the Game.").queue();
                    field.printField(channel);
                }
            }else {
                channel.sendMessage("Please mention someone!").queue();
            }
        }
    }

}
