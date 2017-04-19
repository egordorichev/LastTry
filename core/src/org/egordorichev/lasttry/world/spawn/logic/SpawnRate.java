package org.egordorichev.lasttry.world.spawn.logic;

import org.egordorichev.lasttry.world.environment.Event;

import java.util.ArrayList;

public class SpawnRate {
    private enum SpawnRateMultiplier{
        LESS_THAN_40(0.05f), LESS_THAN_60(0.025f), DEFAULT(0.0125f);

        private float multiplier;

        SpawnRateMultiplier(float multiplier) {
            this.multiplier = multiplier;
        }

        public float getMultiplier() {
            return this.multiplier;
        }
    }

    public static int calculateSpawnRate(int spawnRate) {
        //Get active events
        //ArrayList<Event> activeEvents = LastTry.environment.getCurrentEvents();

        //TODO Implement spawn rate rules based on events

        spawnRate = calcSpawnRateBasedOnEvents(null, spawnRate);
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
        if (percentageOfSpawnRateAndActiveMonsters < 40) {
            spawnRate = spawnRate * SpawnRateMultiplier.LESS_THAN_40.getMultiplier();
        } else if (percentageOfSpawnRateAndActiveMonsters < 60) {
            spawnRate = spawnRate * SpawnRateMultiplier.LESS_THAN_60.getMultiplier();
        } else{
            spawnRate = spawnRate * SpawnRateMultiplier.DEFAULT.getMultiplier();
        }

        return spawnRate;
    }

}
