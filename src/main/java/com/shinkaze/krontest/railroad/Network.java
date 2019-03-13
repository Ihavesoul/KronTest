package com.shinkaze.krontest.railroad;

public class Network {
    private int[][] connectionGraph;

    public Network(int stations) {
        connectionGraph = new int[stations][stations];
    }

    int getLength(int first, int second) {
        return connectionGraph[first][second];
    }

    public void setLength(int first, int second, int length) {
        connectionGraph[first][second] = length;
        connectionGraph[second][first] = length;
    }
}
