package com.treasuremap.model.adventurer;

public enum DirectionEnum {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    private static final DirectionEnum[] vals = values();

    public DirectionEnum next() {
        return vals[(this.ordinal() + 1) % vals.length];
    }

    public DirectionEnum previous() {
        return vals[(this.ordinal() + 3) % vals.length];
    }
}
