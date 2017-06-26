package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.util.Log;
import java.util.HashMap;

public class Creatures {
	public static HashMap<String, CreatureInfo> CREATURE_CACHE = new HashMap<>();

	/**
	 * Loads all creatures from json
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to load enemies before bootstrap");
			return;
		}

		CREATURE_CACHE.clear();

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(new FileHandle(System.getProperty("user.home") + "/.LastTry/data/creatures.json"));

			for (JsonValue creature : root) {
				try {
					CREATURE_CACHE.put(creature.name(), new CreatureInfo(creature, creature.name()));
				} catch (Exception exception) {
					Log.error("Failed to parse " + creature.name());
					exception.printStackTrace();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load creatures");
		}
	}

	/**
	 * Creates new creature with given name
	 *
	 * @param name Creature name
	 * @return New creature or null, if it is not found
	 */
	public static Creature create(String name) {
		CreatureInfo creature = CREATURE_CACHE.get(name);

		if (creature == null) {
			Log.warn("Creature with name " + name + " does not exist.");
			return null;
		}

		return creature.create();
	}

	/**
	 * Returns true, if creature with given name can spawn
	 *
	 * @param name Creature name
	 * @param availableMaxSpawn Left spawn value
	 * @return If creature with given name can spawn
	 */
	public static boolean canSpawn(String name, int availableMaxSpawn) {
		CreatureInfo creature = CREATURE_CACHE.get(name);

		if (creature == null) {
			return false;
		}

		return creature.ai.canSpawn() && creature.spawnWeight <= availableMaxSpawn;
	}
}