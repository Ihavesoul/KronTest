package com.shinkaze.krontest.train;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Train {
    private ArrayList<Integer> path;
    private int stationA;
    private int stationB;
    //Distance between stations
    private ArrayList<Integer> stationDistances;
    private int currentDistance;
    private int stationAPathIndex;

    public Train(ArrayList<Integer> path) {
        this.path = path;
        this.stationA = path.get(0);
        this.stationB = path.get(1);
        this.stationAPathIndex = 0;
    }

    public void updatePosition() {
        if (currentDistance < stationDistances.get(stationAPathIndex) - 1) {
            currentDistance++;
        } else {
            stationAPathIndex++;
            stationA = path.get(stationAPathIndex);
            if (stationAPathIndex + 1 < path.size()) {
                stationB = path.get(stationAPathIndex + 1);
            } else {
                stationB = stationA;
            }
            currentDistance = 0;
        }
    }
}
