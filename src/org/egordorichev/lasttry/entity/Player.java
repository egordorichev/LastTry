package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.util.Direction;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Input;

public class Player extends Entity {
	protected String name;
	
	public Player(String name) {
		super(100, 0, 0);

		this.name = name;
		this.texture = Assets.playerTexture;

		Animation idleAnimation = new Animation();
		idleAnimation.addFrame(this.texture.getSubImage(0, 0, 32, 48), 1000);
		idleAnimation.setLooping(false);

		Animation movingAnimation = new Animation();
		movingAnimation.addFrame(this.texture.getSubImage(0, 96, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 144, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 192, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 240, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 288, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 336, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 384, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 432, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 480, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 528, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 576, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 624, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 672, 32, 48), 30);
		movingAnimation.addFrame(this.texture.getSubImage(0, 720, 32, 48), 30);

		Animation jumpingAnimation = new Animation();
		jumpingAnimation.addFrame(this.texture.getSubImage(0, 48, 32, 48), 1000); // TODO

		Animation flyingAnimation = new Animation();
		flyingAnimation.addFrame(this.texture.getSubImage(0, 768, 32, 40), 40);

		Animation deadAnimation = new Animation(false);
		deadAnimation.addFrame(this.texture.getSubImage(0, 0, 32, 48), 1000); // TODO
		deadAnimation.setLooping(false);

		this.animations[State.IDLE.getId()] = idleAnimation;
		this.animations[State.MOVING.getId()] = movingAnimation;
		this.animations[State.JUMPING.getId()] = jumpingAnimation;
		this.animations[State.FALLING.getId()] = jumpingAnimation; // They are the same
		this.animations[State.FLYING.getId()] = flyingAnimation;
		this.animations[State.DEAD.getId()] = deadAnimation;
	}

	public void setGhostMode(boolean enabled) {
		this.isSolid = !enabled;

		if(enabled) {
			this.state = State.FLYING;
		} else {
			this.state = State.IDLE;
		}
	}

	@Override
	public void onSpawn() {
		for(int y = 0; y < 3; y++) {
			for(int x = 0; x < 2; x++) {
				LastTry.world.setBlock(null, this.getGridX() + x, this.getGridY() + y);
			}
		}
	}

	@Override
	public void update(int dt) {
		if(!this.shouldUpdate) {
			return;
		}

		if(this.state == State.FLYING) {
			if(LastTry.input.isKeyDown(Input.KEY_SPACE) || LastTry.input.isKeyDown(Input.KEY_W)) {
				this.velocity.y -= 1;
			}

			if(LastTry.input.isKeyDown(Input.KEY_S)) {
				this.velocity.y += 1;
			}

			if(LastTry.input.isKeyDown(Input.KEY_A)) {
				this.move(Direction.LEFT);
			}

			if(LastTry.input.isKeyDown(Input.KEY_D)) {
				this.move(Direction.RIGHT);
			}
		} else {
			if(LastTry.input.isKeyDown(Input.KEY_SPACE)) {
				this.jump();
			}

			if(LastTry.input.isKeyDown(Input.KEY_A)) {
				this.move(Direction.LEFT);
			}

			if(LastTry.input.isKeyDown(Input.KEY_D)) {
				this.move(Direction.RIGHT);
			}
		}

		super.update(dt);
	}

	public String getName() {
		return this.name;
	}	
}
