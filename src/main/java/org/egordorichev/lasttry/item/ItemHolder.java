package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.util.Assets;
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

	public void renderAt(int x, int y) {
		if(this.item != null) {
			Image texture = this.item.getTexture();

			int tw = texture.getWidth();
			int th = texture.getHeight();

			texture.draw(x, y);

			if(this.count > 1) {
				Assets.smallFont.drawString(x - 8, y + th - 8, String.format("%d", this.count));
			}
		}
	}

	public void renderAt(int x, int y, int width, int height) {
		if(this.item != null) {
			Image texture = this.item.getTexture();

			int tw = texture.getWidth();
			int th = texture.getHeight();
			int iy = y + (height - th) / 2;

			texture.draw(x + (width - tw) / 2, iy);

			if(this.count > 1) {
				Assets.smallFont.drawString(x + tw / 2, iy + th / 2, String.format("%d", this.count));
			}
		}
	}

	public int getCount() {
		return this.count;
	}

	public Item getItem() {
		return this.item;
	}

	public Modifier getModifier() {
		return this.modifier;
	}
}