package com.treasuremap.model.map;

public class Mountain extends GameMapElement {

    public Mountain(int horizontalPos, int verticalPos) {
        super(horizontalPos, verticalPos);
    }

    @Override
    public String toString(){
        return "M";
    }
}
