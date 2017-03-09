package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class Player extends Entity {
	public Player(String name) {
		super(name, true, 100, 0, 0);

		this.image = Assets.playerTexture;

		Animation idleAnimation = new Animation();
		idleAnimation.addFrame(this.image.getSubImage(0, 0, 32, 48), 1000);
		idleAnimation.setLooping(false);

		Animation movingAnimation = new Animation();
		movingAnimation.addFrame(this.image.getSubImage(0, 48, 32, 48), 300); // TODO

		Animation jumpingAnimation = new Animation();
		jumpingAnimation.addFrame(this.image.getSubImage(0, 0, 32, 48), 1000); // TODO

		Animation deadAnimation = new Animation(false);
		deadAnimation.addFrame(this.image.getSubImage(0, 0, 32, 48), 1000); // TODO
		deadAnimation.setLooping(false);

		this.animations[State.IDLE.getId()] = idleAnimation;
		this.animations[State.MOVING.getId()] = movingAnimation;
		this.animations[State.JUMPING.getId()] = jumpingAnimation;
		this.animations[State.FALLING.getId()] = jumpingAnimation; // They are the same
		this.animations[State.FLYING.getId()] = null; // Player can't fly
		this.animations[State.DEAD.getId()] = deadAnimation;
	}

	@Override
	public void onSpawn() {
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 2; x++) {
				LastTry.world.setBlock(null, this.getGridX() + x, this.getGridY() + y);
			}
		}
	}
}