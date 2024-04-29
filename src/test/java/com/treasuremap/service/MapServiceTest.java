package com.treasuremap.service;

import com.treasuremap.exception.MapIntegrityException;
import com.treasuremap.model.map.GameMap;
import com.treasuremap.model.map.Mountain;
import com.treasuremap.model.map.GameMapElement;
import com.treasuremap.model.map.Treasure;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapServiceTest {

    MapService mapService = new MapService();

    @Test
    public void testInitializeMapLayoutOK() throws MapIntegrityException {
        // Given

        // When
        GameMap result = mapService.intializeMap(3,4, new ArrayList<>());

        //Then
        assertEquals(3, result.getLength());
        assertEquals(4, result.getWidth());

        assertEquals(3, result.getGrid().length);
        assertEquals(4, result.getGrid()[0].length);
        assertEquals(4, result.getGrid()[1].length);
        assertEquals(4, result.getGrid()[2].length);

    }

    @Test
    public void testInitializeMapWithMountainOK() throws MapIntegrityException {
        // Given
        List<GameMapElement> mountainList = List.of(new Mountain(1, 2));

        // When
        GameMap result = mapService.intializeMap(3,4, mountainList);

        //Then
        assertTrue(result.getGrid()[1][2] instanceof Mountain);
    }

    @Test(expected = MapIntegrityException.class)
    public void testInitializeMapCoordinatesErrorMountainOnMountain() throws MapIntegrityException {
        // Given
        List<GameMapElement> mountainList = List.of(new Mountain(1, 2), new Mountain(1, 2));

        // When
        GameMap result = mapService.intializeMap(3,4, mountainList);
    }

    @Test(expected = MapIntegrityException.class)
    public void testInitializeMapCoordinatesErrorMountainOutOufHorizontalBounds() throws MapIntegrityException {
        // Given
        List<GameMapElement> mountainList = List.of(new Mountain(3, 2));

        // When
        GameMap result = mapService.intializeMap(3,4, mountainList);
    }

    @Test(expected = MapIntegrityException.class)
    public void testInitializeMapCoordinatesErrorMountainOutOufVerticalBounds() throws MapIntegrityException {
        // Given
        List<GameMapElement> mountainList = List.of(new Mountain(1, 5));

        // When
        GameMap result = mapService.intializeMap(3,4, mountainList);
    }

    @Test
    public void testInitializeMapCoordinatesWithTreasure() throws MapIntegrityException {
        // Given
        List<GameMapElement> treasureList = List.of(new Treasure(6, 2, 3));

        // When
        GameMap result = mapService.intializeMap(8,5, treasureList);

        //Then
        assertTrue(result.getGrid()[6][2] instanceof Treasure);
        assertEquals(3, ((Treasure) result.getGrid()[6][2]).getNumberOfTreasure());
    }

}
