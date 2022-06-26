package com.adventofcode.day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.net.URI;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BinaryDiagnostic {
    public static final int BINARY_LENGTH = 12; // Binary string is of length 12

    /**
     * part one of the problem to calculate the power consumption of the submarine
     * Time complexity: O(n * length(binaryString)) where n is the number of entries in the file
     */
    public static Integer partOne() throws URISyntaxException, IOException{
        int[][] frequency = new int[BINARY_LENGTH][2];  // store frequency of 0's and 1's
        URI uri = BinaryDiagnostic.class.getClassLoader().getResource("input3.txt").toURI();
        BufferedReader in = Files.newBufferedReader(Paths.get(uri));

        String binary = "";
        while( (binary = in.readLine()) != null){
            for(int i = 0; i < BINARY_LENGTH; i++){
                frequency[i][binary.codePointAt(i)-48]++;
            }
        }
        in.close();

        StringBuilder gammaBinary = new StringBuilder();
        StringBuilder epsilonBinary = new StringBuilder();

        for(int i = 0; i < BINARY_LENGTH; i++){
            if(frequency[i][0] > frequency[i][1]){
                gammaBinary.append(0);
                epsilonBinary.append(1);
            }else{
                gammaBinary.append(1);
                epsilonBinary.append(0);
            }
        }

        return Integer.parseInt(gammaBinary.toString(),2) * Integer.parseInt(epsilonBinary.toString(),2);
    }

    public static void main(String[] args) {
        try {
            Integer res = BinaryDiagnostic.partOne();
            System.out.println(res);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
    }
}
