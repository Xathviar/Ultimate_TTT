package Ultimate_TTT;


import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageActivity;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PlayField {
    List<TicTacToe> field = new ArrayList<TicTacToe>();
    Player player1;
    Player player2;
    boolean activeGame = false;


    public PlayField() {
        fillField();
    }

    private void fillField() {
        for (int i = 0; i < 9; i++) {
            field.add(new TicTacToe());
        }
    }

    public void printField(MessageChannel channel) {
        printRow(channel, 0);
        printRow(channel, 1);
        printRow(channel, 2);
    }

    private void printRow(MessageChannel channel, int index) {
        if (index == 2) {
            for (int i = 0; i < 3; i++) {
                channel.sendMessage(
                        field.get(index * 3).getRowToString(i) +
                                Tile.BLACK.toString() +
                                field.get(index * 3 + 1).getRowToString(i) +
                                Tile.BLACK.toString() +
                                field.get(index * 3 + 2).getRowToString(i))
                        .queue();
            }
        } else {
            for (int i = 0; i < 3; i++) {
                channel.sendMessage(
                        field.get(index * 3).getRowToString(i) +
                                Tile.BLACK.toString() +
                                field.get(index * 3 + 1).getRowToString(i) +
                                Tile.BLACK.toString() +
                                field.get(index * 3 + 2).getRowToString(i))
                        .queue();
            }
            channel.sendMessage(StringUtils.repeat(Tile.BLACK.toString(), 11)).queue();
        }
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
}
