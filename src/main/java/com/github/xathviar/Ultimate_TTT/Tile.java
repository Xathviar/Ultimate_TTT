package com.github.xathviar.Ultimate_TTT;

import java.io.Serializable;

public enum Tile implements Serializable {
    X(":x:"),
    O(":o:"),
    ONE(":one:"),
    TWO(":two:"),
    THREE(":three:"),
    FOUR(":four:"),
    FIVE(":five:"),
    SIX(":six:"),
    SEVEN(":seven:"),
    EIGHT(":eight:"),
    NINE(":nine:"),
    WHITE(":gear:"),
    BLACK(":boom:");

    private final String glyph;

    /**
     * Constructor for the Tile
     * @param glyph Which glyph the tile should use
     */
    Tile(String glyph) {
        this.glyph = glyph;
    }

    /**
     * returns the glyph for the Tile
     * @return String
     */
    public String getGlyph() {
        return glyph;
    }

    /**
     * Determines the Tile can be changed or not
     * @return true - if it can be changed
     */
    public boolean isAvailable() {
        return this != Tile.X && this != Tile.O;
    }

    @Override
    public String toString() {
        return glyph;
    }

    public String toBlankString () {
        if (isAvailable()) {
            return Tile.WHITE.getGlyph();
        }else {
            return this.glyph;
        }
    }
}
