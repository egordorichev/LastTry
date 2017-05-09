package org.egordorichev.lasttry.entity.ai;

import org.egordorichev.lasttry.entity.CreatureWithAI;

public abstract class AI {
    private short id;

    public AI(short id) {
        if (AIs.AI_CACHE[id] != null) {
            throw new RuntimeException("AI with id " + id + " is already defined!");
        }

        AIs.AI_CACHE[id] = this; // todo
        this.id = id;
    }

    public abstract void init(CreatureWithAI creature);
    public abstract void update(CreatureWithAI creature, int dt, int currentAi);

    public short getId() {
        return this.id;
    }

    public static AI fromID(short id) {
        return AIs.AI_CACHE[id];
    }

    public boolean canSpawn() {
    	return false;
    }
}