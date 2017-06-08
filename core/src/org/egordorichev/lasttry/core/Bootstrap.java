package org.egordorichev.lasttry.core;

import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.entity.ai.AIs;
import org.egordorichev.lasttry.entity.Creatures;
import org.egordorichev.lasttry.world.biome.Biomes;

/** Initializes small systems */
public class Bootstrap {
	private static boolean loaded = false;

	/**
	 * @return Systems are initialisated
	 */
	public static boolean isLoaded() {
		return loaded;
	}

	/**
	 * Loads all services
	 */
	public static void load() {
		if (loaded) {
			return;
		}

		loaded = true;

		Items.load();
		Biomes.load();
		AIs.load();
		Creatures.load();
	}
}