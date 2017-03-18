package org.egordorichev.lasttry.entity;

import org.egordorichev.lasttry.LastTry;
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
		this.hitbox = this.renderBounds.copy();
		this.shouldUpdate = true;
		this.isSolid = true;
	}

	@Override
	public void update(int dt) {
		super.update(dt);

		if(this.getHitbox().intersects(LastTry.player.getHitbox())) {
			System.out.println("Add");

			if(LastTry.player.inventory.add(this.holder)) {
				LastTry.world.remove(this);
			}
		}
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