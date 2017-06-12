package org.egordorichev.lasttry.ai.ais;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.ai.AI;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class ZombieAI extends AI {
	@Override
	public void init(CreatureWithAI creature) {
		creature.ai.setMax(3600);
	}

	@Override
	public void update(CreatureWithAI creature, int dt, int currentAi) {
		creature.physics.move((Float.compare(Globals.getPlayer().physics.getCenterX(), creature.physics.getCenterX()) == -1) ? PhysicsComponent.Direction.LEFT : PhysicsComponent.Direction.RIGHT);
	}

	@Override
	public boolean canSpawn() {
		return Globals.environment.time.isNight();
	}
}