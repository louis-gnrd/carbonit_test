package com.treasuremap.service;

import com.treasuremap.exception.ReadFileException;
import com.treasuremap.model.adventurer.Adventurer;
import com.treasuremap.model.adventurer.DirectionEnum;
import com.treasuremap.model.adventurer.MoveEnum;
import com.treasuremap.model.map.GameMap;
import com.treasuremap.model.map.Mountain;
import com.treasuremap.model.map.Treasure;

import java.awt.*;
import java.util.*;
import java.util.List;

public class StringConversionService {


    private static final String inputSeparator = "-";
    private static final String outputSeparator = " - ";

    private static final Map<String, DirectionEnum> directionStringConversionMap = Map.of(
            "N", DirectionEnum.NORTH,
            "S", DirectionEnum.SOUTH,
            "E", DirectionEnum.EAST,
            "O", DirectionEnum.WEST);

    private static final Map<DirectionEnum, String> directionEnumConversionMap = Map.of(
            DirectionEnum.NORTH, "N",
            DirectionEnum.SOUTH, "S",
            DirectionEnum.EAST, "E",
            DirectionEnum.WEST, "O");

    private static final Map<String, MoveEnum> moveConversionMap = Map.of(
            "A", MoveEnum.FORWARD,
            "G", MoveEnum.LEFT,
            "D", MoveEnum.RIGHT);

    /**
     * Convert a string of the form C - X - Y into a pair of coordinates X - Y.
     *
     * @param line tring to convert
     * @return Coordinates given by the string
     * @throws ReadFileException if the string is incorrectly formated
     */
    public Point convertStringIntoCoordinates(String line) throws ReadFileException {
        String[] mapData = splitLineIntoData(line);
        if (mapData.length != 3) throw new ReadFileException("Error reading line : " + line);
        try {
            return new Point(Integer.parseInt(mapData[1]), Integer.parseInt(mapData[2]));
        } catch (Exception e){
            throw new ReadFileException("Error reading line : " + line);
        }
    }

    /**
     * Convert a string of the form M - X - Y into a montain of coordinates X - Y.
     *
     * @param line string to convert
     * @return Mountain with string data
     * @throws ReadFileException if the string is incorrectly formated
     */
    public Mountain convertStringIntoMountain(String line) throws ReadFileException {
        String[] mountainData = splitLineIntoData(line);
        if (mountainData.length != 3) throw new ReadFileException("Error reading line : " + line);
        try {
            return new Mountain(Integer.parseInt(mountainData[1]), Integer.parseInt(mountainData[2]));
        } catch (Exception e){
            throw new ReadFileException("Error reading line : " + line);
        }
    }

    /**
     * Convert a string of the form T - X - Y - N into a treasure of coordinates X - Y and numberTreasure N.
     *
     * @param line string to convert
     * @return Treasure with string data
     * @throws ReadFileException if the string is incorrectly formated
     */
    public Treasure convertStringIntoTreasure(String line) throws ReadFileException {
        String[] treasureData = splitLineIntoData(line);
        if (treasureData.length != 4) throw new ReadFileException("Error reading line : " + line);
        try {
            return new Treasure(Integer.parseInt(treasureData[1]), Integer.parseInt(treasureData[2]), Integer.parseInt(treasureData[3]));
        } catch (Exception e){
            throw new ReadFileException("Error reading line : " + line);
        }
    }

    /**
     * Convert a string of the form A - Name - X - Y - D - Seq into an Adventurer of coordinates X - Y,
     * name Name, direction D and sequence Seq
     *
     * @param line string to convert
     * @return Adventurer with string data
     * @throws ReadFileException if the string is incorrectly formated
     */
    public Adventurer convertStringIntoAdventurer(String line) throws ReadFileException {
        String[] adventurerData = splitLineIntoData(line);
        DirectionEnum direction = directionStringConversionMap.get(adventurerData[4]);
        if (adventurerData.length != 6 || direction == null) throw new ReadFileException("Error reading line : " + line);
        MoveEnum[] sequence = Arrays.stream(adventurerData[5].split("")).map(moveConversionMap::get).filter(Objects::nonNull).toArray(MoveEnum[]::new);
        if(sequence.length != adventurerData[5].length()) throw new ReadFileException("Error reading line : " + line);
        try {
            return new Adventurer(adventurerData[1], Integer.parseInt(adventurerData[2]), Integer.parseInt(adventurerData[3]), direction, sequence);
        } catch (Exception e){
            throw new ReadFileException("Error reading line : " + line);
        }
    }

    /**
     * Convert a map and all its sub objects into a list containing one formatted string by map element
     *
     * @param map map to convert
     * @return list containing map conversion into string as well as its sub elements
     */
    public List<String> convertMapToString(GameMap map){

        java.util.List<String> result = new ArrayList<>();
        java.util.List<Mountain> mountainList = Arrays.stream(map.getGrid()).flatMap(Arrays::stream)
                .filter(elt -> elt instanceof Mountain)
                .map(elt -> (Mountain) elt)
                .toList();
        List<Treasure> treasureList = Arrays.stream(map.getGrid()).flatMap(Arrays::stream)
                .filter(elt -> elt instanceof Treasure)
                .map(elt -> (Treasure) elt).filter(treasure -> treasure.getNumberOfTreasure() != 0)
                .toList();

        result.add("C - " + map.getLength() + outputSeparator + map.getWidth());
        for (Mountain mountain : mountainList){
            result.add("M - " + mountain.getHorizontalPos() + outputSeparator + mountain.getVerticalPos());
        }
        if (!treasureList.isEmpty()){
            result.add("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}");
        }
        for (Treasure treasure : treasureList){
            result.add("T - " + treasure.getHorizontalPos() + outputSeparator
                    + treasure.getVerticalPos() + outputSeparator
                    + treasure.getNumberOfTreasure());
        }

        return result;
    }

    public List<String> convertAdventurersIntoString(List<Adventurer> adventurerList){
        List<String> result = new ArrayList<>();
        result.add("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}");

        for (Adventurer adventurer : adventurerList){
            result.add("A - " + adventurer.getName() + outputSeparator
                    + adventurer.getHorizontalPos() + outputSeparator
                    + adventurer.getVerticalPos() + outputSeparator
                    + directionEnumConversionMap.get(adventurer.getDirection()) + outputSeparator
                      + adventurer.getFoundTreasure());
        }

        return result;
    }

    private String[] splitLineIntoData(String line){
        return line.replaceAll("\\s", "").split(inputSeparator);
    }

}
