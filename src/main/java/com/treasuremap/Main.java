package com.treasuremap;

import com.treasuremap.exception.MapIntegrityException;
import com.treasuremap.exception.ReadFileException;
import com.treasuremap.model.adventurer.Adventurer;
import com.treasuremap.model.map.GameMap;
import com.treasuremap.model.map.GameMapElement;
import com.treasuremap.service.AdventurerService;
import com.treasuremap.service.MapService;
import com.treasuremap.service.StringConversionService;
import com.treasuremap.utils.AdventurerUtils;

import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


public class Main {

    private static final AdventurerService adventurerService = new AdventurerService(new AdventurerUtils());
    private static final MapService gameMapService = new MapService();
    private static final StringConversionService stringConversionService = new StringConversionService();

    public static void main(String[] args) throws IOException, ReadFileException, MapIntegrityException {

        List<String> allLines = Files.readAllLines(Paths.get(args[0]));

        // Conversion en entitles
        allLines = allLines.stream().filter(line -> !line.startsWith("#")).toList();

        Point mapDimensions = null;
        List<GameMapElement> gameMapElementList = new ArrayList<>();
        List<Adventurer> adventurerList = new ArrayList<>();

        for (String line : allLines){
            String[] lineData = line.replaceAll("\\s", "").split("-");
            //ValidateLine ??
            switch (line.charAt(0)){ //
                case 'C':
                    mapDimensions = stringConversionService.convertStringIntoCoordinates(line);
                    break;
                case 'M':
                    gameMapElementList.add(stringConversionService.convertStringIntoMountain(line));
                    break;
                case 'T':
                    gameMapElementList.add(stringConversionService.convertStringIntoTreasure(line));
                    break;
                case 'A':
                    adventurerList.add(stringConversionService.convertStringIntoAdventurer(line));
                    break;
                default: throw new ReadFileException("");
            }
        }

        if (mapDimensions == null){
            throw new ReadFileException("Map is missing from file !");
        }

        GameMap gameMap = gameMapService.intializeMap(mapDimensions.x, mapDimensions.y ,gameMapElementList);
        adventurerService.moveAdventurersOnMap(adventurerList, gameMap);

        List<String> resultLines = stringConversionService.convertMapToString(gameMap);
        resultLines.addAll(stringConversionService.convertAdventurersIntoString(adventurerList));

        Path file = Paths.get(args[1]);
        Files.write(file, resultLines, StandardCharsets.UTF_8);
    }

    // C:\_DEV\the-file-name.txt

    //mvn exec:java -Dexec.mainClass=com.treasuremap.Main -Dexec.args="inputFile.txt outputFile.txt"




}