package Ultimate_TTT.commands;

import Ultimate_TTT.PlayField;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

/**
 * Sets The Message and the Turn
 */
public class GetMessage extends ListenerAdapter {
    private final PlayField field;

    /**
     * Constructor for GetMessage
     * @param field the PlayField which will be used
     */
    public GetMessage(PlayField field) {
        this.field = field;
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        User author = event.getAuthor();                //The user that sent the message
        Message message = event.getMessage();           //The message that was received.
        String msg = message.getContentDisplay();       //get msg as String
        boolean isBot = author.isBot(); //Determines whether user is a bot or not

        if (isBot && msg.startsWith(":") && field.getMessage() == null) {
            field.setMessage(message);
        } else if (isBot && msg.startsWith("`Current turn: ") && field.getTurn() == null) {
            field.setTurn(message);
        }
    }

}
