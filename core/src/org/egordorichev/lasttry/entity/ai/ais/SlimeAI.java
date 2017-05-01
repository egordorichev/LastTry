package org.egordorichev.lasttry.entity.ai.ais;

import org.egordorichev.lasttry.entity.CreatureWithAI;
import org.egordorichev.lasttry.entity.ai.AI;
import org.egordorichev.lasttry.entity.ai.AIID;

public class SlimeAI extends AI {
    public SlimeAI() {
        super(AIID.slime);
    }

    @Override
    public void update(CreatureWithAI creature, int dt, int currentAi) {

    }
}