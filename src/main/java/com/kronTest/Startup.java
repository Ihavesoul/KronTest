package com.kronTest;

import com.kronTest.exceptions.CollisionException;
import com.kronTest.helpers.RailroadHelper;
import com.kronTest.helpers.TrainHelper;
import com.kronTest.railroad.Network;
import com.kronTest.railroad.Simulate;
import com.kronTest.train.Train;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Startup {
    public static void main(String[] args) throws IOException{
        for (File file : listFilesMatching(new File("src/main/resources/TestData"), "stations.*\\.txt")) {
            if (file.isFile()) {
                String fileName = file.getPath();
                String trainFileName = fileName.replace("stations","trains");
                ArrayList<Train> trains = TrainHelper.ParseTrains(trainFileName);
                Network network = RailroadHelper.ParseNetwork(file.getPath());
                Simulate simulate = new Simulate(trains,network);

                System.out.println("Checking " + fileName + " and " + trainFileName);
                try {
                    simulate.start();
                }
                catch(CollisionException e){
                    System.out.println("Collision");
                }
            }
        }
    }
    private static File[] listFilesMatching(File root, String regex) {
        if(!root.isDirectory()) {
            throw new IllegalArgumentException(root+" is no directory.");
        }
        final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
        return root.listFiles(file -> p.matcher(file.getName()).matches());
    }
}
