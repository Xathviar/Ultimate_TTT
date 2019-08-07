package Ultimate_TTT;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles the TTT Field
 */
public class PlayField {
    private List<TicTacToe> field = new ArrayList<>();
    private Player player1;
    private Player player2;
    private Player active;
    private boolean activeGame = false;
    private Message message;
    private Message turn;
    private MessageChannel channel;

    /**
     * Constructor for the PlayField
     */
    PlayField() {
        fillField();
        active = null;
    }

    private void fillField() {
        for (int i = 0; i < 9; i++) {
            field.add(new TicTacToe());
        }
    }

    public void printField() {
        String s = printRow(0) + printRow(1) + printRow(2);
        if (message == null) {
            channel.sendMessage(s).queue();
        } else {
            message.editMessage(s).queue();
        }
    }

    private String printRow(int index) {
        StringBuilder b = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            b
                    .append(field.get(index * 3).getRowToString(i, containsActive()))
                    .append(Tile.BLACK.toString())
                    .append(field.get(index * 3 + 1).getRowToString(i, containsActive()))
                    .append(Tile.BLACK.toString())
                    .append(field.get(index * 3 + 2).getRowToString(i, containsActive())).append("\n");
        }
        if (index != 2) {
            b.append(StringUtils.repeat(Tile.BLACK.toString(), 11)).append("\n");
        }
        return b.toString();
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setActiveGame(boolean activeGame) {
        this.activeGame = activeGame;
    }

    public boolean isActiveGame() {
        return activeGame;
    }

    public boolean containsActive() {
        return field.stream().anyMatch(TicTacToe::isActive);
    }

    public TicTacToe getActive() {
        return field.stream().filter(TicTacToe::isActive).findFirst().orElse(null);
    }

    public Player getActivePlayer() {
        return active;
    }

    public void setActivePlayer(Player player) {
        this.active = player;
    }

    public void setActivePlayer() {
        if (active.equals(player1)) {
            active = player2;
        } else {
            active = player1;
        }
        turn.delete().queue();
        turn = null;
        channel.sendMessage("`Current turn: `" + active.getUser().getAsMention()).queue();
    }

    public Player getPlayer1() {
        return player1;
    }


    public void setActiveTTT(int newActiveTTT) {
        field.forEach(n -> n.setActive(false));
        if (field.get(newActiveTTT - 1).winner != null) {
            return;
        }
        field.get(newActiveTTT - 1).setActive(true);
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Message getMessage() {
        return message;
    }

    public MessageChannel getChannel() {
        return channel;
    }

    public Message getTurn() {
        return turn;
    }

    public void setTurn(Message turn) {
        this.turn = turn;
    }

    public void setChannel(MessageChannel channel) {
        this.channel = channel;
    }

    public void checkWinner(MessageChannel channel, Player player) {
        for (int i = 0; i < 3; i++) {
            if ((field.get(i * 3).winner != null && field.get(i * 3 + 1).winner != null && field.get(i * 3 + 2).winner != null
                    && field.get(i * 3).winner.getTile() == player.getTile()
                    && field.get(i * 3 + 1).winner.getTile() == player.getTile()
                    && field.get(i * 3 + 2).winner.getTile() == player.getTile()) ||
                    ( field.get(i).winner != null && field.get(i + 3).winner != null && field.get(i + 6).winner.getTile() != null &&
                            field.get(i).winner.getTile() == player.getTile()
                            && field.get(i + 3).winner.getTile() == player.getTile()
                            && field.get(i + 6).winner.getTile() == player.getTile()) ||
                    (field.get(0).winner != null && field.get(4).winner != null && field.get(8).winner != null &&
                               field.get(0).winner.getTile() == player.getTile()
                            && field.get(4).winner.getTile() == player.getTile()
                            && field.get(8).winner.getTile() == player.getTile()) ||
                    (field.get(2).winner != null && field.get(4).winner != null && field.get(6).winner != null &&
                            field.get(2).winner.getTile() == player.getTile()
                            && field.get(4).winner.getTile() == player.getTile()
                            && field.get(6).winner.getTile() == player.getTile())) {
                channel.sendMessage(player.getUser().getAsMention() + " has won the game!").queue();
                setActiveGame(false);
            }
        }
    }
}
