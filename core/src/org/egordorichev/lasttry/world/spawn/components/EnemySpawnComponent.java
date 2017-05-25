package org.egordorichev.lasttry.world.spawn.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;

import java.util.ArrayList;
import java.util.List;

public class EnemySpawnComponent {
    // availableMaxSpawn is an int denoting the remaining spawn value allowed for a new spawn in the biome
    public static ArrayList<Enemy> retrieveEligibleSpawnEnemies(final int availableMaxSpawn) {
        final ArrayList<Enemy> eligibleEnemiesForSpawn = new ArrayList<>();

        Enemies.ENEMY_CACHE.keySet().stream().forEach(enemyKey -> {
	        Enemy enemy = Enemies.create(enemyKey);
	        if(enemy.canSpawn() && enemy.getSpawnWeight() <= availableMaxSpawn){
	            eligibleEnemiesForSpawn.add(enemy);
            }
        });

        return eligibleEnemiesForSpawn;
    }

    public static boolean shouldEnemySpawn(float spawnRateFloat) {
        // Spawn rate is calculated as '1' in 'spawnRate'
        float randomNumber = LastTry.random.nextFloat()/100;

        // if spawn rate is less than random number, an enemy can spawn this tick
        if (spawnRateFloat > randomNumber) {
            return true;
        }

        return false;
    }

    public static int calcSpawnWeightOfActiveEnemies(final List<Enemy> enemiesInActiveArea) {
        return enemiesInActiveArea.stream().mapToInt(enemy->enemy.getSpawnWeight()).sum();
    }

    public static Enemy retrieveRandomEnemy(List<Enemy> eligibleEnemiesForSpawning) {
        int randomIndex = LastTry.random.nextInt(eligibleEnemiesForSpawning.size());
        return eligibleEnemiesForSpawning.get(randomIndex);
    }

    public static ArrayList<Enemy> generateEnemiesInActiveArea(CircleAreaComponent playerActiveArea) {
        // Must clear the list each time, as it has no way of knowing if an entity has died so we must rebuild
        // each time to ensure we have an up to date list
        ArrayList<Enemy> enemiesInActiveArea = new ArrayList<>();
        List<Enemy> enemyEntities = Globals.entityManager.getEnemyEntities();

        enemyEntities.stream().forEach(enemy -> {
            // TODO Rethink
            // Checks if the enemy is in the active area and if the enemy is not already in the list, it adds to the list
            if(GridComponent.isCreatureInPlayerActiveArea(enemy, playerActiveArea)){
                enemiesInActiveArea.add(enemy);
                // LastTry.debug("Enemy in active area of: "+enemy.getName());
            }
        });

        return enemiesInActiveArea;
    }
}