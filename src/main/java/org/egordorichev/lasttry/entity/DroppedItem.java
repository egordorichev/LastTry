package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.util.Assets;

public class DroppedItem extends Entity {
	/**
	 * Item holder that this dropped entity represents.
	 */
	private final ItemHolder holder;

	public DroppedItem(ItemHolder holder) {
		super();

		this.holder = holder;
		this.state = State.FALLING;
		this.texture = this.holder.getItem().getTexture();
		this.shouldUpdate = true;

		// TODO: it can be any
		this.renderBounds.width = 16;
		this.renderBounds.height = 16;
		this.hitbox = renderBounds;
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		/**
		 * TODO: On collision with player, destroy this entity, and add the item
		 * to the player's inventory. But first, finish inventory
		 */
	}

	/**
	 * Return the ItemHolder that this entity represents.
	 * 
	 * @return 
	 */
	public ItemHolder getHolder() {
		return holder;
	}
}