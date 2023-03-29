package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ForkJoinPool;

public class Main {
    private static  Logger LOGGER = LogManager.getRootLogger();
    private static final Marker ERRORS_MARKER = MarkerManager.getMarker("ERRORS_HISTORY");
    private static char[] multipiers = {'B','K', 'M', 'G', 'T' };
    public static void main(String[] args) {
        try {
            String folderPath = "D:/Games";
            File file = new File(folderPath);
            FolderSizeCalculator calculator = new FolderSizeCalculator(file);
            ForkJoinPool pool = new ForkJoinPool();
            long size = pool.invoke(calculator);
            System.out.println(size);
        }catch (NullPointerException ex){
            LOGGER.error(ERRORS_MARKER, "there is no necessary folder/file");
        }
    }

    public static long getFolderSize(File folder){
        if(folder.isFile()){
            return folder.length();
        }

        long sum = 0;
        File[] files = folder.listFiles();

        for (File file: files){
            sum += getFolderSize(file);
        }
        return sum;
    }

    public static String getHumanReadableSize(long size){
        for (int i = 0; i < multipiers.length; i++) {
            double value = size / Math.pow(1024, i);
            if (value < 1024){
                return Math.round(value) + ""
                        + multipiers[i]
                        + (i>0 ? "b" : "");
            }
        }
        return "Very big!";
    }


    public static long getSizeFromHumanReadable(String size){
        HashMap<Character, Integer> char2multiplayer  = getMultipliers();
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