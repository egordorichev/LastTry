package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.util.Direction;
import org.newdawn.slick.Animation;

public class Slime extends Enemy {
	/**
	 * The next AI tick to jump at.
	 */
	protected int nextJump;
	/**
	 * Marker for if the slime has jumped this AI cycle.
	 */
	protected boolean canJump;

	public Slime(String name, Image texture) {
		super(name, 28, 0, 12);

		this.maxAi = 360;
		this.updateJumpDelay();
		this.texture = texture;

		Animation jumpAnimation = new Animation();

		jumpAnimation.addFrame(this.texture.getSubImage(32, 0, 32, 24), 1);
		jumpAnimation.setLooping(false);

		Animation idleAnimation = new Animation();

		idleAnimation.addFrame(this.texture.getSubImage(0, 0, 32, 24), 200);
		idleAnimation.addFrame(this.texture.getSubImage(32, 0, 32, 24), 200);

		this.animations[State.JUMPING.getId()] = jumpAnimation;
		this.animations[State.MOVING.getId()] = jumpAnimation;
		this.animations[State.IDLE.getId()] = idleAnimation;
		this.animations[State.DEAD.getId()] = null;
		this.animations[State.FALLING.getId()] = jumpAnimation;
		this.animations[State.FLYING.getId()] = null;

		this.renderBounds.width = 32;
		this.renderBounds.height = 24;
		this.hitbox = renderBounds;
	}

	@Override
	public void updateAI() {
		super.updateAI();
		// New cycle -> allow jumping again
		if (this.currentAi == 0) {
			canJump = true;
		}

		// If the current AI tick is the jump tick...
		if (this.currentAi == nextJump) {
			// Set direction so that slime faces the player
			int dir = Float.compare(LastTry.player.getX(), this.getX());
			if (dir < 0) {
				this.direction = Direction.LEFT;
			} else if (dir > 0) {
				this.direction = Direction.RIGHT;
			}
			// Jump and set next jump delay.
			this.jump();
			this.updateJumpDelay();

		}

		// If the slime is jumping / falling try and fall to the player.
		// If they are on the ground they will not move.
		// If the direction to the player changes mid-jump the slime will not be
		// able to change directions.
		if (this.state == State.JUMPING || this.state == State.FALLING) {
			this.move(this.direction);
		}
	}

	/**
	 * Update the time of the next jump.
	 */
	private void updateJumpDelay() {
		this.canJump = false;
		this.nextJump = (int) (Math.random() * this.maxAi);
	}
}
