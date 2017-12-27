package org.egordorichev.lasttry.entity.entities.world.biome;

import org.egordorichev.lasttry.entity.component.Component;

import java.util.HashMap;

/**
 * Stores info about spawns in a biome
 */
public class BiomeSpawnComponent extends Component {
	/**
	 * Maximum spawn in this biome
	 */
	public int maxSpawns;
	/**
	 * Keeps info about spawn rate
	 */
	public HashMap<String, Float> spawnRate = new HashMap<>();
}