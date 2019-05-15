package Ultimate_TTT;

import java.util.Arrays;

public class TicTacToe {

    public Tile[] field = new Tile[9];
    public static Tile[] tiles = new Tile[13];

    public Player winner;

    private boolean active = false;

    /**
     * Constructor for the 3x3 TTT Field
     */
    public TicTacToe() {
        fillTiles();
        fillField();
    }

    /**
     * Adds all Tiles to an Array
     */
    private void fillTiles() {
        tiles[0] = Tile.X;
        tiles[1] = Tile.O;
        tiles[2] = Tile.ONE;
        tiles[3] = Tile.TWO;
        tiles[4] = Tile.THREE;
        tiles[5] = Tile.FOUR;
        tiles[6] = Tile.FIVE;
        tiles[7] = Tile.SIX;
        tiles[8] = Tile.SEVEN;
        tiles[9] = Tile.EIGHT;
        tiles[10] = Tile.NINE;
        tiles[11] = Tile.WHITE;
        tiles[12] = Tile.BLACK;
    }

    /**
     * Fills the Field with Values
     */
    private void fillField() {
        for (int i = 0; i < field.length; i++) {
            field[i] = tiles[i + 2];
        }
    }

    /**
     * Gets the whole 3x3 Field
     *
     * @return Tile[] containing the whole Field
     */
    public Tile[] getField() {
        return field;
    }

    /**
     * Gets one Tile of the 3x3 Field
     *
     * @param x the index of the 3x3 field
     * @return Tile
     */
    public Tile getField(int x) {
        return field[x];
    }

    /**
     * Set the Tile on the specific index to X or O depending on the player
     *
     * @param index  the index of the field
     * @param player which player set the tile
     * @return the index of the tile
     */
    public int setTile(int index, Player player) {
        if (getField(index - 1).isAvailable()) {
            field[index - 1] = player.getTile();
            checkVictory(player);
            return index;
        }
        return -1;
    }

    private void checkVictory(Player player) {
        for (int i = 0; i < 3; i++) {
            if ((field[i * 3] == player.getTile() && field[i * 3 + 1] == player.getTile() && field[i * 3 + 2] == player.getTile()) ||
                    (field[i] == player.getTile() && field[i + 3] == player.getTile() && field[i + 6] == player.getTile()) ||
                    (field[0] == player.getTile() && field[4] == player.getTile() && field[8] == player.getTile()) ||
                    (field[2] == player.getTile() && field[4] == player.getTile() && field[6] == player.getTile())) {
                winner = player;
            }
        }
    }

    /**
     * Gets one row of the Field
     *
     * @param index the index of the row, can be either 0,1 or 2
     * @return Tile Array containing the Row
     */
    public Tile[] getRow(int index) {
        return new Tile[]{field[index * 3], field[index * 3 + 1], field[index * 3 + 2]};
    }

    public String getRowToString(int index, boolean containsActive) {
        if (containsActive) {
            if (isActive()) {
                return field[index * 3].toString() + field[index * 3 + 1].toString() + field[index * 3 + 2].toString();
            } else {
                return field[index * 3].toBlankString() + field[index * 3 + 1].toBlankString() + field[index * 3 + 2].toBlankString();
            }
        }
        return field[index * 3].toString() + field[index * 3 + 1].toString() + field[index * 3 + 2].toString();
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void blur() {
        for (int i = 0; i < field.length; i++) {
            if (field[i] != Tile.O && field[i] != Tile.X) {
                field[i] = Tile.WHITE;
            }
        }
    }
}
