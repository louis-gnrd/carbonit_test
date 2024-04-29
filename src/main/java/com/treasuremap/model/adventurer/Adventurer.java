package com.treasuremap.model.adventurer;

public class Adventurer {

    private int verticalPos;

    private int horizontalPos;

    /** Every adventurer needs a name  ! **/
    private final String name;

    /** The direction the adventurer is in **/
    private DirectionEnum direction;

    /** Sequence of movement for an adventurer **/
    private final MoveEnum[] moveSequence;

    /** Number of found treasures dor adventurer **/
    private int foundTreasure;

    public Adventurer(String name, int horizontalPosition, int verticalPosition, DirectionEnum directionEnum, MoveEnum[] moveSequence) {
        this.horizontalPos = horizontalPosition;
        this.verticalPos = verticalPosition;
        this.name = name;
        this.direction = directionEnum;
        this.moveSequence = moveSequence != null ? moveSequence : new MoveEnum[0];
        this.foundTreasure = 0;
    }

    public int getVerticalPos() {
        return verticalPos;
    }

    public void setVerticalPos(int verticalPos) {
        this.verticalPos = verticalPos;
    }

    public int getHorizontalPos() {
        return horizontalPos;
    }

    public void setHorizontalPos(int horizontalPos) {
        this.horizontalPos = horizontalPos;
    }

    public String getName() {
        return name;
    }

    public DirectionEnum getDirection() {
        return direction;
    }

    public void setDirection(DirectionEnum direction) {
        this.direction = direction;
    }

    public MoveEnum[] getMoveSequence() {
        return moveSequence;
    }

    public void increaseTreasureFound(){
        this.foundTreasure++;
    }

    public int getFoundTreasure() {
        return foundTreasure;
    }
}
