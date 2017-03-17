package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.*;
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
	private Type type;

	public UiItemSlot(Rectangle rectangle, Type type, Origin origin) {
		super(rectangle, origin);

		this.texture = Assets.inventorySlotTexture;
		this.itemHolder = null;
		this.type = type;
	}

	public UiItemSlot(Rectangle rectangle, Type type) {
		this(rectangle, type, Origin.TOP_LEFT);
	}

	public boolean setItemHolder(ItemHolder holder) {
		if(!this.canHold(holder)) {
			return false;
		}

		this.itemHolder = holder;
		return true;
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

		if(this.itemHolder != null) {
			this.itemHolder.renderAt(x, y, width, height);
		}
	}

	public ItemHolder getItemHolder() {
		return this.itemHolder;
	}

	public Item getItem() {
		if(this.itemHolder == null) {
			return null;
		}

		return this.itemHolder.getItem();
	}

	public boolean canHold(ItemHolder holder) {
		switch(this.type) {
			case ANY: case TRASH: default: break;
			case ACCESSORY:
				if(!(holder.getItem() instanceof Accessory)) {
					return false;
				}
			break;
			case ARMOR: case VANITY:
				if(!(holder.getItem() instanceof Armor)) {
					return false;
				}
			break;
			case COIN:
				if(!(holder.getItem() instanceof Coin)) {
					return false;
				}
			break;
			case AMMO:
				if(!(holder.getItem() instanceof Ammo)) {
					return false;
				}
			break;
			case DYE:
				if(!(holder.getItem() instanceof Dye)) {
					return false;
				}
			break;
		}

		return true;
	}

	@Override
	protected void onStateChange() {
		if(this.state == State.MOUSE_DOWN) {
			if(LastTry.player.inventory.currentItem != null) {
				if(this.canHold(LastTry.player.inventory.currentItem)) {
					ItemHolder tmp = this.itemHolder;
					this.itemHolder = LastTry.player.inventory.currentItem;
					LastTry.player.inventory.currentItem = tmp;
				}
			} else {
				LastTry.player.inventory.currentItem = this.itemHolder;
				this.itemHolder = null;
			}
		}
	}
}