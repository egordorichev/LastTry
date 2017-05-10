package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AIs;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;

public class Npc extends CreatureWithAI {
	public Npc() {
		super(new CreaturePhysicsComponent(), new NpcGraphicsComponent(), AIs.none); // todo: npc ai
	}
}