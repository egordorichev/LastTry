package org.egordorichev.lasttry.entity.ai;

import org.egordorichev.lasttry.entity.CreatureWithAI;

public abstract class AI {
    public void AI() {

    }

    public abstract void update(CreatureWithAI creature, int dt, int currentAi);

    public static AI fromID(short id) {
        return AIs.AI_CACHE[id];
    }
}