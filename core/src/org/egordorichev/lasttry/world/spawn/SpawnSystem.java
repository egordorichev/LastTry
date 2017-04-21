package org.egordorichev.lasttry.world.spawn;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemies;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.AdvancedRectangle;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.GenericContainer;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.spawn.components.Area;
import org.egordorichev.lasttry.world.spawn.components.EnemySpawn;
import org.egordorichev.lasttry.world.spawn.components.GridCalculations;
import org.egordorichev.lasttry.world.spawn.components.SpawnRate;

import java.util.ArrayList;
import java.util.List;

/**
 * Spawn system that will spawn monsters in the gameworld based on certain rules.
 * Created by LogoTie on 17/04/2017.
 */
public class SpawnSystem {
    private  Biome biome;
    private int spawnRate;
	private int maxSpawns;
    private int diffBetweenSpawnedAndMaxSpawns;
    private List<Enemy> activeEnemyEntities = new ArrayList<>();
    private int spawnWeightOfCurrentlyActiveEnemies;
    private Area activeAreaOfPlayer;

    public void update() {
        if(LastTry.environment.currentBiome.get() == null){
            return;
        }

        this.biome = LastTry.environment.currentBiome.get(); // Get user biome

        spawnTriggered();
    }

    private  void spawnTriggered() {

        final int maxSpawns = this.biome.getSpawnMax();

        final int origSpawnRate = this.biome.getSpawnRate();

        ArrayList<Enemy> enemiesInActiveArea = this.generateEnemiesInActiveArea();

        //Calculate if any enemy is less than or equal to the remaining max space of the biome
        final boolean spaceForNewEnemy = this.ableToSpawnNewEnemy(maxSpawns, enemiesInActiveArea);

        if(!spaceForNewEnemy){
            return;
        }

        //Calculate spawn rate based on certain rules.
        final float spawnRateFinal = this.calculateFinalSpawnRate(origSpawnRate);

        if (!EnemySpawn.shouldEnemySpawn(spawnRateFinal)) {
            return;
        }

        ArrayList<Enemy> eligibleEnemiesForSpawn = EnemySpawn.retrieveEligibleSpawnEnemies(diffBetweenSpawnedAndMaxSpawns);

        if (eligibleEnemiesForSpawn.size() == 0) {
            return;
        }

        Enemy enemyToBeSpawned = EnemySpawn.retrieveRandomEnemy(eligibleEnemiesForSpawn);
        this.spawnEnemy(enemyToBeSpawned);
    }

    private float calculateFinalSpawnRate(int origSpawnRate) {
        // Spawn rate is modified based on different factors such as time, events occurring
        return SpawnRate.calculateSpawnRate(origSpawnRate, spawnWeightOfCurrentlyActiveEnemies, maxSpawns);
    }

    private void spawnEnemy(Enemy enemy) {
        GenericContainer.Pair<Integer> suitableXySpawnPoint = EnemySpawn.generateEligibleSpawnPoint(activeAreaOfPlayer);
        LastTry.entityManager.spawnEnemy((short)enemy.getID(), suitableXySpawnPoint.getFirst(), suitableXySpawnPoint.getSecond());
    }

    private ArrayList<Enemy> generateEnemiesInActiveArea() {
        // Must clear the list each time, as it has no way of knowing if an entity has died so we must rebuild
        // each time to ensure we have an up to date list
        ArrayList<Enemy> enemiesInActiveArea = new ArrayList<>();
        activeAreaOfPlayer= GridCalculations.generateActiveArea();
        List<Enemy> enemyEntities = LastTry.entityManager.retrieveEnemyEntities();

        enemyEntities.stream().forEach(enemy -> {

            // TODO Rethink
            // Checks if the enemy is in the active area and if the enemy is not already in the list, it adds to the list
            if(EnemySpawn.isEnemyInActiveArea(enemy, activeAreaOfPlayer)){
                enemiesInActiveArea.add(enemy);
                // LastTry.debug("Enemy in active area of: "+enemy.getName());
            }
        });

        return enemiesInActiveArea;
    }
    
    private void calcArea(){
        float xOfPLayer = LastTry.player.physics.getCenterX();
        float yOfPlayer = LastTry.player.physics.getCenterY();

        AdvancedRectangle advancedRectangle = new AdvancedRectangle(xOfPLayer, yOfPlayer, 6);

        if(advancedRectangle.allSidesInBoundary()){
            Log.debug("All sides in boundary");
            // advancedRectangle.debugSetItemsOnPoints();
        }
    }

    private void calculateNonSpawnSafePlayerArea(){
        // TODO Implement
    }

    private boolean ableToSpawnNewEnemy(int maxSpawnsOfBiome, ArrayList<Enemy> enemiesInActiveArea) {
        // TODO Expensive calculation
        // TODO Reached 49 and got stuck as there is no monster with spawn weight of 1, wasting calculations.
        this.spawnWeightOfCurrentlyActiveEnemies = EnemySpawn.calcSpawnWeightOfActiveEnemies(enemiesInActiveArea);

        if(spawnWeightOfCurrentlyActiveEnemies>=maxSpawns){
            return false;
        }

        return true;
    }

}
