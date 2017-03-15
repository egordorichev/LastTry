package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Animation;

public class DroppedItem extends Entity {
	private final ItemHolder holder;

	public DroppedItem(ItemHolder holder) {
		super();

		this.holder = holder;
		this.state = State.FALLING;
		this.texture = Assets.boxTexture;
		this.shouldUpdate = true;

		Animation animation = new Animation();
		animation.addFrame(this.texture, 1);
		animation.setLooping(false);

		this.animations[State.JUMPING.getId()] = null;
		this.animations[State.MOVING.getId()] = null;
		this.animations[State.IDLE.getId()] = animation;
		this.animations[State.DEAD.getId()] = null;
		this.animations[State.FALLING.getId()] = animation;
		this.animations[State.FLYING.getId()] = animation; // May fly like a soul

		this.renderBounds.width = 14;
		this.renderBounds.height = 16;
		this.hitbox = renderBounds;
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		/**
		 * TODO: On collision with player, destroy this entity, and add the item
		 * to the player's inventory.
		 * But first, finish inventory
		 */
	}
}