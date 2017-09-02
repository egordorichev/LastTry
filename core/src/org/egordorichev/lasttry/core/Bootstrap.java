package org.egordorichev.lasttry.core;

import org.egordorichev.lasttry.crafting.Recipes;
import org.egordorichev.lasttry.effect.Effects;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.util.Files;
import org.egordorichev.lasttry.entity.ai.AIs;
import org.egordorichev.lasttry.entity.Creatures;
import org.egordorichev.lasttry.world.biome.Biomes;

import java.io.File;

/**
 * Initializes small systems
 */
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

		try {
			File file = new File(Files.getDir());

			if (!file.exists() || !file.isDirectory()) {
				file.createNewFile();
			}
		} catch (Exception exception) {
			throw new RuntimeException("Couldn't open save directory. Aborting.");
		}

		loaded = true;

		Items.load();
		Recipes.load();
		Biomes.load();
		Effects.load();
		AIs.load();
		Creatures.load();
	}
}