package org.egordorichev.lasttry.entity.entities.world.generator;

import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.generator.generators.ForestGenerator;

import java.util.Objects;

/**
 * Generates worlds
 */
public class WorldGenerator {
	/**
	 * New world name
	 */
	protected String name;
	/**
	 * The world, that is being generated
	 */
	protected World world;

	public WorldGenerator(String name) {
		this.name = name;
	}

	/**
	 * Generates a new world
	 *
	 * @return New world
	 */
	public World generate() {
		this.world = new World(this.name, "forest");

		// Generate all here

		return this.world;
	}

	public static WorldGenerator forType(String name, String type) {
		if (Objects.equals(type, "forest")) {
			return new ForestGenerator(name);
		}

		return new WorldGenerator(name);
	}
}