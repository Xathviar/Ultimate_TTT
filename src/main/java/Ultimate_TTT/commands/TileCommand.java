package Ultimate_TTT.commands;

import Ultimate_TTT.PlayField;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.regex.Pattern;

public class TileCommand extends ListenerAdapter {
    PlayField field;
    char prefix;

    public TileCommand(PlayField field, char prefix) {
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

        if (!isBot && (Pattern.compile(prefix + "[1-9]").matcher(msg).matches())) {
            if (field.containsActive()) {

            }
        }
    }
}
