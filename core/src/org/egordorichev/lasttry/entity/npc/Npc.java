package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;

public class Npc extends CreatureWithAI {
	public Npc() {
		super(new CreaturePhysicsComponent(), new NpcGraphicsComponent());
	}

	@Override
	public void updateAI() {
		// TODO
	}
}