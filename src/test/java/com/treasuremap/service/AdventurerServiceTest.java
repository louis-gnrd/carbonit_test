package com.treasuremap.service;
import com.treasuremap.exception.MapIntegrityException;
import com.treasuremap.model.adventurer.Adventurer;
import com.treasuremap.model.adventurer.DirectionEnum;
import com.treasuremap.model.adventurer.MoveEnum;
import com.treasuremap.model.map.GameMap;
import com.treasuremap.model.map.GameMapElement;
import com.treasuremap.model.map.Mountain;
import com.treasuremap.model.map.Treasure;
import com.treasuremap.utils.AdventurerUtils;
import org.junit.Test;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class AdventurerServiceTest {

    AdventurerService adventurerService = new AdventurerService(new AdventurerUtils());

    @Test
    public void moveAdventurersOnMapRightNominalCase() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        GameMap gameMap = new GameMap(2,3, mapGrid);

        MoveEnum[] sequence = {MoveEnum.RIGHT};
        Adventurer adventurer = new Adventurer("Test", 1,1, DirectionEnum.NORTH, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(1, adventurer.getHorizontalPos());
        assertEquals(1, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.EAST, adventurer.getDirection());

    }

    @Test
    public void moveAdventurersOnMapLeftNominalCase() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        GameMap gameMap = new GameMap(2,3, mapGrid);

        MoveEnum[] sequence = {MoveEnum.LEFT};
        Adventurer adventurer = new Adventurer("Test", 1,1, DirectionEnum.WEST, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(1, adventurer.getHorizontalPos());
        assertEquals(1, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.SOUTH, adventurer.getDirection());

    }

    @Test
    public void moveAdventurersOnMapForwardNominalCase() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        GameMap gameMap = new GameMap(2,3, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 1,1, DirectionEnum.WEST, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(0, adventurer.getHorizontalPos());
        assertEquals(1, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.WEST, adventurer.getDirection());

    }

    @Test
    public void moveAdventurersOnMapForwardOnUpEdge() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[1][1];
        GameMap gameMap = new GameMap(1,1, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 0,0, DirectionEnum.NORTH, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(0, adventurer.getHorizontalPos());
        assertEquals(0, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.NORTH, adventurer.getDirection());
    }

    @Test
    public void moveAdventurersOnMapForwardOnDownEdge() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[1][1];
        GameMap gameMap = new GameMap(1,1, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 0,0, DirectionEnum.SOUTH, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(0, adventurer.getHorizontalPos());
        assertEquals(0, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.SOUTH, adventurer.getDirection());
    }

    @Test
    public void moveAdventurersOnMapForwardOnLeftEdge() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[1][1];
        GameMap gameMap = new GameMap(1,1, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 0,0, DirectionEnum.WEST, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(0, adventurer.getHorizontalPos());
        assertEquals(0, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.WEST, adventurer.getDirection());
    }

    @Test
    public void moveAdventurersOnMapForwardOnRightEdge() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[1][1];
        GameMap gameMap = new GameMap(1,1, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 0,0, DirectionEnum.EAST, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(0, adventurer.getHorizontalPos());
        assertEquals(0, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.EAST, adventurer.getDirection());
    }

    @Test
    public void moveAdventurersOnMapForwardOnMountain() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        mapGrid[1][0] = new Mountain(1,0);
        GameMap gameMap = new GameMap(2,3, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 1,1, DirectionEnum.NORTH, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(1, adventurer.getHorizontalPos());
        assertEquals(1, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.NORTH, adventurer.getDirection());
    }

    @Test
    public void moveAdventurersOnMapForwardOnTreasureNominalCase() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        mapGrid[1][2] = new Treasure(1,2, 2);
        GameMap gameMap = new GameMap(2,3, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 1,1, DirectionEnum.SOUTH, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(1, adventurer.getHorizontalPos());
        assertEquals(2, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.SOUTH, adventurer.getDirection());
        assertEquals(1, adventurer.getFoundTreasure());
        assertEquals( 1, ((Treasure) gameMap.getGrid()[1][2]).getNumberOfTreasure());
    }

    @Test
    public void moveAdventurersOnMapForwardOnTreasureNoTreasureLeft() throws MapIntegrityException {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        mapGrid[0][1] = new Treasure(0,1, 0);
        GameMap gameMap = new GameMap(2,3, mapGrid);

        MoveEnum[] sequence = {MoveEnum.FORWARD};
        Adventurer adventurer = new Adventurer("Test", 1,1, DirectionEnum.WEST, sequence);

        //When
        adventurerService.moveAdventurersOnMap(Collections.singletonList(adventurer), gameMap);

        //Then
        assertEquals(0, adventurer.getHorizontalPos());
        assertEquals(1, adventurer.getVerticalPos());
        assertEquals(DirectionEnum.WEST, adventurer.getDirection());
        assertEquals(0, adventurer.getFoundTreasure());
        assertEquals( 0, ((Treasure) gameMap.getGrid()[0][1]).getNumberOfTreasure());
    }

}
