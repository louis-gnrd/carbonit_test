package com.treasuremap.model.map;

public class GameMap {

    /** Horizontal number of cells */
    private final int length;

    /** Vertical number of cells */
    private final int width;

    /**
     * Grid indexing all fixed element of a map
     *
     * Due to the way the coordinates indexes the map, this grid is a list of columns of the expected map
     *
     */
    private final GameMapElement[][] grid;

    public GameMap(int length, int width, GameMapElement[][] grid) {
        this.length = length;
        this.width = width;
        this.grid = grid;
    }

    public int getLength() {
        return length;
    }

    public int getWidth() {
        return width;
    }

    public GameMapElement[][] getGrid() {
        return grid;
    }
}
