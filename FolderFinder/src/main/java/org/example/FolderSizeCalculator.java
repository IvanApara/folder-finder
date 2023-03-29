package org.example;

import java.io.File;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class FolderSizeCalculator extends RecursiveTask<Long>  {

    private final File folder;


    public FolderSizeCalculator(File folder){
        this.folder = folder;
    }

    @Override
    protected Long compute() throws NullPointerException {
        if(folder.isFile()){
            return folder.length();
        }

        long sum = 0;
            List<FolderSizeCalculator> subTask = new LinkedList<>();
//            try {
                File[] files = folder.listFiles();
                for (File file : files) {
                    FolderSizeCalculator task = new FolderSizeCalculator(file);
                    task.fork();
                    subTask.add(task);
                }
                for (FolderSizeCalculator task : subTask) {
                    sum += task.join();
                }
//            }catch (NullPointerException exception){
//
//            }
        return sum;
    }
}
