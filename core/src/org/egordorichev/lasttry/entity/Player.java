package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Animation;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.ui.UiInventory;
import org.egordorichev.lasttry.util.Direction;

public class Player extends Entity {
	protected String name;
	protected Animation[] animations;

	public UiInventory inventory;

	public Player(String name) {
		super(100, 0, 0);

		this.name = name;
		this.texture = Textures.player;

		inventory = new UiInventory(89);

		/* this.animations = new Animation[State.values().length];

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
		flyingAnimation.addFrame(this.texture.getSubImage(0, 768, 32, 40), 80);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 808, 32, 40), 80);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 848, 32, 40), 80);
		flyingAnimation.addFrame(this.texture.getSubImage(0, 888, 32, 40), 80);

		Animation deadAnimation = new Animation(false);
		deadAnimation.addFrame(this.texture.getSubImage(0, 0, 32, 48), 1000); // TODO
		deadAnimation.setLooping(false);

		this.animations[State.IDLE.getId()] = idleAnimation;
		this.animations[State.MOVING.getId()] = movingAnimation;
		this.animations[State.JUMPING.getId()] = jumpingAnimation;
		this.animations[State.FALLING.getId()] = jumpingAnimation; // They are
																	// the same
		this.animations[State.FLYING.getId()] = flyingAnimation;
		this.animations[State.DEAD.getId()] = deadAnimation; */
	}

	public void setGhostMode(boolean enabled) {
		this.isSolid = !enabled;

		if (enabled) {
			this.state = State.FLYING;
		} else {
			this.state = State.IDLE;
		}
	}

	@Override
	public void onSpawn() {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 2; x++) {
				LastTry.world.setBlock(ItemID.none, this.getGridX() + x, this.getGridY() + y);
			}
		}

		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 25; x++) {
				LastTry.world.setBlock(ItemID.crimstoneBlock, this.getGridX() + 3 + x, this.getGridY() + 2 + y);
			}
		}
	}

	@Override
	public void render() {
		// this.animations[this.state.getId()].getCurrentFrame().getFlippedCopy(this.direction == Direction.RIGHT, false)
		//		.draw(this.renderBounds.x, this.renderBounds.y);
	}

	public void renderBuffs() {
		if (!this.inventory.isOpen()) {
			for (int i = 0; i < this.effects.size(); i++) {
				this.effects.get(i).render(10 + (i % 11) * 34, 90);
			}
		}
	}

	@Override
	public void update(int dt) {
		if (!this.shouldUpdate) {
			return;
		}

		if (this.state == State.FLYING) {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE) ||  Gdx.input.isKeyPressed(Input.Keys.W)) {
				this.velocity.y -= 1;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.S)) {
				this.velocity.y += 1;
			}

			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				this.move(Direction.LEFT);
			}

			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				this.move(Direction.RIGHT);
			}
		} else {
			if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				this.jump();
			}

			if (Gdx.input.isKeyPressed(Input.Keys.A)) {
				this.move(Direction.LEFT);
			}

			if (Gdx.input.isKeyPressed(Input.Keys.D)) {
				this.move(Direction.RIGHT);
			}
		}

		if (Gdx.input.isKeyJustPressed(Input.Keys.E) || Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
			inventory.toggle();
		}

		super.update(dt);
		// this.animations[this.state.getId()].update(dt);
	}

	public String getName() {
		return this.name;
	}
}