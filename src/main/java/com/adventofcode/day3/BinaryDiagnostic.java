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
        return binaries.stream().filter(binary -> binary.charAt(n) == '1').count();
    }

    /**
     * Find the frequency of 1 bits, given a bit position n and a list of binary numbers
     * @param binaries is the list of binaries from the input file
     * @param n is the bit position to consider
     * @return
     */
    public static long frequencyOfZeroInBitPositionN(List<String> binaries, int n){
        return binaries.stream().filter(binary -> binary.charAt(n) == '0').count();
    }

    /**
     * returns the most frequent bit, given a bit position n, and a list of binary numbers
     * Note: if frequency of 0 bit and 1 bit is the same return 1
     * @param binaries is the list of binaries from the input file
     * @param n is the bit position to consider
     * @return
     */
    public static char getMostFrequentInBitPositionN(List<String> binaries, int n) {
        return frequencyOfZeroInBitPositionN(binaries,n) > frequencyOfOneInBitPositionN(binaries,n) ? '0' : '1';
    }

    /**
     * get the GammaRate by finding the most common bit in the corresponding position of all numbers in the diagnostic report
     * Time Complexity O(n*k) where, n is the number of lines in the diagnostic report; k is the length of the binary numbers
     * @param binaries is the list of binaries from the input file
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
     * Helper function to invert binaries (e.x - Epsilon is the invert of Gamma);
     * @param binary is the binary to invert
     * @return a string representation of the inverted binary
     */
    public static String invertBinary(String binary){
        return binary.replace('0','x').replace('1','0').replace('x','1');
    }

    /**
     * Calculate the power consumption given a list of binaries using generated gamma and epsilon binaries
     * Time Complexity O(n*k) where, n is the number of lines in the diagnostic report; k is the length of the binary numbers
     * @param binaries is the list of binaries from the input file
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

    /**
     * oxygen generator rating determined by iteratively finding the most common value at each bit position while removing the least common from the list
     * @param binaries is the list of binaries from the input file
     * @return
     */
    public static Integer getOxygenRating(List<String> binaries) {
        List<String> oxygenRating = new ArrayList<>(binaries);
        int bitLength = oxygenRating.get(0).length();
        for(int i = 0; i < bitLength; i++){
            final int bitPos = i;
            char bit = getMostFrequentInBitPositionN(oxygenRating,i);
            oxygenRating.removeIf(binary -> binary.charAt(bitPos) != bit);
            if(oxygenRating.size() == 1) break;
        }

        return Integer.parseInt(oxygenRating.get(0),2);
    }

    /**
     * CO2 scrubber rating determined by applying inverse logic to the oxygen generator rating
     * @param binaries is the list of binaries from the input file
     * @return
     */
    public static Integer getCo2Rating(List<String> binaries) {
        List<String> co2Rating = new ArrayList<>(binaries);
        int bitLength = co2Rating.get(0).length();
        for(int i = 0; i < bitLength; i++){
            final int bitPos = i;
            char bit = getMostFrequentInBitPositionN(co2Rating,i);
            co2Rating.removeIf(binary -> binary.charAt(bitPos) == bit);
            if(co2Rating.size() == 1) break;
        }

        return Integer.parseInt(co2Rating.get(0),2);
    }

    /**
     * Life support rating determined by multiplying the CO2 scrubber rating by the oxygen generator rating
     * @param binaries
     * @return
     */
    public static Integer getLifeSupportRating(List<String> binaries) {
        return getCo2Rating(binaries) * getOxygenRating(binaries);
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
        if(binaries != null){
            Integer lifeSupportRating = getLifeSupportRating(binaries);
            System.out.println(lifeSupportRating);
        }
    }

}
