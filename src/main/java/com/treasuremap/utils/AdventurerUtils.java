package com.treasuremap.utils;

import com.treasuremap.model.adventurer.Adventurer;
import com.treasuremap.model.adventurer.DirectionEnum;
import com.treasuremap.model.adventurer.MoveEnum;

import java.awt.*;

public class AdventurerUtils {

    public DirectionEnum calculateNewDirection(Adventurer adventurer, MoveEnum moveEnum){
        DirectionEnum oldDirection = adventurer.getDirection();
        switch (moveEnum){
            case RIGHT -> {
                return oldDirection.next();
            }
            case LEFT -> {
                return oldDirection.previous();
            }
            default -> {
                return oldDirection;
            }
        }
    }

    public Point calculateForwardNewCoordinates(Adventurer adventurer){
        int horizontalPosition = adventurer.getHorizontalPos();
        int verticalPosition =  adventurer.getVerticalPos();
        DirectionEnum direction = adventurer.getDirection();

        switch (direction){
            case NORTH -> verticalPosition--;
            case SOUTH -> verticalPosition++;
            case WEST -> horizontalPosition--;
            case EAST -> horizontalPosition++;
            default -> {}
        }

        return new Point(horizontalPosition, verticalPosition);
    }

}
