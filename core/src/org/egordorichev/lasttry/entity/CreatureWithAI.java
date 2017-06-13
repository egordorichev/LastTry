package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.ai.AI;
import org.egordorichev.lasttry.ai.AiComponent;
import org.egordorichev.lasttry.entity.creature.Creature;
import org.egordorichev.lasttry.entity.creature.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.creature.CreaturePhysicsComponent;

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