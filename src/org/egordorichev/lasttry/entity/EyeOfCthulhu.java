package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class EyeOfCthulhu extends Boss { // TODO: second phase
	public EyeOfCthulhu() {
		super(EntityID.eyeOfCthulhu, "Eye of Cthulhu", 2800, 12, 15);

		this.texture = Assets.eyeOfCthulhuTexture;

		this.renderBounds.width = 110;
		this.renderBounds.height = 166;

		this.maxAi = 600;

		this.hitbox.x = 8;
		this.hitbox.y = 58;
		this.hitbox.width = 94;
		this.hitbox.height = 104;

		this.state = State.FLYING;
		this.isSolid = false;

		Animation flyingAnimation = new Animation();

		this.animations[State.FLYING.getId()] = flyingAnimation;
		flyingAnimation.addFrame(this.texture.getSubImage(0, 0, 110, 166), 300);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 166, 110, 166), 300);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 332, 110, 166), 300);

		this.drop.add(new Drop(ItemID.goldCoin, Drop.Chance.ALWAYS, 5, 5));
	}

	@Override
	public void updateAI() {
		super.updateAI();

		if(this.currentAi == 0 || this.currentAi == 120 || this.currentAi == 240) {
			// TODO
		}
	}
}