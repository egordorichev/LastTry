package org.egordorichev.lasttry.world.spawn.components;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;

import java.util.ArrayList;

public class EnemySpawn {

    public static ArrayList<Enemy> retrieveEligibleSpawnEnemies(final int availableMaxSpawn) {

        final ArrayList<Enemy> eligibleEnemiesForSpawn = new ArrayList<>();

	    Enemies.preload();

//        Enemy.availEnemies.stream().forEach(enemy -> {
//            if(enemy.canSpawn()&&enemy.getSpawnWeight()<=availableMaxSpawn){
//                eligibleEnemiesForSpawn.add(enemy);
//            }
//        });

        return eligibleEnemiesForSpawn;
    }

    public static boolean shouldEnemySpawn(float spawnRateFloat) {
        float randomNumber = LastTry.random.nextFloat()/100;
        if(spawnRateFloat>randomNumber){
            return true;
        }
        return false;
    }

    public static int calcSpawnWeightOfActiveEnemies(final ArrayList<Enemy> enemiesInActiveArea) {
        return enemiesInActiveArea.stream().mapToInt(enemy->enemy.getSpawnWeight()).sum();
    }

}
