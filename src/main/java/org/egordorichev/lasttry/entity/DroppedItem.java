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

		this.renderBounds.width = 16; // TODO: it can be any
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