package Ultimate_TTT;

import net.dv8tion.jda.core.entities.User;

/**
 * Stores the Player
 */
public class Player {
    private final Tile tile;
    private final User user;

    /**
     * Constructor for the Player
     * @param tile the tile which the Player will use
     * @param user the Discord-User
     */
    public Player(Tile tile, User user) {
        this.tile = tile;
        this.user = user;
    }

    /**
     * getter for Tile
     * @return Tile of the Player
     */
    Tile getTile() {
        return tile;
    }

    /**
     * getter for User
     * @return User of the Player
     */
    public User getUser() {
        return user;
    }
}
