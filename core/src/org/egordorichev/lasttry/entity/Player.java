package org.egordorichev.lasttry.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.ui.UiInventory;

public class Player extends Entity {
	protected String name;
	protected Animation[] animations;

	public UiInventory inventory;

	public Player(String name) {
		super(100, 0, 0);

		this.name = name;
		this.texture = Textures.player;

		inventory = new UiInventory(89);

		this.animations = new Animation[State.values().length];

		Animation idleAnimation = new Animation(false);
		idleAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 32, 48), 0));

		Animation movingAnimation = new Animation(true);
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 96, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 144, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 192, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 240, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 288, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 336, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 384, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 432, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 480, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 528, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 576, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 624, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 672, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 720, 32, 48), 1));

		Animation jumpingAnimation = new Animation(false);
		jumpingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture,0, 48, 32, 48), 0)); // TODO

		Animation flyingAnimation = new Animation(true);
		flyingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 768, 32, 40), 5));
		flyingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 808, 32, 40), 5));
		flyingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 848, 32, 40), 5));
		flyingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 888, 32, 40), 5));

		Animation deadAnimation = new Animation(false);
		deadAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 32, 48), 0)); // TODO

		this.animations[State.IDLE.getId()] = idleAnimation;
		this.animations[State.MOVING.getId()] = movingAnimation;
		this.animations[State.JUMPING.getId()] = jumpingAnimation;
		this.animations[State.FALLING.getId()] = jumpingAnimation; // They are the same
		this.animations[State.FLYING.getId()] = flyingAnimation;
		this.animations[State.DEAD.getId()] = deadAnimation;
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
	}

	@Override
	public void render() {
		this.animations[this.state.getId()].render(this.renderBounds.x, LastTry.world.getHeight() * Block.TEX_SIZE
			- this.renderBounds.y - this.renderBounds.height, this.renderBounds.width, this.renderBounds.height,
			(this.direction == Direction.RIGHT), false);
	}

	public void renderBuffs() {
		if (!this.inventory.isOpen()) {
			for (int i = 0; i < this.effects.size(); i++) {
				this.effects.get(i).render(10 + (i % 11) * 34, Gdx.graphics.getHeight() - 130);
			}
		}
	}

	@Override
	public void update(int dt) {
		if (!this.shouldUpdate) {
			return;
		}

		this.animations[this.state.getId()].update();

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