package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.item.modifier.Modifier;
import org.newdawn.slick.Image;

/**
 * Holds dropped items.
 */
public class ItemHolder {
	/**
	 * The number of items held.
	 */
	private int count;
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

	public ItemHolder(Item item, int count) {
		this(item, count, null);
	}

	/**
	 * Render the contained item + the number of items at the given screen
	 * coordinates.
	 *
	 * @param x
	 *            Screen x-position.
	 * @param y
	 *            Screen y-position.
	 */
	public void renderAt(int x, int y) {
		if (this.item != null) {
			Image texture = this.item.getTexture();

			int th = texture.getHeight();

			texture.draw(x, y);

			if (this.count > 1) {
				Assets.smallFont.drawString(x - 8, y + th - 8, String.format("%d", this.count));
			}
		}
	}

	/**
	 * Render the contained item + the number of items at the given screen
	 * coordinates with the given dimensions.
	 * 
	 * @param x
	 *            Screen x-position.
	 * @param y
	 *            Screen y-position.
	 * @param width
	 *            Width.
	 * @param height
	 *            Height.
	 */
	public void renderAt(int x, int y, int width, int height) {
		if (this.item != null) {
			Image texture = this.item.getTexture();

			int tw = texture.getWidth();
			int th = texture.getHeight();
			int iy = y + (height - th) / 2;

			texture.draw(x + (width - tw) / 2, iy);

			if (this.count > 1) {
				Assets.smallFont.drawString(x + tw / 2, iy + th / 2, String.format("%d", this.count));
			}
		}
	}
	/**
	 * Sets num item in the slot
	 * @param count
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * Return the number of items held.
	 * 
	 * @return Number of items.
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * Return the {@link org.egordorichev.lasttry.item.Item Item} instance being
	 * held.
	 * 
	 * @return Item instance.
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * Return the {@link Modifier modifier} for
	 * the held item.
	 * 
	 * @return Item modifier.
	 */
	public Modifier getModifier() {
		return this.modifier;
	}
}