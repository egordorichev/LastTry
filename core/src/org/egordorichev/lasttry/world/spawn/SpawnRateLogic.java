package org.egordorichev.lasttry.world.spawn;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.world.environment.Event;

import java.util.ArrayList;

/**
 * Static methods used by the SpawnSystem, when determining the spawn rate.
 *
 * Spawnrate is decreased/increased based on various factors such as current events,
 * items in the environment and current game time.
 */
public class SpawnRateLogic {

    /**
     * Enums containing the spawn rate multiplier.
     */
    private enum SpawnRateMultiplier{
        LESSTHAN40(0.05f), LESSTHAN60(0.025f), DEFAULT(0.0125f);

        private float multiplier;

        SpawnRateMultiplier(float multiplier) {
            this.multiplier = multiplier;
        }

        public float getMultiplier() {
            return this.multiplier;
        }
    }

    /**
     * Receives a spawn rate and applies the appropriate logic rules needed for generating
     * an accurate spawn rate.
     *
     * @param spawnRate the spawnRate to be modified
     * @return accurate spawnrate based on rules defined
     */
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

    /**
     * Returns a modified spawnRate calculated using the percentage of active monsters
     * of the total max spawn limit.
     * Based on the percentage of monsters currently active out of the total max spawn limit.
     * A multiplier is chosen and applied to the spawnrate.
     *
     * @param spawnRate the spawnrate for the multiplier to be applied
     * @param percentageOfSpawnRateAndActiveMonsters active monsters percentage which will be used to
     *                                               choose the multipler
     * @return modified spawnrate incorporating the determined multiplier.
     */
    public static float applyMultiplierToSpawnRate(float spawnRate, float percentageOfSpawnRateAndActiveMonsters) {

        if(percentageOfSpawnRateAndActiveMonsters<40){
            spawnRate = spawnRate * SpawnRateMultiplier.LESSTHAN40.getMultiplier();
        }else if(percentageOfSpawnRateAndActiveMonsters<60){
            spawnRate = spawnRate * SpawnRateMultiplier.LESSTHAN60.getMultiplier();
        }else{
            spawnRate = spawnRate * SpawnRateMultiplier.DEFAULT.getMultiplier();
        }

        return spawnRate;
    }

}
