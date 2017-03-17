package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.item.blocks.Block;
import org.newdawn.slick.Image;
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
	 * The item the holder contains.
	 */
	private Item item;
	/**
	 * The item modifier
	 */
	private Modifier modifier;

	public ItemHolder(Item item, int count, Modifier modifier) {
		this.item = item;
		this.count = count;
		this.modifier = modifier;
	}

	public void renderAt(int x, int y) {
		if(this.item != null) {
			this.item.getTexture().draw(x, y);
		}
	}

	public void renderAt(int x, int y, int width, int height) {
		if(this.item != null) {
			Image texture = this.item.getTexture();

			int tw = texture.getWidth();
			int th = texture.getHeight();

			texture.draw(x + (width - tw) / 2, y + (height - th) / 2);
		}
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

	public Modifier getModifier() {
		return this.modifier;
	}
}