package org.egordorichev.lasttry.world.spawn.logic;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;

import java.util.ArrayList;

/**
 * Static methods used by the spawn system, when handling enemy objects.
 */
public class EnemySpawn {

    /**
     * Returns an arraylist of enemies that can be spawned in the current game environment
     * and are less than or equal to the remaining available Max spawn of the biome.
     *
     * @param availableMaxSpawn Amount of the max spawns left out of total max spawns.
     * @return an arraylist containing enemies that are eligible for spawning in environment
     */
    public static ArrayList<Enemy> retrieveEligibleSpawnEnemies(final int availableMaxSpawn) {

        final ArrayList<Enemy> eligibleEnemiesForSpawn = new ArrayList<>();

        Enemy.triggerEnemyCacheCreation();

        Enemy.availEnemies.stream().forEach(enemy -> {
            if(enemy.canSpawn()&&enemy.getSpawnWeight()<=availableMaxSpawn){
                eligibleEnemiesForSpawn.add(enemy);
            }
        });

        return eligibleEnemiesForSpawn;
    }

    /**
     * Returns a boolean based on whether the spawn rate is greater than a randomly generated number
     *
     * @param spawnRateFloat Number representing the spawn rate.
     * @return boolean indicating whether an enemy should spawn.
     */
    public static boolean shouldEnemySpawn(float spawnRateFloat) {
        float randomNumber = LastTry.random.nextFloat()/100;
        if(spawnRateFloat>randomNumber){
            return true;
        }
        return false;
    }

    /**
     * Returns a sum of the Spawn weight of all the enemies in the received arraylist
     *
     * @param enemiesInActiveArea an arraylist containing all enemies found in the active area
     * @return int sum of the spawn weights of all enemies in the parameter arraylist.
     */
    public static int calcSpawnWeightOfActiveEnemies(final ArrayList<Enemy> enemiesInActiveArea) {
        return enemiesInActiveArea.stream().mapToInt(enemy->enemy.getSpawnWeight()).sum();
    }

}
