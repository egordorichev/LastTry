package org.egordorichev.lasttry.ai.ais;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.ai.AI;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;
import org.egordorichev.lasttry.entity.creature.CreatureStateComponent;
import org.egordorichev.lasttry.world.biome.Biomes;

public class SlimeAI extends AI {
	private static final int MAX = 360;

	@Override
	public void init(CreatureWithAI creature) {
		creature.ai.setMax(MAX);
	}

	@Override
	public void update(CreatureWithAI creature, int dt, int currentAi) {
		if (currentAi == 0) {
			if (creature.physics.getVelocity().y == 0) {
				creature.physics.jump();
			}

			creature.ai.setMax((int) ((MAX / 2) + (Math.random() * MAX / 2)));

			int dir = Float.compare(Globals.getPlayer().physics.getCenterX(), creature.physics.getCenterX());

			if (dir < 0) {
				creature.ai.setData((byte) 0);
			} else if (dir > 0) {
				creature.ai.setData((byte) 1);
			}
		}

		if (creature.state.get() == CreatureStateComponent.State.JUMPING || creature.state.get() == CreatureStateComponent.State.FALLING) {
			creature.physics.move((creature.ai.getData() == 0) ? PhysicsComponent.Direction.LEFT : PhysicsComponent.Direction.RIGHT);
		}
	}

	@Override
	public boolean canSpawn() {
		return Globals.environment.time.isDay() && Globals.environment.currentBiome == Biomes.get("lt:forest");
	}
}