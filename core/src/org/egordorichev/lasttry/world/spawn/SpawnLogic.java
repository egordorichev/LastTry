package org.egordorichev.lasttry.world.spawn;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.world.environment.Event;

import java.util.ArrayList;

/**
 * Created by Admin on 19/04/2017.
 */
public class SpawnLogic {


    public static int calculateSpawnRate(int spawnRate) {

        //Get active events
        ArrayList<Event> activeEvents = LastTry.environment.getCurrentEvents();

        spawnRate = calcSpawnRateBasedOnEvents(activeEvents, spawnRate);

        spawnRate = calcSpawnRateBasedOnItems(spawnRate);

        spawnRate = calcSpawnRateBasedOnTime(spawnRate);

        return spawnRate;
    }

    private static int calcSpawnRateBasedOnEvents(ArrayList<Event> activeEvents, int spawnRate) {
        //TODO Implement spawn info in events
        return spawnRate;
    }


    private static int calcSpawnRateBasedOnTime(int spawnRate) {
        //TODO Implement spawn info based on day
        return spawnRate;
    }

    private static int calcSpawnRateBasedOnItems(int spawnRate) {
        //TODO Implement spawn rate altering based on items in environment
        return spawnRate;
    }


    public static float applyMultiplierToSpawnRate(float spawnRate, float percentageOfSpawnRateAndActiveMonsters) {

        double spawnRateDouble = spawnRate;

        if(percentageOfSpawnRateAndActiveMonsters<40){
            spawnRateDouble = spawnRateDouble * 0.1;
        }else if(percentageOfSpawnRateAndActiveMonsters<60){
            spawnRateDouble = spawnRateDouble * 0.05;
        }else{
            spawnRateDouble = spawnRateDouble * 0.01;
        }

        return (float)spawnRateDouble;
    }

}
