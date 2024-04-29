package com.treasuremap.service;
import com.treasuremap.exception.ReadFileException;
import com.treasuremap.model.adventurer.Adventurer;
import com.treasuremap.model.adventurer.DirectionEnum;
import com.treasuremap.model.adventurer.MoveEnum;
import com.treasuremap.model.map.GameMap;
import com.treasuremap.model.map.GameMapElement;
import com.treasuremap.model.map.Mountain;
import com.treasuremap.model.map.Treasure;
import org.junit.Test;


import java.awt.Point;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringConversionTest {

    StringConversionService stringConversionService = new StringConversionService();

    @Test
    public void testConvertStringIntoCoordinatesNominalCase() throws ReadFileException {
        //Given
        String stringToTest = "C - 3 - 5";

        //When
        Point result = stringConversionService.convertStringIntoCoordinates(stringToTest);

        //Then
        assertEquals(3, result.x);
        assertEquals(5, result.y);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoCoordinatesErrorLength() throws ReadFileException {
        //Given
        String stringToTest = "C - 3 - 5 - 8";

        //When
        Point result = stringConversionService.convertStringIntoCoordinates(stringToTest);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoCoordinatesErrorNoNumbers() throws ReadFileException {
        //Given
        String stringToTest = "C - 3 - T";

        //When
        Point result = stringConversionService.convertStringIntoCoordinates(stringToTest);
    }

    @Test
    public void testConvertStringIntoMountainNominalCase() throws ReadFileException {
        //Given
        String stringToTest = "M - 1 - 5";

        //When
        Mountain result = stringConversionService.convertStringIntoMountain(stringToTest);

        //Then
        assertEquals(1, result.getHorizontalPos());
        assertEquals(5, result.getVerticalPos());
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoMountainErrorLength() throws ReadFileException {
        //Given
        String stringToTest = "M - 1 - 5 - 1";

        //When
        Mountain result = stringConversionService.convertStringIntoMountain(stringToTest);
    }


    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoMountainErrorNoNumbers() throws ReadFileException {
        //Given
        String stringToTest = "M - 1 - U";

        //When
        Point result = stringConversionService.convertStringIntoCoordinates(stringToTest);
    }

    @Test
    public void testConvertStringIntoTreasureNominalCase() throws ReadFileException {
        //Given
        String stringToTest = "T - 8 - 2 - 4";

        //When
        Treasure result = stringConversionService.convertStringIntoTreasure(stringToTest);

        //Then
        assertEquals(8, result.getHorizontalPos());
        assertEquals(2, result.getVerticalPos());
        assertEquals(4, result.getNumberOfTreasure());
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoTreasureErrorLenght() throws ReadFileException {
        //Given
        String stringToTest = "T - 8 - 2 - 4 - 1";

        //When
        Treasure result = stringConversionService.convertStringIntoTreasure(stringToTest);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoTreasureErrorNoNumbers() throws ReadFileException {
        //Given
        String stringToTest = "T - X - 2 - 1";

        //When
        Treasure result = stringConversionService.convertStringIntoTreasure(stringToTest);
    }

    @Test
    public void testConvertStringIntoAdventurerNominalCase() throws ReadFileException {
        //Given
        String stringToTest = "A - NameTest - 5 - 6 - E - AGDA";

        //When
        Adventurer result = stringConversionService.convertStringIntoAdventurer(stringToTest);

        //Then
        assertEquals("NameTest", result.getName());
        assertEquals(5, result.getHorizontalPos());
        assertEquals(6, result.getVerticalPos());
        assertEquals(DirectionEnum.EAST, result.getDirection());

        MoveEnum[] moveResult = result.getMoveSequence();
        assertEquals(MoveEnum.FORWARD, moveResult[0]);
        assertEquals(MoveEnum.LEFT, moveResult[1]);
        assertEquals(MoveEnum.RIGHT, moveResult[2]);
        assertEquals(MoveEnum.FORWARD, moveResult[3]);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoAdventurerErrorLenght() throws ReadFileException {
        //Given
        String stringToTest = "A - NameTest - 5 - E - AGDA";

        //When
        Adventurer result = stringConversionService.convertStringIntoAdventurer(stringToTest);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoAdventurerErrorNumberFormat() throws ReadFileException {
        //Given
        String stringToTest = "A - NameTest - 5 - K - E - AGDA";

        //When
        Adventurer result = stringConversionService.convertStringIntoAdventurer(stringToTest);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoAdventurerErrorDirectionUnknown() throws ReadFileException {
        //Given
        String stringToTest = "A - NameTest - 5 - 6 - T - AGDA";

        //When
        Adventurer result = stringConversionService.convertStringIntoAdventurer(stringToTest);
    }

    @Test(expected = ReadFileException.class)
    public void testConvertStringIntoAdventurerErrorMoveUnkown() throws ReadFileException {
        //Given
        String stringToTest = "A - NameTest - 5 - 6 - E - AGZA";

        //When
        Adventurer result = stringConversionService.convertStringIntoAdventurer(stringToTest);
    }

    @Test
    public void testConvertMapIntoStringNominalCase() {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[2][3];
        GameMap map = new GameMap(2, 3, mapGrid);

        //When
        List<String> result = stringConversionService.convertMapToString(map);

        //Then
        assertEquals(1, result.size());
        assertEquals("C - 2 - 3", result.get(0));
    }

    @Test
    public void testConvertMapIntoStringWithMountain() {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[5][2];
        mapGrid[3][0] = new Mountain(3, 0);
        GameMap map = new GameMap(5, 2, mapGrid);

        //When
        List<String> result = stringConversionService.convertMapToString(map);

        //Then
        assertEquals(2, result.size());
        assertEquals("C - 5 - 2", result.get(0));
        assertEquals("M - 3 - 0", result.get(1));
    }

    @Test
    public void testConvertMapIntoStringWithTreasure() {
        //Given
        GameMapElement[][] mapGrid = new GameMapElement[6][9];
        mapGrid[4][8] = new Treasure(4, 8, 7);
        GameMap map = new GameMap(6, 9, mapGrid);

        //When
        List<String> result = stringConversionService.convertMapToString(map);

        //Then
        assertEquals(3, result.size());
        assertEquals("C - 6 - 9", result.get(0));
        assertEquals("# {T comme Trésor} - {Axe horizontal} - {Axe vertical} - {Nb. de trésors restants}", result.get(1));
        assertEquals("T - 4 - 8 - 7", result.get(2));
    }

    @Test
    public void convertAdventurerIntoString() {
        //Given
        Adventurer adventurer = new Adventurer("Toto", 5, 12, DirectionEnum.SOUTH, null);
        adventurer.increaseTreasureFound();
        adventurer.increaseTreasureFound();

        //When
        List<String> result = stringConversionService.convertAdventurersIntoString(Collections.singletonList(adventurer));

        //Then
        assertEquals(2,result.size());
        assertEquals("# {A comme Aventurier} - {Nom de l’aventurier} - {Axe horizontal} - {Axe vertical} - {Orientation} - {Nb. trésors ramassés}", result.get(0));
        assertEquals("A - Toto - 5 - 12 - S - 2", result.get(1));
    }

}
