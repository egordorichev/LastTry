package org.egordorichev.lasttry.entity.entities.world.biome;

import org.egordorichev.lasttry.entity.component.Component;

import java.util.HashMap;

/**
 * Stores info about blocks, that represent the biome
 */
public class BiomeIndexComponent extends Component {
	/**
	 * Blocks, that show the BiomeSystem, that this biome is present
	 */
	public HashMap<String[], Integer> needed = new HashMap<>();
}