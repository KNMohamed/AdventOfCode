package com.adventofcode.day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.net.URI;
import java.util.stream.Collectors;

public class GiantSquid  {
    public static final String file = "input4.txt";

    /**
     * This method should be called first as the drawn numbers appear on the first line;
     * @return
     */
    public static List<Integer> getDrawnNumbers() throws URISyntaxException, IOException {
        URI uri = GiantSquid.class.getClassLoader().getResource(file).toURI();
        return Arrays.stream(Files.newBufferedReader(Paths.get(uri))
                .readLine()
                .split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }

    public static List<BingoCard> getBingoCards() throws URISyntaxException, IOException {
        List<BingoCard> gameBingoCards = new ArrayList<>();
        URI uri = GiantSquid.class.getClassLoader().getResource(file).toURI();
        try(BufferedReader in = Files.newBufferedReader(Paths.get(uri))){
            in.readLine();  // Skip first line
            String line = "";
            List<List<Integer>> card = new ArrayList<>();
            while((line = in.readLine()) != null){
                if(line.trim().length() == 0) continue;
                List<Integer> row = Arrays.stream(line.trim().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
                card.add(row);
                if(card.size() == 5){
                    BingoCard bingoCard = new BingoCard(card);
                    gameBingoCards.add(bingoCard);
                    card.clear();
                }
            }
        }
        return gameBingoCards;
    }

    public static void main(String[] args) {
        try {
            GiantSquid.getBingoCards();
        }catch (URISyntaxException | IOException e){
            e.printStackTrace();
        }
    }

}
