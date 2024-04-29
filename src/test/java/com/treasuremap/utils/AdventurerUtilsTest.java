package com.treasuremap.utils;

import com.treasuremap.model.adventurer.Adventurer;
import com.treasuremap.model.adventurer.DirectionEnum;
import com.treasuremap.model.adventurer.MoveEnum;
import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class AdventurerUtilsTest {

    AdventurerUtils adventurerUtils = new AdventurerUtils();

    @Test
    public void testCalculateNewDirectionRightFromNorth(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.NORTH, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.RIGHT);

        //Then
        assertEquals(DirectionEnum.EAST, result);

    }

    @Test
    public void testCalculateNewDirectionRightFromEast(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.EAST, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.RIGHT);

        //Then
        assertEquals(DirectionEnum.SOUTH, result);

    }

    @Test
    public void testCalculateNewDirectionRightFromSouth(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.SOUTH, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.RIGHT);

        //Then
        assertEquals(DirectionEnum.WEST, result);

    }

    @Test
    public void testCalculateNewDirectionRightFromWest(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.WEST, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.RIGHT);

        //Then
        assertEquals(DirectionEnum.NORTH, result);

    }

    @Test
    public void testCalculateNewDirectionLeftFromNorth(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.NORTH, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.LEFT);

        //Then
        assertEquals(DirectionEnum.WEST, result);

    }

    @Test
    public void testCalculateNewDirectionLeftFromEast(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.EAST, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.LEFT);

        //Then
        assertEquals(DirectionEnum.NORTH, result);

    }

    @Test
    public void testCalculateNewDirectionLeftFromSouth(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.SOUTH, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.LEFT);

        //Then
        assertEquals(DirectionEnum.EAST, result);

    }

    @Test
    public void testCalculateNewDirectionLeftFromWest(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.WEST, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.LEFT);

        //Then
        assertEquals(DirectionEnum.SOUTH, result);

    }

    @Test
    public void testCalculateNewDirectionOtherDirection(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.SOUTH, null);

        //When
        DirectionEnum result = adventurerUtils.calculateNewDirection(adventurer, MoveEnum.FORWARD);

        //Then
        assertEquals(DirectionEnum.SOUTH, result);

    }

    @Test
    public void calculateForwardNewCoordinatesWhenFacingNorth(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 0, 0, DirectionEnum.NORTH, null);

        //When
        Point result = adventurerUtils.calculateForwardNewCoordinates(adventurer);

        //Then
        assertEquals(0, result.x);
        assertEquals(-1, result.y);
    }

    @Test
    public void calculateForwardNewCoordinatesWhenFacingEast(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 1, 2, DirectionEnum.EAST, null);

        //When
        Point result = adventurerUtils.calculateForwardNewCoordinates(adventurer);

        //Then
        assertEquals(2, result.x);
        assertEquals(2, result.y);
    }

    @Test
    public void calculateForwardNewCoordinatesWhenFacingSouth(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 3, 5, DirectionEnum.SOUTH, null);

        //When
        Point result = adventurerUtils.calculateForwardNewCoordinates(adventurer);

        //Then
        assertEquals(3, result.x);
        assertEquals(6, result.y);
    }

    @Test
    public void calculateForwardNewCoordinatesWhenFacingWest(){
        //Given
        Adventurer adventurer = new Adventurer("Name", 2, 2, DirectionEnum.WEST, null);

        //When
        Point result = adventurerUtils.calculateForwardNewCoordinates(adventurer);

        //Then
        assertEquals(1, result.x);
        assertEquals(2, result.y);
    }



}
