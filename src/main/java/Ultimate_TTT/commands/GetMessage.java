package Ultimate_TTT.commands;

import Ultimate_TTT.PlayField;
import Ultimate_TTT.Player;
import Ultimate_TTT.Tile;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class GetMessage extends ListenerAdapter {
    PlayField field;
    char prefix;

    public GetMessage(PlayField field, char prefix) {
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

        if (isBot && msg.startsWith(":") && field.getMessage() == null) {
            field.setMessage(message);
        } else if (isBot && msg.startsWith("`Current turn: ") && field.getTurn() == null) {
            field.setTurn(message);
        }
    }

}
