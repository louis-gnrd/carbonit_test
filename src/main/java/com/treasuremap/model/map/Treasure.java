package com.treasuremap.model.map;

public class Treasure extends GameMapElement {

    /** Number of treasure contained by the slot **/
    private int numberOfTreasure;


    public Treasure(int horizontalPos, int verticalPos, int nbTreasure) {
        super(horizontalPos, verticalPos);
        this.numberOfTreasure = nbTreasure;
    }

    public int getNumberOfTreasure() {
        return numberOfTreasure;
    }

    public void setNumberOfTreasure(int numberOfTreasure) {
        this.numberOfTreasure = numberOfTreasure;
    }

    public void decreaseNumberOfTreasure(){
        this.numberOfTreasure--;
    }

    @Override
    public String toString(){
        return "T(" + this.numberOfTreasure + ")";
    }
}
