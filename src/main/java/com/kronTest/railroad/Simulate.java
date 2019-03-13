package com.kronTest.railroad;

import com.kronTest.exceptions.CollisionException;
import com.kronTest.train.Train;

import java.util.ArrayList;

public class Simulate {
    private ArrayList<Train> trains;
    private Network railroadNetwork;

    public Simulate(ArrayList<Train> trains, Network network){
        this.trains = trains;

        railroadNetwork = network;

        for (Train item : this.trains){
            ArrayList<Integer> stationDistances = new ArrayList<>();
            for (int i = 0; i < item.path.size() - 1 ; i++){
                stationDistances.add(railroadNetwork.getLength(item.path.get(i),item.path.get(i+1)));
            }

            item.stationDistances = stationDistances;
        }
    }

    public void start() throws CollisionException {
        while(trains.size() > 0){
            update();
        }
    }

    private void update() throws CollisionException{
        detectCollisions();
        removeArrivedTrains();
        updateTrainsPosition();
    }


    private void updateTrainsPosition(){
        for(Train train : trains){
            train.updatePosition();
        }
    }
    private void detectCollisions() throws CollisionException {
        for(int i = 0 ; i<trains.size(); i++){
            for (int j = i+1 ; j < trains.size(); j++){
                if(trains.get(i).stationA == trains.get(j).stationA &&
                        trains.get(i).currentDistance == 0 && trains.get(j).currentDistance == 0){
                    throw new CollisionException("You have collision");
                }
                if(trains.get(i).stationA == trains.get(j).stationB && trains.get(i).stationB == trains.get(j).stationA){
                    throw new CollisionException("You have collision");
                }
            }
        }
    }

    private void removeArrivedTrains(){
        for(int i = trains.size() -1 ; i >= 0 ; i--){
            if(trains.get(i).stationA == trains.get(i).stationB){
                trains.remove(i);
            }
        }
    }
}
