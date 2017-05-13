package org.egordorichev.lasttry.world.spawn;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.enemy.Enemy;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.GenericContainer;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.spawn.components.CircleAreaComponent;
import org.egordorichev.lasttry.world.spawn.components.EnemySpawnComponent;
import org.egordorichev.lasttry.world.spawn.components.GridComponent;
import org.egordorichev.lasttry.world.spawn.components.SpawnRateComponent;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Spawn system that will spawn monsters in the gameworld based on certain rules.
 * Created by LogoTie on 17/04/2017.
 */
public class SpawnSystem {
    private  Biome biome;
    private int spawnWeightOfCurrentlyActiveEnemies;
    private CircleAreaComponent playerActiveArea;
    private int enemiesInActiveAreaCount;

    public void update() {
        if(Globals.environment.currentBiome == null){
            return;
        }
        this.biome = Globals.environment.currentBiome; // Get user biome
        this.refreshTriggered();
    }

    //The following three methods are exposed for the debugger.
    public int getSpawnWeightOfCurrentlyActiveEnemies() {
        return spawnWeightOfCurrentlyActiveEnemies;
    }

    public int getEnemiesInActiveAreaCount() {
        return enemiesInActiveAreaCount;
    }

    public int getRemainingSpawnWeightOfBiome() {
        return biome.getSpawnMax() - spawnWeightOfCurrentlyActiveEnemies;
    }

    private void refreshTriggered() {

        final int maxSpawns = this.biome.getSpawnMax();

        final int origSpawnRate = this.biome.getSpawnRate();

        playerActiveArea = GridComponent.retrieveActiveAreaCircle(Globals.environment.time);

        ArrayList<Enemy> enemiesInActiveArea = EnemySpawnComponent.generateEnemiesInActiveArea(playerActiveArea);

        enemiesInActiveAreaCount = enemiesInActiveArea.size();

        //Calculate if any enemy is less than or equal to the remaining max space of the biome
        final boolean spaceForNewEnemy = this.ableToSpawnNewEnemy(maxSpawns, enemiesInActiveArea);

        if(spaceForNewEnemy){
            this.spawnRequested(origSpawnRate, maxSpawns);
        }else{
            return;
        }
    }

    private void spawnRequested(final int origSpawnRate, final int maxSpawns) {
        //Calculate spawn rate based on certain rules.
        final float spawnRateFinal = SpawnRateComponent.calculateSpawnRate(origSpawnRate, spawnWeightOfCurrentlyActiveEnemies, maxSpawns);

        if (!EnemySpawnComponent.shouldEnemySpawn(spawnRateFinal)) {
            return;
        }

        ArrayList<Enemy> eligibleEnemiesForSpawn = EnemySpawnComponent.retrieveEligibleSpawnEnemies(maxSpawns-spawnWeightOfCurrentlyActiveEnemies);

        if (eligibleEnemiesForSpawn.size() == 0) {
            return;
        }else{
            this.spawnTriggered(eligibleEnemiesForSpawn);
        }
    }

    private void spawnTriggered(final ArrayList<Enemy> eligibleEnemiesForSpawn) {
        Enemy enemyToBeSpawned = EnemySpawnComponent.retrieveRandomEnemy(eligibleEnemiesForSpawn);

        Optional<GenericContainer.Pair<Integer>> optionalSuitableXySpawnPoint = GridComponent.generateEligibleEnemySpawnPoint(playerActiveArea);

        if(optionalSuitableXySpawnPoint.isPresent()){
            int xEnemySpawnPoint = optionalSuitableXySpawnPoint.get().getFirst();
            int yEnemySpawnPoint = optionalSuitableXySpawnPoint.get().getSecond();

            Globals.entityManager.spawnEnemy(enemyToBeSpawned.getName(), xEnemySpawnPoint * Block.SIZE, yEnemySpawnPoint *Block.SIZE);
            LastTry.debug.print("Spawn has been triggered");
        }else{
            LastTry.debug.print("Enemy eligible spawn counter expired, unable to find suitable point to spawn enemy");

            return;
        }

    }

    private boolean ableToSpawnNewEnemy(int maxSpawnsOfBiome, ArrayList<Enemy> enemiesInActiveArea) {
        // TODO Expensive calculation
        // TODO Reached 49 and got stuck as there is no monster with spawn weight of 1, wasting calculations.
        this.spawnWeightOfCurrentlyActiveEnemies = EnemySpawnComponent.calcSpawnWeightOfActiveEnemies(enemiesInActiveArea);

        if(spawnWeightOfCurrentlyActiveEnemies>=maxSpawnsOfBiome){
            return false;
        }

        return true;
    }

}
