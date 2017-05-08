package org.egordorichev.lasttry.entity.ai;

import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.ais.*;
import org.egordorichev.lasttry.util.Log;

public class AIs {
    public static final AI[] AI_CACHE = new AI[AIID.count];

    public static AI none;
    public static AI slime;

    static {
        if (!Bootstrap.isLoaded()) {
            Log.error("Trying to access ais class before bootstrap");
        }

        none = new AI(AIID.none) {
	        @Override
	        public void init(CreatureWithAI creature) {
		        
	        }

	        @Override
            public void update(CreatureWithAI creature, int dt, int currentAi) {

            }
        };

        slime = new SlimeAI();
    }

    public static void load() {

    }
}