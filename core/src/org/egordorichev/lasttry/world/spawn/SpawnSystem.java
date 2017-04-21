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
    private int spawnWeightOfCurrentlyActiveEnemies;
    private Area playerActiveArea;

    public void update() {
        if(LastTry.environment.currentBiome.get() == null){
            return;
        }
        this.biome = LastTry.environment.currentBiome.get(); // Get user biome
        this.refreshTriggered();
    }

    private void refreshTriggered() {

        final int maxSpawns = this.biome.getSpawnMax();

        final int origSpawnRate = this.biome.getSpawnRate();

        playerActiveArea = GridCalculations.generateActiveArea();

        ArrayList<Enemy> enemiesInActiveArea = EnemySpawn.generateEnemiesInActiveArea(playerActiveArea);

        //Calculate if any enemy is less than or equal to the remaining max space of the biome
        final boolean spaceForNewEnemy = this.ableToSpawnNewEnemy(maxSpawns, enemiesInActiveArea);

        if(!spaceForNewEnemy){
            return;
        }else{
            this.spawnRequested(origSpawnRate, maxSpawns);
        }
    }

    private void spawnRequested(final int origSpawnRate, final int maxSpawns) {
        //Calculate spawn rate based on certain rules.
        final float spawnRateFinal = SpawnRate.calculateSpawnRate(origSpawnRate, spawnWeightOfCurrentlyActiveEnemies, maxSpawns);

        if (!EnemySpawn.shouldEnemySpawn(spawnRateFinal)) {
            return;
        }

        ArrayList<Enemy> eligibleEnemiesForSpawn = EnemySpawn.retrieveEligibleSpawnEnemies(maxSpawns-spawnWeightOfCurrentlyActiveEnemies);

        if (eligibleEnemiesForSpawn.size() == 0) {
            return;
        }else{
            this.spawnTriggered(eligibleEnemiesForSpawn);
        }
    }

    private void spawnTriggered(final ArrayList<Enemy> eligibleEnemiesForSpawn) {
        Enemy enemyToBeSpawned = EnemySpawn.retrieveRandomEnemy(eligibleEnemiesForSpawn);

        GenericContainer.Pair<Integer> suitableXySpawnPoint = GridCalculations.generateEligibleSpawnPoint(GridCalculations.generateActiveArea());

        LastTry.entityManager.spawnEnemy((short)enemyToBeSpawned.getID(), suitableXySpawnPoint.getFirst(), suitableXySpawnPoint.getSecond());
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

        if(spawnWeightOfCurrentlyActiveEnemies>=maxSpawnsOfBiome){
            return false;
        }

        return true;
    }

}
