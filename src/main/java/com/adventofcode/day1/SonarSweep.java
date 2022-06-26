package com.adventofcode.day1;

import com.adventofcode.FileProcessor;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class SonarSweep {
    public final FileProcessor fileProcessor = new FileProcessor();
    public static final String file = "input1.txt";

    /**
     * Time complexity: O(N)
     */
    public Integer partOne() throws IOException, URISyntaxException {
        Integer count = 0;
        List<Integer> depths = fileProcessor.processListOfIntegers(file);
        Integer before = depths.get(0);

        for(int i = 1; i < depths.size(); i++){
            Integer after = depths.get(i);

            if(after.compareTo(before) > 0)
                count++;
            before = after;
        }

        return count;
    }

    /**
     * Time complexity: O(N)
    */
    public Integer partTwo(int windowSize) throws IOException, URISyntaxException {
        Integer count = 0;
        List<Integer> depths = fileProcessor.processListOfIntegers(file);

        int before  = depths.subList(0,windowSize).stream().mapToInt(Integer::intValue).sum();
        for(int i = 1; i <= depths.size()-windowSize; i++){
            int after = before - depths.get(i-1) + depths.get(i+windowSize-1);
            if(after > before)
                count++;
            before = after;
        }

        return count;
    }

    public static void main(String[] args) {
        SonarSweep sonarSweep = new SonarSweep();
        try {
            Integer res = sonarSweep.partTwo(3);
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
