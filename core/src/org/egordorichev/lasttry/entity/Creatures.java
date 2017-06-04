package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.util.Log;

import java.util.HashMap;

public class Creatures {
	public static HashMap<String, CreatureInfo> CREATURE_CACHE = new HashMap<>();

	public static void load() {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to access enemies class before bootstrap");
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
					Log.error("Failed to parse " + creature.name());
					exception.printStackTrace();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load creatures");
		}
	}

	public static Creature create(String name) {
		CreatureInfo creature = CREATURE_CACHE.get(name);

		if (creature == null) {
			Log.warn("Creature with name " + name + " does not exist.");
			return null;
		}

		return creature.create();
	}

	public static boolean canSpawn(String name, int availableMaxSpawn) {
		CreatureInfo creature = CREATURE_CACHE.get(name);

		if (creature == null) {
			return false;
		}

		return creature.ai.canSpawn() && creature.spawnWeight <= availableMaxSpawn;
	}
}