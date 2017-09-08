package org.egordorichev.lasttry.entity.ai;

import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.ais.SlimeAI;
import org.egordorichev.lasttry.entity.ai.ais.ZombieAI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AIs {
    private static final Logger logger = LoggerFactory.getLogger(AIs.class);

    public static final AI[] AI_CACHE = new AI[AIID.count];

    public static AI none;
    public static AI slime;
    public static AI zombie;

    static {
        if (!Bootstrap.isLoaded()) {
            logger.error("Trying to access ais class before bootstrap");
        }

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

    public static void load() {

    }
}