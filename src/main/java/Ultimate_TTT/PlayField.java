package Ultimate_TTT;


import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlayField {
    List<TicTacToe> field = new ArrayList<>();
    Player player1;
    Player player2;
    Player active;
    boolean activeGame = false;
    Message message;
    Message turn;
    MessageChannel channel;


    public PlayField() {
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
        System.out.println(s.length());
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
        turn.delete().queue();
        turn = null;
        channel.sendMessage("`Current turn: `" + active.getUser().getAsMention()).queue();
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
}
