package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.components.AiComponent;

public class CreatureWithAI extends Creature {
	public AiComponent ai;

	public CreatureWithAI(String id, AI ai) {
		super(id);
		this.ai = new AiComponent(this, ai);
		this.ai.ai.init(this);
	}

	@Override
	public void update(int dt) {
		super.update(dt);
		this.ai.update(dt);
	}
}