package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.*;

public class CreatureWithAI extends Creature {
	public AiComponent ai = new AiComponent(this);

	public CreatureWithAI(CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics) {
		super(physics, graphics);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.ai.update(dt);
	}

	public void updateAI() {

	}
}