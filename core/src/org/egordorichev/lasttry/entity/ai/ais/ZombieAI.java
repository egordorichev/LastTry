package org.egordorichev.lasttry.entity.ai.ais;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.ai.AIID;

public class ZombieAI extends AI {
	public ZombieAI() {
		super(AIID.zombie);
	}

	@Override
	public void init(CreatureWithAI creature) {
		creature.ai.setMax(3600);
	}

	@Override
	public void update(CreatureWithAI creature, int dt, int currentAi) {
		// TODO
	}
}