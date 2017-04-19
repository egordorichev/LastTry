package org.egordorichev.lasttry.entity.enemy.enemies;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.enemy.Boss;
import org.egordorichev.lasttry.item.Items;

public class EyeOfCthulhu extends Boss { // TODO
    public EyeOfCthulhu() {
        super(EnemyID.eyeOfCthulhu, "Eye of Cthulhu", LastTry.world.flags.isExpertMode() ? 3640 : 2800);

        /*this.texture = Assets.getTexture(Textures.eyeOfCthulhu);

        this.renderBounds.width = 110;
        this.renderBounds.height = 166;*/

        /*this.hitbox.x = 8;
        this.hitbox.y = 58;
        this.hitbox.width = 94;
        this.hitbox.height = 104;

        this.state = State.FLYING;*/

        this.physics.setSolid(false);
	    this.ai.setMax(1600);

	    this.drops.add(new Drop(Items.goldCoin, 5, 5));
        this.drops.add(new Drop(Items.copperCoin, 15));
        this.drops.add(new Drop(Items.heart, 5, 10));
    }

    @Override
    public void updateAI() {
        // TODO
    }
}
