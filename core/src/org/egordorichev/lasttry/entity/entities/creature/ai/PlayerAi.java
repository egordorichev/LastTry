package org.egordorichev.lasttry.entity.entities.creature.ai;

import org.egordorichev.lasttry.entity.entities.creature.Creature;

public class PlayerAi extends Ai {
	public PlayerAi() {
		this.registerAll("idle", "walking", "flying");
	}

	public void idle(Creature self, int t) {
		if (t > 10) {
			self.become("walking");
		}
	}

	public void walking(Creature self, int t) {

	}

	public void flying(Creature self, int t) {

	}
}