package org.example;

import java.io.File;
import java.util.HashMap;

public class SizeCalculator {
    private static char[] multipiers = {'B','K', 'M', 'G', 'T' };
    private static HashMap<Character, Integer> char2multiplayer  = getMultipliers();


    public static String getHumanReadableSize(long size){
        for (int i = 0; i < multipiers.length; i++) {
            double value = ((double) size) / Math.pow(1024, i);
            if (value < 1024){
                return Math.round(value * 100) /100. + ""
                        + multipiers[i]
                        + (i>0 ? "b" : "");
            }
        }
        return "Very big!";
    }


    public static long getSizeFromHumanReadable(String size){
        char sizeFactor = size
                .replaceAll("[0-9\\s+]+", "")
                .charAt(0);
        int multiplier = char2multiplayer.get(sizeFactor);
        return multiplier * Long.valueOf(
                size.replaceAll("[^0-9]", "")
        );
    }

    private static HashMap<Character, Integer> getMultipliers(){
        HashMap<Character, Integer> char2multiplier = new HashMap<>();
        for (int i = 0; i < multipiers.length; i++) {
            char2multiplier.put(multipiers[i], (int) Math.pow(1024, i));
        }
        return char2multiplier;
    }

}
