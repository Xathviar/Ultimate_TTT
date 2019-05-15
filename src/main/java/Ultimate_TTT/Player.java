package Ultimate_TTT;

import net.dv8tion.jda.core.entities.User;

public class Player {
    Tile tile;
    User user;

    public Player(Tile tile, User user) {
        this.tile = tile;
        this.user = user;
    }

    Tile getTile() {
        return tile;
    }

    public User getUser() {
        return user;
    }
}
