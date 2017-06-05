package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.inventory.InventorySlot;
import org.egordorichev.lasttry.inventory.ItemHolder;

public class UiItemSlot extends UiComponent implements InventorySlot {
	private static TextureRegion inventorySlot5 = Assets.getTexture(Textures.inventorySlot5);
	private boolean active;
	private TextureRegion texture;
	private ItemHolder itemHolder;
	private Type type;
	private TextureRegion back;

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
			Graphics.batch.draw(this.back, x + (width - this.back.getRegionWidth()) / 2,
					y + (height - this.back.getRegionHeight()) / 2);
			Graphics.batch.setColor(1, 1, 1, 1);
		}

		Graphics.batch.setColor(1, 1, 1, 1);
	}

	public void swapWithCurrent() {
		Globals.getPlayer().getInventory().setSelectedItem(this.swapItems(Globals.getPlayer().getInventory().getSelectedItem()));
	}

	@Override
	protected void onStateChange() {
		UiInventory inventory = Globals.getPlayer().getInventory();
		if (this.state == State.MOUSE_DOWN && inventory.isOpen()) {
			if (InputManager.isMouseButtonPressed(Input.Buttons.LEFT)) {
				if (InputManager.isKeyDown(Input.Keys.SHIFT_LEFT)) {
					if (this.isEmpty()) {
						return;
					}

					Globals.getPlayer().getInventory().trash(this.itemHolder);
					this.itemHolder = new ItemHolder(null, 0);
				} if (this.isEmpty() || inventory.getSelectedItem().isEmpty()) {
					this.swapWithCurrent();
				} else if (inventory.getSelectedItem().getItem().getID().equals(this.getItemID())) {
					int count = inventory.getSelectedItem().getCount() + this.itemHolder.getCount();
					int max = this.itemHolder.getItem().getMaxInStack();

					if (count <= max) {
						this.itemHolder.setCount(count);
						inventory.setSelectedItem(new ItemHolder(null, 0));
					} else {
						this.swapWithCurrent();
					}
				} else if (this.type == Type.TRASH) {
					if (inventory.getSelectedItem().getItem() == null) {
						inventory.setSelectedItem(this.itemHolder);
						this.itemHolder = new ItemHolder(null, 0);
					} else {
						this.itemHolder = inventory.getSelectedItem();
						inventory.setSelectedItem(new ItemHolder(null, 0));
					}
				}
			} else if (InputManager.isMouseButtonPressed(Input.Buttons.RIGHT)) {

			}
		}
	}

	@Override
	public ItemHolder getItemHolder() {
		return this.itemHolder;
	}

	@Override
	public boolean setItemHolder(ItemHolder holder) {
		if (!this.canHold(holder)) {
			return false;
		}

		this.itemHolder = holder;
		return true;
	}

	public boolean isActive() {
		return this.active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public Type getType() {
		return type;
	}
}
