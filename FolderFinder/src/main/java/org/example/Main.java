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
    public static void main(String[] args) {
        try {
            String folderPath = "D:/Games";
            File file = new File(folderPath);
            Node root = new Node(file);

            FolderSizeCalculator calculator = new FolderSizeCalculator(root);
            ForkJoinPool pool = new ForkJoinPool();
            pool.invoke(calculator);
            System.out.println(root);
        }catch (NullPointerException ex){
            LOGGER.error(ERRORS_MARKER, "there is no necessary folder/file");
        }
    }


}