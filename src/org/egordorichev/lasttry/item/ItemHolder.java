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
	 * Value indicating if the holder has been dropped into the world.
	 */
	private boolean dropped;
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
		this.dropped = false;
	}

	public void drop(int x, int y) {
		this.dropped = true;
		this.position.x = x;
		this.position.y = y;
	}

	public void pickup() {
		this.dropped = false;
	}

	public void render() {
		if (this.dropped) {
			this.item.getTexture().draw(this.position.x, this.position.y);
		}
	}

	public void renderAt(int x, int y) {
		this.item.getTexture().draw(x, y);
	}

	public int getCount() {
		return this.count;
	}

	public boolean isDropped() {
		return this.dropped;
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