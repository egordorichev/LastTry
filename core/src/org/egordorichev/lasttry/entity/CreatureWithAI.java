package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.AiComponent;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;

public class CreatureWithAI extends Creature {
	public AiComponent ai;

	public CreatureWithAI(String id, CreaturePhysicsComponent physics, CreatureGraphicsComponent graphics, AI ai) {
		super(id, physics, graphics);

		this.ai = new AiComponent(this, ai);
		this.ai.ai.init(this);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.ai.update(dt);
	}
}