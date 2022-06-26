package com.adventofcode.day2;

import com.adventofcode.FileProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Dive {
    public final FileProcessor fileProcessor = new FileProcessor();
    public static final String file = "input2.txt";

    public Integer partOne() throws IOException, URISyntaxException {
        Integer posY = 0;
        Integer posX = 0;
        List<String> listOfCommands = fileProcessor.processListOfStrings(file);

        for(String command: listOfCommands){
            String[] commandSplit = command.split("\\s");
            String direction = commandSplit[0];
            Integer movement = Integer.parseInt(commandSplit[1]);

            switch (direction.toLowerCase()){
                case "forward":
                    posX += movement;
                    break;
                case "up":
                    posY -= movement;
                    break;
                case "down":
                    posY += movement;
                    break;
                default:
                    throw new RuntimeException("Unexpected token");
            }
        }
        return posX * posY;
    }

    public Integer partTwo() throws IOException, URISyntaxException {
        List<String> listOfCommands = fileProcessor.processListOfStrings(file);
        Integer aim = 0;
        Integer posX = 0;
        Integer posY = 0;

        for(String command: listOfCommands){
            String[] commandSplit = command.split("\\s");
            String direction = commandSplit[0];
            Integer movement = Integer.parseInt(commandSplit[1]);

            switch (direction.toLowerCase()){
                case "forward":
                    posX += movement;
                    posY += aim*movement;
                    break;
                case "up":
                    aim -= movement;
                    break;
                case "down":
                    aim += movement;
                    break;
                default:
                    throw new RuntimeException("Unexpected token");
            }
        }

        return posX * posY;
    }

    public static void main(String[] args) {
        Dive dive = new Dive();

        try {
            Integer res = dive.partTwo();
            System.out.println(res);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
