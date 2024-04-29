package com.treasuremap.model.map;

public class GameMapElement {

    /** Horizontal position of the obstacle **/
    private final int horizontalPos;

    /** Vertical position of the obstacle **/
    private final int verticalPos;

    public GameMapElement(int horizontalPos, int verticalPos) {
        this.horizontalPos = horizontalPos;
        this.verticalPos = verticalPos;
    }

    public int getHorizontalPos() {
        return horizontalPos;
    }

    public int getVerticalPos() {
        return verticalPos;
    }
}
