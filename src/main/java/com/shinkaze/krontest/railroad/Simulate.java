package com.shinkaze.krontest.railroad;

import com.shinkaze.krontest.exceptions.CollisionException;
import com.shinkaze.krontest.train.Train;

import java.util.ArrayList;

class Simulate {
    private ArrayList<Train> trains;
    private Network railroadNetwork;

    Simulate(ArrayList<Train> trains, Network network) {
        this.trains = trains;

        railroadNetwork = network;

        for (Train item : this.trains) {
            ArrayList<Integer> stationDistances = new ArrayList<>();
            for (int i = 0; i < item.getPath().size() - 1; i++) {
                stationDistances.add(railroadNetwork.getLength(item.getPath().get(i), item.getPath().get(i + 1)));
            }

            item.setStationDistances(stationDistances);
        }
    }

    void start() throws CollisionException {
        while (trains.size() > 0) {
            update();
        }
    }

    private void update() throws CollisionException {
        detectCollisions();
        removeArrivedTrains();
        updateTrainsPosition();
    }


    private void updateTrainsPosition() {
        for (Train train : trains) {
            train.updatePosition();
        }
    }

    private void detectCollisions() throws CollisionException {
        for (int i = 0; i < trains.size(); i++) {
            for (int j = i + 1; j < trains.size(); j++) {
                if ((trains.get(i).getStationA() == trains.get(j).getStationA()
                        && trains.get(i).getCurrentDistance() == 0
                        && trains.get(j).getCurrentDistance() == 0)
                        || (trains.get(i).getStationA() == trains.get(j).getStationB()
                        && trains.get(i).getStationB() == trains.get(j).getStationA())) {
                    throw new CollisionException("You have collision");
                }
            }
        }
    }

    private void removeArrivedTrains() {
        for (int i = trains.size() - 1; i >= 0; i--) {
            if (trains.get(i).getStationA() == trains.get(i).getStationB()) {
                trains.remove(i);
            }
        }
    }
}
