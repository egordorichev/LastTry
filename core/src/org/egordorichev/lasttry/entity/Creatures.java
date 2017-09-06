package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Creatures {
	private static final Logger logger = LoggerFactory.getLogger(Creatures.class);

	public static HashMap<String, CreatureInfo> CREATURE_CACHE = new HashMap<>();

	/**
	 * Loads all creatures from json
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			logger.error("Trying to load enemies before bootstrap");
			return;
		}

		CREATURE_CACHE.clear();

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(Gdx.files.internal("data/creatures.json"));

			for (JsonValue creature : root) {
				try {
					CREATURE_CACHE.put(creature.name(), new CreatureInfo(creature, creature.name()));
				} catch (Exception exception) {
					logger.error("Failed to parse " + creature.name());
					exception.printStackTrace();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Failed to load creatures");
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
			logger.warn("Creature with name " + name + " does not exist.");
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