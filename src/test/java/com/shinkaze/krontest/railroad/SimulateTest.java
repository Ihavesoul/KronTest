package com.shinkaze.krontest.railroad;

import com.shinkaze.krontest.exceptions.CollisionException;
import com.shinkaze.krontest.helpers.RailroadHelper;
import com.shinkaze.krontest.helpers.TrainHelper;
import com.shinkaze.krontest.train.Train;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;


public class SimulateTest {
    private static File[] listFilesMatching(File root, String regex) {
        if (!root.isDirectory()) {
            throw new IllegalArgumentException(root + " is no directory.");
        }
        final Pattern p = Pattern.compile(regex); // careful: could also throw an exception!
        return root.listFiles(file -> p.matcher(file.getName()).matches());
    }

    @Test
    public void collisionExceptionCheck() throws IOException {
        for (File file : listFilesMatching(new File("src/test/resources/TestData"), "stations.*\\.txt")) {
            if (file.isFile()) {
                String fileName = file.getPath();
                String trainFileName = fileName.replace("stations", "trains");
                ArrayList<Train> trains = TrainHelper.ParseTrains(trainFileName);
                Network network = RailroadHelper.ParseNetwork(file.getPath());
                Simulate simulate = new Simulate(trains, network);
                try {
                    simulate.start();
                    Assert.fail("Collision");
                } catch (CollisionException e) {
                }
            }
        }
    }
}