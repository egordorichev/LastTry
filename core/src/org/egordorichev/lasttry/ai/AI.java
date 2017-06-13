package org.egordorichev.lasttry.ai;

import org.egordorichev.lasttry.entity.CreatureWithAI;

public abstract class AI {
	/**
	 * Returns AI with given id
	 *
	 * @param id AI ID
	 * @return AI with given id or null, if it is not found
	 */
	public static AI fromID(String id) {
		return AIs.AI_CACHE.get(id);
	}

	public abstract void init(CreatureWithAI creature);

	public abstract void update(CreatureWithAI creature, int dt, int currentAi);

	public boolean canSpawn() {
		return false;
	}
}