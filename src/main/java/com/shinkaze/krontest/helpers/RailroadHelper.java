package com.shinkaze.krontest.helpers;

import com.shinkaze.krontest.railroad.Network;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RailroadHelper {
    public static Network ParseNetwork(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        String[] lines = Files.readAllLines(path).toArray(new String[0]);
        int stationsTotal = Integer.parseInt(lines[0]);
        Network network = new Network(stationsTotal);

        for (int i = 0; i < lines.length - 1; i++) {
            String[] splittedLine = lines[i + 1].split(" ");
            network.setLength(Integer.parseInt(splittedLine[0]), Integer.parseInt(splittedLine[1]), Integer.parseInt(splittedLine[2]));
        }
        return network;
    }
}
