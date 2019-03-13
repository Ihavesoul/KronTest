package com.shinkaze.krontest;

import com.shinkaze.krontest.exceptions.CollisionException;
import com.shinkaze.krontest.helpers.RailroadHelper;
import com.shinkaze.krontest.helpers.TrainHelper;
import com.shinkaze.krontest.railroad.Network;
import com.shinkaze.krontest.railroad.Simulate;
import com.shinkaze.krontest.train.Train;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class Startup {
    public static void main(String[] args) throws IOException {
        for (File file : listFilesMatching(new File("src/main/resources/TestData"), "stations.*\\.txt")) {
            if (file.isFile()) {
                String fileName = file.getPath();
                String trainFileName = fileName.replace("stations", "trains");
                ArrayList<Train> trains = TrainHelper.ParseTrains(trainFileName);
                Network network = RailroadHelper.ParseNetwork(file.getPath());
                Simulate simulate = new Simulate(trains, network);

                System.out.println("Checking " + fileName + " and " + trainFileName);
                try {
                    simulate.start();
                } catch (CollisionException e) {
                    System.out.println("Collision");
                }
            }
        }
    }

    private static File[] listFilesMatching(File root, String regex) {
        if (!root.isDirectory()) {
            throw new IllegalArgumentException(root + " is no directory.");
        }
        final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
        return root.listFiles(file -> p.matcher(file.getName()).matches());
    }
}
