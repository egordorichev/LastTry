package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.components.AiComponent;
import org.egordorichev.lasttry.entity.components.GraphicsComponent;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class CreatureWithAI extends Creature {
	public AiComponent ai = new AiComponent(this);

	public CreatureWithAI(PhysicsComponent physics, GraphicsComponent graphics) {
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