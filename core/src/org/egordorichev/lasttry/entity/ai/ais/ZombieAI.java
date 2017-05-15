package org.egordorichev.lasttry.entity.ai.ais;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.ai.AIID;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

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
		creature.physics.move((Float.compare(Globals.player.physics.getCenterX(), creature.physics.getCenterX()) == -1) ? PhysicsComponent.Direction.LEFT : PhysicsComponent.Direction.RIGHT);
	}

	@Override
	public boolean canSpawn() {
		return Globals.environment.time.isNight();
	}
}