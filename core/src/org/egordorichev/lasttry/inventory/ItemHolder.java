package org.egordorichev.lasttry.inventory;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.modifier.Modifier;
import org.egordorichev.lasttry.util.Util;

public class ItemHolder {
	private int count;
	private Item item;
	private Modifier modifier;

	public ItemHolder(Item item, int count, Modifier modifier) {
		this.item = item;
		this.count = count;
		this.modifier = modifier;
	}

	public ItemHolder(Item item, int count) {
		this(item, count, null);
	}

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

	public int getCount() {
		return this.count;
	}

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

	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Modifier getModifier() {
		return this.modifier;
	}

	public void setModifier(Modifier modifier) {
		this.modifier = modifier;
	}

	public boolean isEmpty() {
		return this.item == null || this.count == 0;
	}

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