package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.components.PhysicsComponent;

public class Npc extends CreatureWithAI {
	public Npc() {
		super(new PhysicsComponent(), new NpcGraphicsComponent());
	}

	@Override
	public void updateAI() {
		// TODO
	}
}