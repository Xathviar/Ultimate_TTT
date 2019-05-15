package Ultimate_TTT;


import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.MessageActivity;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.apache.commons.lang3.StringUtils;

import javax.security.auth.callback.TextInputCallback;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayField {
    List<TicTacToe> field = new ArrayList<>();
    Player player1;
    Player player2;
    Player active;
    boolean activeGame = false;


    public PlayField() {
        fillField();
        active = null;
    }

    private void fillField() {
        for (int i = 0; i < 9; i++) {
            field.add(new TicTacToe());
        }
    }

    public void printField(MessageChannel channel) throws InterruptedException {
        printRow(channel, 0);
        printRow(channel, 1);
        printRow(channel, 2);
    }

    private void printRow(MessageChannel channel, int index) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            channel.sendMessage(
                    field.get(index * 3).getRowToString(i, containsActive()) +
                            Tile.BLACK.toString() +
                            field.get(index * 3 + 1).getRowToString(i, containsActive()) +
                            Tile.BLACK.toString() +
                            field.get(index * 3 + 2).getRowToString(i, containsActive()))
                    .queue();
            Thread.sleep(50);
        }
        if (index != 2) {
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

    public TicTacToe getActive() {
        return field.stream().filter(TicTacToe::isActive).findFirst().get();
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
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setActiveTTT(int newActiveTTT) {
        field.forEach(n -> n.setActive(false));
        if (field.get(newActiveTTT - 1).winner != null) {
            return;
        }
        field.get(newActiveTTT - 1).setActive(true);
        System.out.println(field.get(newActiveTTT - 1).isActive());
    }
}
