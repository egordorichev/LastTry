package org.egordorichev.lasttry.entity.npc;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AIID;
import org.egordorichev.lasttry.entity.ai.AIManager;
import org.egordorichev.lasttry.injection.InjectionHelper;

public class Npc extends CreatureWithAI {
	public Npc(String id) {
		super(id, InjectionHelper.getInstance(AIManager.class).get(AIID.none));
	}
}