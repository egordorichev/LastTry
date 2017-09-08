package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AIs;

public class Npc extends CreatureWithAI {
	public Npc(String id) {
		super(id, AIs.none);
	}
}