package com.adventofcode;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;


/**
 * This class will consist exclusively of methods that process files to collections
 */
public class FileProcessor {
    public List<Integer> processListOfIntegers(String file) throws IOException, URISyntaxException {
        URL res = getClass().getClassLoader().getResource(file);
        List<Integer> listOfIntegers = Files.newBufferedReader(Paths.get(res.toURI()))
                .lines().map(Integer::parseInt)
                .collect(Collectors.toList());

        return listOfIntegers;
    }
}
