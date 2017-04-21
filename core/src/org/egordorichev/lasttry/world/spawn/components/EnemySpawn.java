package org.egordorichev.lasttry.world.spawn.components;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.GenericContainer;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class EnemySpawn {

    //availableMaxSpawn is an int denoting the remaining spawn value allowed for a new spawn in the biome
    public static ArrayList<Enemy> retrieveEligibleSpawnEnemies(final int availableMaxSpawn) {
        final ArrayList<Enemy> eligibleEnemiesForSpawn = new ArrayList<>();
        Enemies.preload();
	    Enemies.ENEMY_CACHE.keySet().stream().forEach(enemyKey -> {
	        Enemy enemy = Enemies.create(enemyKey);
	        if(enemy.canSpawn()&&enemy.getSpawnWeight()<=availableMaxSpawn){
	            eligibleEnemiesForSpawn.add(enemy);
            }
        });
        return eligibleEnemiesForSpawn;
    }

    public static boolean shouldEnemySpawn(float spawnRateFloat) {
        //Spawn rate is calculated as '1' in 'spawnRate'
        float randomNumber = LastTry.random.nextFloat()/100;

        //if spawn rate is less than random number, an enemy can spawn this tick
        if(spawnRateFloat>randomNumber){
            return true;
        }
        return false;
    }

    public static int calcSpawnWeightOfActiveEnemies(final ArrayList<Enemy> enemiesInActiveArea) {
        return enemiesInActiveArea.stream().mapToInt(enemy->enemy.getSpawnWeight()).sum();
    }

    public static Enemy retrieveRandomEnemy(ArrayList<Enemy> eligibleEnemiesForSpawning) {
        int randomIndex = LastTry.random.nextInt(eligibleEnemiesForSpawning.size());
        return eligibleEnemiesForSpawning.get(randomIndex);
    }

    public static GenericContainer.Pair<Integer> generateEligibleSpawnPoint(Area enemySpawnArea) {

        // Generate inside the active zone
        int xGridSpawnPoint = enemySpawnArea.getMaxXPointActiveZone()-30;
        int yGridSpawnPoint = enemySpawnArea.getMinYPoint();

        int xPixelSpawnPoint = xGridSpawnPoint* Block.SIZE;
        int yPixelSpawnPoint = yGridSpawnPoint * Block.SIZE;

        GenericContainer.Pair<Integer> xyPoint = new GenericContainer.Pair<>();
        xyPoint.set(xPixelSpawnPoint, yPixelSpawnPoint);

        return xyPoint;
    }

    public static boolean isEnemyInActiveArea(Enemy enemy, Area area) {

        // Get block co ordinates of enemy
        int enemyBlockGridX = enemy.physics.getGridX();
        int enemyBlockGridY = enemy.physics.getGridY();

        // TODO Change on inversion of y axis
        if(enemyBlockGridX>=area.getMinXPoint()&&enemyBlockGridX<=area.getMaxXPoint()){
            if(enemyBlockGridY<=area.getMaxYPoint()&&enemyBlockGridY>=area.getMinYPoint()){
                return true;
            }
        }

        return false;
    }
}
