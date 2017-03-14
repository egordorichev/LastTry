package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class EyeOfCthulhu extends Boss {
	public EyeOfCthulhu() {
		super(EntityID.eyeOfCthulhu, "Eye of Cthulhu", LastTry.world.isExpert() ? 3640 : 2800);

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

		flyingAnimation.addFrame(this.texture.getSubImage(0, 0, 110, 166), 300);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 166, 110, 166), 300);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 332, 110, 166), 300);

		this.animations[State.FLYING.getId()] = flyingAnimation;

		this.drop.add(new Drop(Item.goldCoin, Drop.Chance.ALWAYS, 5, 5));
		this.drop.add(new Drop(Item.copperCoin, Drop.Chance.ALWAYS, 1, 15));

		this.phases = new Phase[2];
		this.phases[0] = new Phase(this, this.maxHp, LastTry.world.isExpert() ? 30 : 15, 12) {
			@Override
			public void onEnter() {
				Animation flyingAnimation = new Animation();

				flyingAnimation.addFrame(texture.getSubImage(0, 498, 110, 166), 300);
				flyingAnimation.addFrame(texture.getSubImage(0, 664, 110, 166), 300);
				flyingAnimation.addFrame(texture.getSubImage(0, 830, 110, 166), 300);

				animations[State.FLYING.getId()] = flyingAnimation;
			}
		};
	}

	@Override
	public void updateAI() {
		super.updateAI();

		if(this.currentAi == 0 || this.currentAi == 120 || this.currentAi == 240) {
			// TODO
		}
	}
}