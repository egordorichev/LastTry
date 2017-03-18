package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.util.Rectangle;

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
		this.renderBounds.width = this.texture.getWidth();
		this.renderBounds.height = this.texture.getHeight();
		this.hitbox = new Rectangle(this.renderBounds.x, this.renderBounds.y, this.renderBounds.width, this.renderBounds.height); // TODO: copy method
		this.shouldUpdate = true;
		this.isSolid = true;
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		System.out.println(this.velocity.y);

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