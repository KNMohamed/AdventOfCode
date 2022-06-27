package com.adventofcode.day3;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BinaryDiagnostic {
    public static List<String> getDiagnosticReport(String file) throws URISyntaxException, IOException {
        URL uri = BinaryDiagnostic.class.getClassLoader().getResource(file);
        return Files.newBufferedReader(Paths.get(uri.toURI())).lines().collect(Collectors.toList());
    }

    /**
     * Find the frequency of 1 bits, given a bit position n and a list of binary numbers
     * @param binaries is the list of binaries from the input file
     * @param n is the bit position to consider
     * @return
     */
    public static long frequencyOfOneInBitPositionN(List<String> binaries, int n){
        int count = 0;
        return binaries.stream().filter(binary -> binary.charAt(n) == '1').count();
    }

    /**
     * Find the frequency of 1 bits, given a bit position n and a list of binary numbers
     * @param binaries is the list of binaries from the input file
     * @param n is the bit position to consider
     * @return
     */
    public static long frequencyOfZeroInBitPositionN(List<String> binaries, int n){
        int count = 0;
        return binaries.stream().filter(binary -> binary.charAt(n) == '0').count();
    }

    /**
     * returns the most frequent bit, given a bit position n, and a list of binary numbers
     * @param binaries is the list of binaries from the input file
     * @param n is the bit position to consider
     * @return
     */
    public static char getMostFrequentInBitPositionN(List<String> binaries, int n) {
        return frequencyOfOneInBitPositionN(binaries,n) > frequencyOfZeroInBitPositionN(binaries,n) ? '1' : '0';
    }

    /**
     * get the GammaRate by finding the most common bit in the corresponding position of all numbers in the diagnostic report
     * Time Complexity O(n*k) where, n is the number of lines in the diagnostic report; k is the length of the binary numbers
     * @param binaries
     * @return
     */
    public static String getGammaRate(List<String> binaries) {
        int len = binaries.get(0).length();
        StringBuilder gammaRate = new StringBuilder();

        for(int i = 0; i < len; i++) {
            gammaRate.append(getMostFrequentInBitPositionN(binaries,i));
        }

        return gammaRate.toString();
    }

    /**
     * Helper function to invert binaries (e.x - Epsilon is the invert of Gamma; CO2 scrubber rating is invert of oxygen generator rating);
     * @param binary
     * @return
     */
    public static String invertBinary(String binary){
        return binary.replace('0','x').replace('1','0').replace('x','1');
    }

    /**
     * Calculate the power consumption given a list of binaries using generated gamma and epsilon binaries
     * Time Complexity O(n*k) where, n is the number of lines in the diagnostic report; k is the length of the binary numbers
     * @param binaries
     * @return
     */
    public static Integer calculatePowerConsumption(List<String> binaries){
        Integer powerConsumption = 0;
        if(binaries == null || binaries.isEmpty()) return 0;
        String gammaRate = getGammaRate(binaries);
        String epsilonRate = invertBinary(gammaRate);
        powerConsumption = Integer.parseInt(gammaRate,2) * Integer.parseInt(epsilonRate,2);
        return powerConsumption;
    }

    public static void main(String[] args) {
       List<String> binaries = null;
        try {
            binaries = getDiagnosticReport("input3.txt");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Integer powerConsumption = BinaryDiagnostic.calculatePowerConsumption(binaries);
        System.out.println("Power consumption is: " + powerConsumption);
    }

}
