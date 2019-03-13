package com.kronTest.train;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Train {
    @Getter
    public ArrayList<Integer> path;
    @Getter
    public int stationA ;
    @Getter
    public int stationB;
    @Getter @Setter
    private int stationAPathIndex;

    //Distance between stations
    @Getter
    public ArrayList<Integer> stationDistances;
    @Getter
    public int currentDistance;

    public Train(ArrayList<Integer> path) {
        this.path = path;
        this.stationA = path.get(0);
        this.stationB = path.get(1);
        this.stationAPathIndex = 0;
    }

    public void updatePosition(){
        if(currentDistance < stationDistances.get(stationAPathIndex) -1){
            currentDistance++;
        }
        else{
            stationAPathIndex++;
            stationA = path.get(stationAPathIndex);
            if(stationAPathIndex + 1 < path.size())
            {
                stationB = path.get(stationAPathIndex+1);
            }
            else {
                stationB = stationA;
            }
            currentDistance =0;
        }
    }
}
