package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.*;
import org.egordorichev.lasttry.item.items.Accessory;
import org.egordorichev.lasttry.item.items.Ammo;
import org.egordorichev.lasttry.item.items.Armor;
import org.egordorichev.lasttry.item.items.Coin;
import org.egordorichev.lasttry.item.items.Dye;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;

public class UiItemSlot extends UiComponent {
	public enum Type {
		ANY,
		ACCESSORY,
		VANITY_ACCESSORY,
		ARMOR,
		COIN,
		AMMO,
		TRASH,
		VANITY,
		DYE
	}

	// What will this be used for and why should it be static?
	private static Color color;
	private Image texture;
	private ItemHolder itemHolder;
	private Type type;
	private Image back;

	public UiItemSlot(Rectangle rectangle, Type type, Origin origin, Image back) {
		super(rectangle, origin);

		this.itemHolder = null;
		this.type = type;
		this.back = back;

		switch(this.type) {
			case ANY: case AMMO: case COIN: case TRASH:
				this.texture = Assets.inventorySlot1Texture;
			break;
			case ACCESSORY: case ARMOR:
				this.texture = Assets.inventorySlot2Texture;
			break;
			case VANITY: case VANITY_ACCESSORY:
				this.texture = Assets.inventorySlot3Texture;
			break;
			case DYE:
				this.texture = Assets.inventorySlot4Texture;
			break;
		}

		this.texture.setAlpha(0.8f);

		if(this.back != null) {
			this.back.setAlpha(0.7f);
		}
	}

	public UiItemSlot(Rectangle rectangle, Type type) {
		this(rectangle, type, Origin.TOP_LEFT, null);
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

		if(this.itemHolder != null && this.itemHolder.getItem() != null) {
			this.itemHolder.renderAt(x, y, width, height);
		} else if(this.back != null) {
			this.back.draw(x + (width - this.back.getWidth()) / 2, y + (height - this.back.getHeight()) / 2);
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
			case ACCESSORY: case VANITY_ACCESSORY:
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
			if(LastTry.player.inventory.isOpen()) {
				if(LastTry.input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
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
				} else if(LastTry.input.isMouseButtonDown(Input.MOUSE_RIGHT_BUTTON)) {
					// TODO
				}
			}
		}
	}
}