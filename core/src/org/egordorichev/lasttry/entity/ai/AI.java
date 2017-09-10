package org.egordorichev.lasttry.entity.ai;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.injection.InjectionHelper;

public abstract class AI {
    private short id;
    private final AIManager aiManager;


    public AI(short id) {
        aiManager = CoreRegistry.get(AIManager.class);
        if (aiManager.get(id) != null) {
            throw new RuntimeException("AI with id " + id + " is already defined!");
        }

        aiManager.set(id,this);
        this.id = id;
    }

    public abstract void init(CreatureWithAI creature);
    public abstract void update(CreatureWithAI creature, int dt, int currentAi);

    public short getId() {
        return this.id;
    }

    public AI fromID(short id) {
        return aiManager.get(id);
    }

    public boolean canSpawn() {
    	return false;
    }
}