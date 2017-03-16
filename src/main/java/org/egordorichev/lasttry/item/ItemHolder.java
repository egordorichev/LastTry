package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.item.blocks.Block;
import org.newdawn.slick.geom.Vector2f;

/**
 * Holds dropped items.
 */
public class ItemHolder {
	/**
	 * The number of items held.
	 */
	private int count;
	/**
	 * Dropped item position in the world.
	 */
	private Vector2f position;
	/**
	 * The item type the holder contains.
	 */
	private Item item;

	public ItemHolder(Item item, int count) {
		this.item = item;
		this.count = count;
	}

	public void renderAt(int x, int y) {
		this.item.getTexture().draw(x, y);
	}

	public int getCount() {
		return this.count;
	}

	public Vector2f getPosition() {
		return this.position;
	}

	public int getGridX() {
		return (int) this.position.x / Block.TEX_SIZE;
	}

	public int getGridY() {
		return (int) this.position.y / Block.TEX_SIZE;
	}

	public Item getItem() {
		return this.item;
	}
}