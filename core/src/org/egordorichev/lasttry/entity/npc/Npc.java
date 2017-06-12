package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.ai.AI;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.creature.CreatureGraphicsComponent;
import org.egordorichev.lasttry.entity.creature.CreaturePhysicsComponent;

public class Npc extends CreatureWithAI {
	public Npc(String id) {
		super(id, new CreaturePhysicsComponent(), new CreatureGraphicsComponent(), AI.fromID("lt:npc"));
	}
}