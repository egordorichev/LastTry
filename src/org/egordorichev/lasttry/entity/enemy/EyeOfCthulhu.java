package org.egordorichev.lasttry.entity.enemy;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Drop;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class EyeOfCthulhu extends Boss {
	public EyeOfCthulhu() {
		super(EnemyID.eyeOfCthulhu, "Eye of Cthulhu", LastTry.world.isExpert() ? 3640 : 2800);

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


		this.drops.add(new Drop(Item.goldCoin, Drop.Chance.ALWAYS, 5, 5));
		this.drops.add(new Drop(Item.copperCoin, Drop.Chance.ALWAYS, 1, 15));

		this.phases = new Phase[2];

		this.phases[0] = new Phase(this, this.maxHp, LastTry.world.isExpert() ? 30 : 15, 12) {
			@Override
			public void onEnter() {
				Animation flyingAnimation = new Animation();

				flyingAnimation.addFrame(texture.getSubImage(0, 0, 110, 166), 300);
				flyingAnimation.addFrame(texture.getSubImage(0, 166, 110, 166), 300);
				flyingAnimation.addFrame(texture.getSubImage(0, 332, 110, 166), 300);

				animations[State.FLYING.getId()] = flyingAnimation;
			}
		};

		this.phases[1] = new Phase(this, this.maxHp, LastTry.world.isExpert() ? 22 : 45, 0) {
			@Override
			public void onEnter() {
				Animation flyingAnimation = new Animation();

				flyingAnimation.addFrame(texture.getSubImage(0, 498, 110, 166), 300);
				flyingAnimation.addFrame(texture.getSubImage(0, 664, 110, 166), 300);
				flyingAnimation.addFrame(texture.getSubImage(0, 830, 110, 166), 300);

				animations[State.FLYING.getId()] = flyingAnimation;
			}
		};
		
		this.phases[0].enter();
	}

	@Override
	public void updateAI() {
		super.updateAI();

		if(this.currentAi == 0 || this.currentAi == 120 || this.currentAi == 240) {
			// TODO
		}
	}
}
