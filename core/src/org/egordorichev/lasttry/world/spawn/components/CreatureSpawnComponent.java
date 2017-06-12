package org.egordorichev.lasttry.world.spawn.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Creatures;
import org.egordorichev.lasttry.entity.creature.Creature;

import java.util.ArrayList;
import java.util.List;

public class CreatureSpawnComponent {
	// availableMaxSpawn is an int denoting the remaining spawn value allowed for a new spawn in the biome
	public static List<String> retrieveEligibleSpawnCreatures(final int availableMaxSpawn) {
		final List<String> eligibleCreaturesForSpawn = new ArrayList<>();

		Creatures.CREATURE_CACHE.keySet().stream().forEach(creatureKey -> {
			if (Creatures.canSpawn(creatureKey, availableMaxSpawn)) {
				eligibleCreaturesForSpawn.add(creatureKey);
			}
		});

		return eligibleCreaturesForSpawn;
	}

	public static boolean shouldCreatureSpawn(float spawnRateFloat) {
		// Spawn rate is calculated as '1' in 'spawnRate'
		float randomNumber = LastTry.random.nextFloat() / 100;

		// if spawn rate is less than random number, an creature can spawn this tick
		if (spawnRateFloat > randomNumber) {
			return true;
		}

		return false;
	}

	public static int calcSpawnWeightOfActiveEnemies(final List<Creature> enemiesInActiveArea) {
		return enemiesInActiveArea.stream().mapToInt(creature -> creature.getSpawnWeight()).sum();
	}

	public static String retrieveRandomCreature(List<String> eligibleCreaturesForSpawning) {
		int randomIndex = LastTry.random.nextInt(eligibleCreaturesForSpawning.size());
		return eligibleCreaturesForSpawning.get(randomIndex);
	}

	public static List<Creature> generateEnemiesInActiveArea(CircleAreaComponent playerActiveArea) {
		// Must clear the list each time, as it has no way of knowing if an entity has died so we must rebuild
		// each time to ensure we have an up to date list
		List<Creature> enemiesInActiveArea = new ArrayList<>();
		List<Creature> creatureEntities = Globals.entityManager.getCreatureEntities();

		creatureEntities.stream().forEach(creature -> {
			// TODO Rethink
			// Checks if the creature is in the active area and if the creature is not already in the list, it adds to the list
			if (GridComponent.isCreatureInPlayerActiveArea(creature, playerActiveArea)) {
				enemiesInActiveArea.add(creature);
				// LastTry.debug("Creature in active area of: "+creature.getName());
			}
		});

		return enemiesInActiveArea;
	}
}