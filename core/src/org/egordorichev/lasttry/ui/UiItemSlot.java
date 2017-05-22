package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.items.*;

public class UiItemSlot extends UiComponent {
	private static TextureRegion inventorySlot5 = Assets.getTexture(Textures.inventorySlot5);

	private boolean active;
	private TextureRegion texture;
	private ItemHolder itemHolder;
	private Type type;
	private TextureRegion back;

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

	public UiItemSlot(Rectangle rectangle, Type type, Origin origin, TextureRegion back) {
		super(rectangle, origin);

		this.itemHolder = new ItemHolder(null, 0);
		this.type = type;
		this.back = back;

		switch (this.type) {
			case ANY:
			case AMMO:
			case COIN:
			case TRASH:
				this.texture = Assets.getTexture(Textures.inventorySlot1);
			break;
			case ACCESSORY:
			case ARMOR:
				this.texture = Assets.getTexture(Textures.inventorySlot2);
			break;
			case VANITY:
			case VANITY_ACCESSORY:
				this.texture = Assets.getTexture(Textures.inventorySlot3);
			break;
			case DYE:
				this.texture = Assets.getTexture(Textures.inventorySlot4);
			break;
		}

		this.active = false;
	}

	public UiItemSlot(Rectangle rectangle, Type type) {
		this(rectangle, type, Origin.TOP_LEFT, null);
	}

	public boolean setItemHolder(ItemHolder holder) {
		if (!this.canHold(holder)) {
			return false;
		}

		this.itemHolder = holder;
		return true;
	}

	@Override
	public void render() {
		if (this.hidden) {
			return;
		}

		super.render();

		int x = this.getX();
		int y = this.getY();
		int width = this.getWidth();
		int height = this.getHeight();

		Graphics.batch.setColor(1, 1, 1, 0.8f);

		if (this.active) {
			Graphics.batch.draw(inventorySlot5, x, y, width, height);
		} else {
			Graphics.batch.draw(this.texture, x, y, width, height);
		}

		if (this.itemHolder.getItem() != null) {
			Graphics.batch.setColor(1, 1, 1, 1);
			this.itemHolder.renderAt(x, y, width, height);
		} else if (this.back != null) {
			Graphics.batch.setColor(1, 1, 1, 0.7f);
			Graphics.batch.draw(this.back, x + (width - this.back.getRegionWidth()) / 2, y + (height
				- this.back.getRegionHeight()) / 2);
			Graphics.batch.setColor(1, 1, 1, 1);
		}

		Graphics.batch.setColor(1, 1, 1, 1);
	}

	public void setItemCount(int count) {
		this.itemHolder.setCount(count);
	}

	public ItemHolder getItemHolder() {
		return this.itemHolder;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Item getItem() {
		return this.itemHolder.getItem();
	}

	public short getItemID() {
		Item item = this.getItem();

		if (item == null) {
			return 0;
		}

		return item.getID();
	}

	public int getItemCount() {
		return this.itemHolder.getCount();
	}

	public boolean canHold(ItemHolder holder) {
		switch (this.type) {
			case ANY:
			case TRASH:
			default:
			break;
			case ACCESSORY:
			case VANITY_ACCESSORY:
				if (!(holder.getItem() instanceof Accessory)) {
					return false;
				}
			break;
			case ARMOR:
			case VANITY:
				if (!(holder.getItem() instanceof Armor)) {
					return false;
				}
			break;
			case COIN:
				if (!(holder.getItem() instanceof Coin)) {
					return false;
				}
			break;
			case AMMO:
				if (!(holder.getItem() instanceof Ammo)) {
					return false;
				}
			break;
			case DYE:
				if (!(holder.getItem() instanceof Dye)) {
					return false;
				}
			break;
		}

		return true;
	}

	public boolean isEmpty() {
		return this.itemHolder.isEmpty();
	}

	public ItemHolder swapItems(ItemHolder to) {
		if (to != null && !this.canHold(to)) {
			return to;
		}

		ItemHolder tmp = this.itemHolder;
		this.itemHolder = to;

		return tmp;
	}

	public void swapWithCurrent() {
		Globals.player.getInventory().currentItem = this.swapItems(Globals.player.getInventory().currentItem);
	}

	@Override
	protected void onStateChange() {
		UiInventory inventory = Globals.player.getInventory();
		if (this.state == State.MOUSE_DOWN && inventory.isOpen()) {
			if (InputManager.isMouseButtonPressed(Input.Buttons.LEFT)) {
				if (this.isEmpty() || inventory.currentItem.isEmpty()) {
					this.swapWithCurrent();
				} else if (inventory.currentItem.getItem().getID() == this.getItemID()) {
					int count = inventory.currentItem.getCount() + this.itemHolder.getCount();
					int max = this.itemHolder.getItem().getMaxInStack();

					if (count <= max) {
						this.itemHolder.setCount(count);
						inventory.currentItem = new ItemHolder(null, 0);
					} else {
						this.swapWithCurrent();
					}
				} else if (this.type == Type.TRASH) {
					if (inventory.currentItem.getItem() == null) {
						inventory.currentItem = this.itemHolder;
						this.itemHolder = new ItemHolder(null, 0);
					} else {
						this.itemHolder = inventory.currentItem;
						inventory.currentItem = new ItemHolder(null, 0);
					}
				}
			} else if (InputManager.isMouseButtonPressed(Input.Buttons.RIGHT)) {

			}
		}
	}
}
