package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class UiItemSlot extends UiComponent {
	public enum Type {
		ANY,
		ACCESSORY,
		ARMOR,
		COIN,
		AMMO,
		TRASH,
		VANITY,
		DYE
	}

	private Image texture;
	private ItemHolder itemHolder;

	public UiItemSlot(Rectangle rectangle, Type type, Origin origin) {
		super(rectangle, origin);

		this.texture = Assets.inventorySlotTexture;
		this.itemHolder = new ItemHolder(null, 0, null);
	}

	public UiItemSlot(Rectangle rectangle, Type type) {
		this(rectangle, type, Origin.TOP_LEFT);
	}

	public void setItemHolder(ItemHolder holder) {
		this.itemHolder = holder;
	}

	@Override
	public void render() {
		if(this.hidden) {
			return;
		}

		super.render();

		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		int height = this.getHeight();

		this.texture.draw(x, y, width, height);
		this.itemHolder.renderAt(x, y, width, height);
	}

	public ItemHolder getItemHolder() {
		return this.itemHolder;
	}
}