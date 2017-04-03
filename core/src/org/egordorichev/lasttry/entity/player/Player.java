package org.egordorichev.lasttry.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.Direction;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.graphics.Animation;
import org.egordorichev.lasttry.graphics.AnimationFrame;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.ui.UiInventory;

public class Player extends Entity {
	/** Player info */
	protected PlayerInfo info;
	
	/** Player animations */
	protected Animation[] animations;
	
	/** Players render info */
	protected PlayerRenderInfo renderInfo;

	/** Players inventory */
	public UiInventory inventory;

	public Player(PlayerInfo info) {
		super(info.maxHp, 0, 0);
		
		this.info = info;
		this.animations = new Animation[State.values().length];
		this.setupTexture();

		this.inventory = new UiInventory(89);
		LastTry.ui.add(this.inventory);

		this.hitbox.height -= 3;
	}
	
	/** Creates/updates player texture and animations */
	private void setupTexture() {
		this.renderInfo = this.info.renderInfo;
		this.texture = PlayerRenderer.generateTexture(this.renderInfo);
		
		Animation idleAnimation = new Animation(false);
		idleAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 8, 32, 48), 0));

		Animation movingAnimation = new Animation(true);
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 342, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 400, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 456, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 512, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 568, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 624, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 680, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 736, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 792, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 848, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 902, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 960, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 1016, 32, 48), 1));
		movingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 1072, 32, 48), 1));

		Animation jumpingAnimation = new Animation(false);
		jumpingAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 4, 288, 32, 48), 0));

		Animation flyingAnimation = new Animation(true); // TODO

		Animation deadAnimation = new Animation(false);
		deadAnimation.addFrame(new AnimationFrame(new TextureRegion(this.texture, 0, 0, 0, 0), 0)); // TODO

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
		ItemHolder holder = this.inventory.getActiveHolder();

		if (holder.getItem() != null) {
			holder.getItem().renderAnimation();
		}

		this.animations[this.state.getId()].render(this.renderBounds.x, LastTry.world.getHeight() * Block.TEX_SIZE
			- this.renderBounds.y - this.renderBounds.height, this.renderBounds.width, this.renderBounds.height,
			(this.direction == Direction.LEFT), false);
	}

	public boolean isFlipped() {
		return (this.direction == Direction.LEFT);
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
			if (InputManager.isKeyDown(Keys.JUMP) || InputManager.isKeyDown(Keys.MOVE_UP)) {
				this.velocity.y -= 1;
			}

			if (InputManager.isKeyDown(Keys.MOVE_DOWN)) {
				this.velocity.y += 1;
			}

			if (InputManager.isKeyDown(Keys.MOVE_LEFT)) {
				this.move(Direction.LEFT);
			}

			if (InputManager.isKeyDown(Keys.MOVE_RIGHT)) {
				this.move(Direction.RIGHT);
			}
		} else {
			if (InputManager.isKeyDown(Keys.JUMP)) {
				this.jump();
			}

			if (InputManager.isKeyDown(Keys.MOVE_LEFT)) {
				this.move(Direction.LEFT);
			}

			if (InputManager.isKeyDown(Keys.MOVE_RIGHT)) {
				this.move(Direction.RIGHT);
			}
		}

		if (InputManager.isKeyJustDown(Keys.OPEN_INVENTORY)) {
			this.inventory.toggle();
		}

		super.update(dt);
	}

	public String getName() {
		return this.info.name;
	}
}
