package org.egordorichev.lasttry.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.items.modifiers.Modifier;
import org.egordorichev.lasttry.util.Util;

/**
 * Class, that holds item, count and item modifier
 */
public class ItemHolder {
	/**
	 * Item count
	 */
	private int count;
	/**
	 * Item pointer
	 */
	private Item item;
	/**
	 * Item modifier
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
	 * Renders item at given position
	 *
	 * @param x Render X
	 * @param y Render Y
	 */
	public void renderAt(int x, int y) {
		if (this.item != null) {
			TextureRegion texture = this.item.getTextureRegion();

			int th = texture.getRegionHeight();

			Graphics.batch.draw(texture, x, y);

			if (this.count > 1) {
				Util.drawWithShadow(Assets.f18, String.format("%d", this.count), x - 8, y + th - 8);
			}
		}
	}

	/**
	 * Renders item at given position and with given size
	 *
	 * @param x Render X
	 * @param y Render Y
	 * @param width Item width
	 * @param height Item height
	 */
	public void renderAt(int x, int y, int width, int height) {
		if (this.item != null) {
			TextureRegion texture = this.item.getTextureRegion();

			int tw = texture.getRegionWidth();
			int th = texture.getRegionHeight();
			int iy = y + (height - th) / 2;

			Graphics.batch.draw(texture, x + (width - tw) / 2, iy);

			if (this.count > 1) {
				Util.drawWithShadow(Assets.f18, String.format("%d", this.count), x + tw / 2, iy + th / 2);
			}
		}
	}

	/**
	 * Sets item
	 *
	 * @param item New item
	 */
	public void setItem(Item item) {
		this.item = item;
	}

	/**
	 * Sets count
	 *
	 * @param count New count
	 */
	public void setCount(int count) {
		if (this.item == null) {
			this.count = 0;
			return;
		}

		if (count > this.item.getMaxInStack()) {
			this.count = this.item.getMaxInStack();
		}

		this.count = count;
	}

	/**
	 * Sets modifier
	 *
	 * @param modifier New modifier
	 */
	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	/**
	 * @return Item count
	 */
	public int getCount() {
		return this.count;
	}

	/**
	 * @return Item pointer
	 */
	public Item getItem() {
		return this.item;
	}

	/**
	 * @return Item modifier
	 */
	public Modifier getModifier() {
		return this.modifier;
	}

	/**
	 * @return Holder is empty
	 */
	public boolean isEmpty() {
		return this.item == null || this.count == 0;
	}

	/**
	 * @return String info about item
	 */
	public String asInfo() {
		if (this.isEmpty()) {
			return "";
		}

		String info = "";

		if (this.getModifier() != null) {
			info += this.getModifier().getName() + " ";
		}

		info += this.getItem().getName();

		if (this.getCount() != 1) {
			info += " (" + this.getCount() + ")";
		}

		return info;
	}
}