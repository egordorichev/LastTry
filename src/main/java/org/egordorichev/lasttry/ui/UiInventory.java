package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.geom.Rectangle;

public class UiInventory extends UiComponent {
	private UiItemSlot[] slots;
	private boolean open;

	public UiInventory(int size) {
		super(new Rectangle(10, 10, 100, 32)); // TODO: tune

		this.slots = new UiItemSlot[88];
		this.open = false;

		int x = 10;
		int y = 30;

		for(int i = 0; i < 10; i++) { // Hot bar
			this.slots[i] = new UiItemSlot(new Rectangle(x + i * 54, y, 52, 52), UiItemSlot.Type.ANY);
		}

		for(int i = 10; i < 50; i++) { // Main inventory
			this.slots[i] = new UiItemSlot(new Rectangle(x + (i % 10) * 54, y + 54 * (i / 10), 52, 52), UiItemSlot.Type.ANY);
		}

		for(int i = 50; i < 54; i++) { // Coins
			this.slots[i] = new UiItemSlot(new Rectangle(x + 542, y + 100 + (i - 50) * 34, 32, 32), UiItemSlot.Type.COIN);
		}

		for(int i = 54; i < 58; i++) { // Ammo
			this.slots[i] = new UiItemSlot(new Rectangle(x + 576, y + 100 + (i - 54) * 34, 32, 32), UiItemSlot.Type.AMMO);
		}

		// Trash
		this.slots[58] = new UiItemSlot(new Rectangle(x + 486, y + 270, 52, 52), UiItemSlot.Type.TRASH);

		for(int i = 59; i < 62; i++) { // Armor
			this.slots[i] = new UiItemSlot(new Rectangle(10, 280 + (i - 59) * 54, 52, 52), UiItemSlot.Type.ARMOR, Origin.BOTTOM_RIGHT);
		}

		for(int i = 62; i < 65; i++) { // Vanity Armor
			this.slots[i] = new UiItemSlot(new Rectangle(64, 280 + (i - 62) * 54, 52, 52), UiItemSlot.Type.ARMOR, Origin.BOTTOM_RIGHT);
		}

		for(int i = 65; i < 68; i++) { // Armor Dye
			this.slots[i] = new UiItemSlot(new Rectangle(118, 280 + (i - 65) * 54, 52, 52), UiItemSlot.Type.ARMOR, Origin.BOTTOM_RIGHT);
		}

		for(int i = 68; i < 73; i++) { // Accessories
			this.slots[i] = new UiItemSlot(new Rectangle(10, 10 + (i - 68) * 54, 52, 52), UiItemSlot.Type.ACCESSORY, Origin.BOTTOM_RIGHT);
		}

		for(int i = 73; i < 78; i++) { // Vanity Accessories
			this.slots[i] = new UiItemSlot(new Rectangle(64, 10 + (i - 73) * 54, 52, 52), UiItemSlot.Type.ACCESSORY, Origin.BOTTOM_RIGHT);
		}

		for(int i = 78; i < 83; i++) { // Accessories Dyes
			this.slots[i] = new UiItemSlot(new Rectangle(118, 10 + (i - 78) * 54, 52, 52), UiItemSlot.Type.DYE, Origin.BOTTOM_RIGHT);
		}

		for(int i = 83; i < 88; i++) { // Equipment
			this.slots[i] = new UiItemSlot(new Rectangle(0, 0, 52, 52), UiItemSlot.Type.ANY); // TODO
			this.slots[i].hide();
		}

		this.slots[0].setItemHolder(new ItemHolder(Item.goldCoin, 14, null));
	}

	@Override
	public void render() {
		super.render();

		Assets.font.drawString(10, 5, "Inventory");

		for(int i = 0; i < 10; i++) {
			this.slots[i].render();
		}

		if(this.open) {
			for(int i = 10; i < 88; i++) {
				this.slots[i].render();
			}
		}
	}

	public void open() {
		this.open = true;
	}

	public void close() {
		this.open = false;
	}

	public void toggle() {
		this.open = !this.open;
	}

	public boolean isOpen() {
		return open;
	}
}