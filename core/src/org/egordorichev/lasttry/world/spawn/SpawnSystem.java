package org.egordorichev.lasttry.world.spawn;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creature;
import org.egordorichev.lasttry.entity.Creatures;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.GenericContainer;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.spawn.components.CircleAreaComponent;
import org.egordorichev.lasttry.world.spawn.components.CreatureSpawnComponent;
import org.egordorichev.lasttry.world.spawn.components.GridComponent;
import org.egordorichev.lasttry.world.spawn.components.SpawnRateComponent;

import java.util.ArrayList;
import java.util.List;
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

		ArrayList<Creature> creaturesInActiveArea = CreatureSpawnComponent.generateEnemiesInActiveArea(playerActiveArea);

		enemiesInActiveAreaCount = creaturesInActiveArea.size();

		//Calculate if any enemy is less than or equal to the remaining max space of the biome
		final boolean spaceForNewEnemy = this.ableToSpawnNewEnemy(maxSpawns, creaturesInActiveArea);

		if(spaceForNewEnemy){
			this.spawnRequested(origSpawnRate, maxSpawns);
		}else{
			return;
		}
	}

	private void spawnRequested(final int origSpawnRate, final int maxSpawns) {
		//Calculate spawn rate based on certain rules.
		final float spawnRateFinal = SpawnRateComponent.calculateSpawnRate(origSpawnRate, spawnWeightOfCurrentlyActiveEnemies, maxSpawns);

		if (!CreatureSpawnComponent.shouldCreatureSpawn(spawnRateFinal)) {
			return;
		}

		List<String> eligibleCreaturesForSpawn = CreatureSpawnComponent.retrieveEligibleSpawnCreatures(maxSpawns-spawnWeightOfCurrentlyActiveEnemies);

		if (eligibleCreaturesForSpawn.size() == 0) {
			return;
		}else{
			this.spawnTriggered(eligibleCreaturesForSpawn);
		}
	}

	private void spawnTriggered(final List<String> eligibleCreaturesForSpawn) {
		Creature creatureToBeSpawned = Creatures.create(CreatureSpawnComponent.retrieveRandomCreature(eligibleCreaturesForSpawn));
		Optional<GenericContainer.Pair<Integer>> optionalSuitableXySpawnPoint = GridComponent.generateEligibleEnemySpawnPoint(playerActiveArea);

		if(optionalSuitableXySpawnPoint.isPresent()){
			int xEnemySpawnPoint = optionalSuitableXySpawnPoint.get().getFirst();
			int yEnemySpawnPoint = optionalSuitableXySpawnPoint.get().getSecond();

			Globals.entityManager.spawn(creatureToBeSpawned, xEnemySpawnPoint * Block.SIZE, yEnemySpawnPoint * Block.SIZE);
			LastTry.debug.print("Spawn has been triggered");
		}else{
			LastTry.debug.print("Enemy eligible spawn counter expired, unable to find suitable point to spawn enemy");
			return;
		}

	}

	private boolean ableToSpawnNewEnemy(int maxSpawnsOfBiome, List<Creature> enemiesInActiveArea) {
		// TODO Expensive calculation
		// TODO Reached 49 and got stuck as there is no monster with spawn weight of 1, wasting calculations.

		this.spawnWeightOfCurrentlyActiveEnemies = CreatureSpawnComponent.calcSpawnWeightOfActiveEnemies(enemiesInActiveArea);

		if (spawnWeightOfCurrentlyActiveEnemies >= maxSpawnsOfBiome) {
			return false;
		}

		return true;
	}

}
