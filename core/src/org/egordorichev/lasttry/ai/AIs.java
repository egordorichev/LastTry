package org.egordorichev.lasttry.ai;

import org.egordorichev.lasttry.ai.ais.NpcAI;
import org.egordorichev.lasttry.ai.ais.SlimeAI;
import org.egordorichev.lasttry.ai.ais.ZombieAI;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.util.Log;

import java.util.HashMap;

public class AIs {
	public static final HashMap<String, AI> AI_CACHE = new HashMap<>();

	/**
	 * Registers and AI
	 *
	 * @param id AI ID
	 * @param ai AI
	 */
	public static void register(String id, AI ai) {
		AI_CACHE.put(id, ai);
	}

	/**
	 * Registers default AIs
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to load AIs before bootstrap");
		}

		register("lt:none", new AI() {
			@Override
			public void init(CreatureWithAI creature) {

			}

			@Override
			public void update(CreatureWithAI creature, int dt, int currentAi) {

			}
		});

		register("lt:slime", new SlimeAI());
		register("lt:zombie", new ZombieAI());
		register("lt:npc", new NpcAI());
	}
}