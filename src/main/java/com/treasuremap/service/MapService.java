package com.treasuremap.service;

import com.treasuremap.exception.MapIntegrityException;
import com.treasuremap.model.map.GameMap;
import com.treasuremap.model.map.GameMapElement;


import java.util.List;

public class MapService {

    /**
     * Initialize a map of given dimensions on which there will be mountains and treasure
     *
     * @param length number of horizontal slots of the map
     * @param width number of vertical slots of the map
     * @param obstacleList list containing x and y position of the map's obstacles
     * @return map with treasure and mountains on its grid
     * @throws MapIntegrityException if map cannot be instantiated given the arguments
     */
    public GameMap intializeMap(int length, int width, List<GameMapElement> obstacleList) throws MapIntegrityException {
        GameMapElement[][] mapGrid = new GameMapElement[length][width];
        for (GameMapElement obstacle : obstacleList) {
            putElementOnGrid(obstacle, mapGrid);
        }
        return new GameMap(length, width, mapGrid);
    }

    private void putElementOnGrid(GameMapElement element, GameMapElement[][] mapGrid) throws MapIntegrityException {
        GameMapElement gridSlot = getGridSlotAtCoordinate(mapGrid, element.getHorizontalPos(), element.getVerticalPos());
        if (gridSlot != null) {
            String errorMessage = "Two element of the grid or more have the same position than the mountain in coordinates : " +
                    element.getHorizontalPos() +
                    ";" +
                    element.getVerticalPos();
            throw new MapIntegrityException(errorMessage);
        }
        mapGrid[element.getHorizontalPos()][element.getVerticalPos()] = element;
    }

    /**
     * Given a grid of slots and two coordinates, gets the Slot matching the coordinates or throw a Functional exception
     *
     * @param grid grid on wich the slot will be extracted
     * @param horizontalPos pair of horizontal and vertical position on wich the slot will be extracted
     * @param verticalPos pair of horizontal and vertical position on wich the slot will be extracted
     * @return Slot of the given coordinates on the grid
     * @throws MapIntegrityException if the coordinates are out of the grid range
     */
    private GameMapElement getGridSlotAtCoordinate(GameMapElement[][] grid, int horizontalPos, int verticalPos) throws MapIntegrityException {
        GameMapElement result;
        try {
            result = grid[horizontalPos][verticalPos];
        } catch (IndexOutOfBoundsException e){
            String errorMessage = "Element with coordinates : " +
                    horizontalPos +
                    ";" +
                    verticalPos +
                    " is outside the map !";
            throw new MapIntegrityException(errorMessage);
        }
        return result;
    }
}
