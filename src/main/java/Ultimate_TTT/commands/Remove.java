package Ultimate_TTT.commands;

import Ultimate_TTT.PlayField;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Deletes every message that wasn't send by a bot
 */
public class Remove extends ListenerAdapter {
    private final PlayField field;

    /**
     * Constructor for Remove
     * @param field the PlayField which will be used
     */
    public Remove(PlayField field) {
        this.field = field;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        MessageChannel channel = event.getChannel();    //This is the MessageChannel that the message was sent to.
        boolean isBot = author.isBot(); //Determines whether user is a bot or not

        if (!isBot && field.isActiveGame() && channel.equals(field.getChannel())) {
            message.delete().queue();
        }
    }
}
