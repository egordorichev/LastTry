package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AIs;
import org.egordorichev.lasttry.entity.components.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.components.CreaturePhysicsComponent;

public class Npc extends CreatureWithAI {
	public Npc(String id) {
		super(id, new CreaturePhysicsComponent(), new CreatureGraphicsComponent(), AIs.none);
	}
}