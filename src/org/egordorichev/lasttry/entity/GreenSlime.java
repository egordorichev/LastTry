package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class GreenSlime extends Enemy {
	public GreenSlime() {
		super("Green Slime", 28, 0, 12);

		this.texture = Assets.greenSlimeTexture;


		Animation jumpAnimation = new Animation();

		jumpAnimation.addFrame(this.texture.getSubImage(32, 0, 30, 24), 1);
		jumpAnimation.setLooping(false);

		Animation idleAnimation = new Animation();

		idleAnimation.addFrame(this.texture.getSubImage(0, 0, 32, 24), 300);
		idleAnimation.addFrame(this.texture.getSubImage(32, 0, 30, 24), 300);

		this.animations[State.JUMPING.getId()] = jumpAnimation;
		this.animations[State.IDLE.getId()] = idleAnimation;
		this.animations[State.DEAD.getId()] = null;
		this.animations[State.FALLING.getId()] = jumpAnimation;
		this.animations[State.FLYING.getId()] = null;


		this.renderBounds.width = 32;
		this.renderBounds.height = 24;
	}

	@Override
	public void updateAI() {
		super.updateAI();

		// TODO
	}
}