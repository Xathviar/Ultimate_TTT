package Ultimate_TTT.commands;

import Ultimate_TTT.PlayField;
import Ultimate_TTT.TicTacToe;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.util.regex.Matcher;
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
                if (field.getActivePlayer().getUser().equals(author)) {
                    Matcher matcher = Pattern.compile(prefix + "([1-9])").matcher(msg);
                    TicTacToe ticTacToe = field.getActive();
                    while(matcher.find()) {
                        int index = Integer.parseInt(matcher.group(1));
                        System.out.println(index);
                        int newActiveTTT = ticTacToe.setTile(index, field.getActivePlayer());
                        if (newActiveTTT < 0) {
                            channel.sendMessage(field.getActivePlayer().getUser().getAsMention() + " please select a field that hasn't been set!").queue();
                            return;
                        }
                        field.setActivePlayer();
                        field.setActiveTTT(newActiveTTT);
                        channel.sendMessage("It is your turn " + field.getActivePlayer().getUser().getAsMention()).queue();
                    }
                    try {
                        field.printField(channel);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    channel.sendMessage("It is not your turn!").queue();
                }
            } else {
                if (field.getActivePlayer().getUser().equals(author)) {
                    Matcher matcher = Pattern.compile(prefix + "([1-9])").matcher(msg);
                    while(matcher.find()) {
                        int index = Integer.parseInt(matcher.group(1));
                        field.setActiveTTT(index);
                    }
                    try {
                        field.printField(channel);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    channel.sendMessage("It is not your turn!").queue();
                }
            }
        }
    }
}
