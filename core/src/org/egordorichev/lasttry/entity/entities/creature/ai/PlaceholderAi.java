package org.egordorichev.lasttry.entity.entities.creature.ai;

import org.egordorichev.lasttry.entity.entities.creature.Creature;

/**
 * Ai with no Ai at all
 */
public class PlaceholderAi extends Ai {
	public PlaceholderAi() {
		this.register("idle");
	}

	public void idle(Creature creature, int t) {

	}
}