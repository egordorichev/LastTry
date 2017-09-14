package org.egordorichev.lasttry.entity.ai;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.ais.SlimeAI;
import org.egordorichev.lasttry.entity.ai.ais.ZombieAI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AIManagerImpl implements AIManager {
    private static final Logger logger = LoggerFactory.getLogger(AIManagerImpl.class);

    public static final AI[] AI_CACHE = new AI[AIID.count];

    public AI none;
    public AI slime;
    public AI zombie;



    public AI get(short id) {
        return AI_CACHE[id];
    }

    @Override
    public void set(short id, AI ai) {
        AI_CACHE[id] = ai;
    }

    public void load() {
        none = new AI(AIID.none) {
            @Override
            public void init(CreatureWithAI creature) {

            }

            @Override
            public void update(CreatureWithAI creature, int dt, int currentAi) {

            }

            @Override
            public boolean canSpawn() {
                return true;
            }
        };

        slime = new SlimeAI();
        zombie = new ZombieAI();
    }
}
