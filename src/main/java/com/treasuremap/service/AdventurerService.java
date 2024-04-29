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


import java.awt.Point;
import java.util.List;

public class AdventurerService {

    private final AdventurerUtils adventurerUtils;

    public AdventurerService(AdventurerUtils adventurerUtils) {
        this.adventurerUtils = adventurerUtils;
    }


    /**
     * Given a list of adventurer and a map, spawn them on the map and
     * makes them move in their sequence on the map
     *
     * @param adventurerList list of the adventurer that will travel the map
     * @param gameMap map giving the position of all the obstacles
     */
    public void moveAdventurersOnMap(List<Adventurer> adventurerList, GameMap gameMap) throws MapIntegrityException {

        //Start by verifying all adventurers starting positions
        for (Adventurer adventurer : adventurerList) {
            if (isCoordinatesForbiddenOnMap(adventurer.getHorizontalPos(), adventurer.getVerticalPos(), gameMap)){
                throw new MapIntegrityException("Adventurer " + adventurer.getName() +
                        " have an incorrect starting position (outside the map or on mountain)");
            }
        }

        //Then make them move
        int maxSequence = adventurerList.stream().mapToInt(av -> av.getMoveSequence().length).max().orElse(0);
        for(int i = 0; i < maxSequence; i++) {
            for (Adventurer adventurer : adventurerList) {
                MoveEnum move = adventurer.getMoveSequence()[i];
                if (move == MoveEnum.FORWARD) {
                    moveAdventurerForward(adventurer, adventurerList, gameMap);
                } else {
                    DirectionEnum newDirection = adventurerUtils.calculateNewDirection(adventurer, move);
                    adventurer.setDirection(newDirection);
                }
            }
        }
    }

    /**
     * Given an adventurer, make him/her move forward in the direction (s)he is facing.
     *
     * @param adventurer the adventurer to move
     * @param adventurerList list of all the adventurer present on the map, for slot availability calculation
     * @param gameMap map giving the position of all the obstacles
     */
    private void moveAdventurerForward(Adventurer adventurer, List<Adventurer> adventurerList, GameMap gameMap){
           Point newCoordinates = adventurerUtils.calculateForwardNewCoordinates(adventurer);

           //Verify if the new coordinates are not taken by a blocking element
           boolean haveObstacle = adventurerList.stream().anyMatch(av -> av.getHorizontalPos() == newCoordinates.x
                   && av.getVerticalPos() == newCoordinates.y);

           if (isCoordinatesForbiddenOnMap(newCoordinates.x, newCoordinates.y, gameMap) || haveObstacle) {
               return;
           }

           //Set new Coordinates if allowed, and do not forget to take the treasure :)
           adventurer.setHorizontalPos(newCoordinates.x);
           adventurer.setVerticalPos(newCoordinates.y);
           GameMapElement element = gameMap.getGrid()[adventurer.getHorizontalPos()][adventurer.getVerticalPos()];
           if (element instanceof Treasure) {
               grabTreasure(adventurer, ((Treasure) element));
           }
    }

    private void grabTreasure(Adventurer adventurer, Treasure treasure){
        int nbTreasure = treasure.getNumberOfTreasure();
        if(nbTreasure > 0) {
            adventurer.increaseTreasureFound();
            treasure.decreaseNumberOfTreasure();
        }
    }

    /**
     * Given two positions on the map, returns true is the position is occupied by a mountain or outside the map
     *
     * @param xpos horizontal position of the coordinate
     * @param ypos vertical position of the coordinate
     * @param gameMap on which the adventurer travels
     *
     * @return true if the coordinates indicates a forbidden position for the adventurer
     */
    private boolean isCoordinatesForbiddenOnMap(int xpos, int ypos , GameMap gameMap){
        if (xpos<0 || ypos<0 || xpos>= gameMap.getLength() || ypos >= gameMap.getWidth()){
            return true;
        }
        GameMapElement element = gameMap.getGrid()[xpos][ypos];
        return element instanceof Mountain;
    }

}
