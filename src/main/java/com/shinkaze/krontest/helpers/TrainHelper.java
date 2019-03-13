package com.shinkaze.krontest.helpers;

import com.shinkaze.krontest.train.Train;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TrainHelper {
    private static ArrayList<Integer> parsePath(String line) {
        String[] splittedLine = line.split(" ");
        ArrayList<Integer> path = new ArrayList<Integer>();
        for (String item : splittedLine) {
            path.add(Integer.parseInt(item));
        }
        return path;
    }

    public static ArrayList<Train> ParseTrains(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        String[] lines = Files.readAllLines(path).toArray(new String[0]);
        int trainsTotal = Integer.parseInt(lines[0]);

        ArrayList<Train> trains = new ArrayList<>(trainsTotal);
        for (int i = 0; i < trainsTotal; i++) {
            trains.add(new Train(parsePath(lines[i + 1])));
        }
        return trains;
    }

}