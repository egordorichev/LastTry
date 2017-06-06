package org.egordorichev.lasttry.core;

import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.entity.ai.AIs;
import org.egordorichev.lasttry.entity.Creatures;
import org.egordorichev.lasttry.world.biome.Biomes;

public class Bootstrap {
	private static boolean loaded = false;

	public static boolean isLoaded() {
		return loaded;
	}

	public static void load() {
		if (loaded) {
			return;
		}

		loaded = true;

		Biomes.load();
		Items.load();
		AIs.load();
		Creatures.load();
	}
}