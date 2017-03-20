package org.egordorichev.lasttry.ui;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.item.modifier.Modifier;
import org.newdawn.slick.Input;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.geom.Rectangle;

public class UiInventory extends UiComponent {
	private UiItemSlot[] slots;
	private boolean open;

	public ItemHolder currentItem = null;
	public int currentSlot = 0;

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
		this.slots[58] = new UiItemSlot(new Rectangle(x + 486, y + 270, 52, 52), UiItemSlot.Type.TRASH, Origin.TOP_LEFT, Assets.trashTexture);

		for(int i = 59; i < 62; i++) { // Armor
			this.slots[i] = new UiItemSlot(new Rectangle(10, 280 + (i - 59) * 54, 52, 52), UiItemSlot.Type.ARMOR, Origin.BOTTOM_RIGHT, Assets.inventoryBackTexture.getSubImage(0, 68 - (i - 59) * 34, 34, 34));
		}

		for(int i = 62; i < 65; i++) { // Vanity Armor
			this.slots[i] = new UiItemSlot(new Rectangle(64, 280 + (i - 62) * 54, 52, 52), UiItemSlot.Type.VANITY, Origin.BOTTOM_RIGHT, Assets.inventoryBackTexture.getSubImage(0, 170 - (i - 62) * 34, 34, 34));
		}

		for(int i = 65; i < 68; i++) { // Armor Dye
			this.slots[i] = new UiItemSlot(new Rectangle(118, 280 + (i - 65) * 54, 52, 52), UiItemSlot.Type.DYE, Origin.BOTTOM_RIGHT, Assets.inventoryBackTexture.getSubImage(34, 0, 34, 34));
		}

		for(int i = 68; i < 73; i++) { // Accessories
			this.slots[i] = new UiItemSlot(new Rectangle(10, 10 + (i - 68) * 54, 52, 52), UiItemSlot.Type.ACCESSORY, Origin.BOTTOM_RIGHT, Assets.inventoryBackTexture.getSubImage(68, 34, 34, 34));
		}

		for(int i = 73; i < 78; i++) { // Vanity Accessories
			this.slots[i] = new UiItemSlot(new Rectangle(64, 10 + (i - 73) * 54, 52, 52), UiItemSlot.Type.VANITY_ACCESSORY, Origin.BOTTOM_RIGHT, Assets.inventoryBackTexture.getSubImage(68, 0, 34, 34));
		}

		for(int i = 78; i < 83; i++) { // Accessories Dyes
			this.slots[i] = new UiItemSlot(new Rectangle(118, 10 + (i - 78) * 54, 52, 52), UiItemSlot.Type.DYE, Origin.BOTTOM_RIGHT, Assets.inventoryBackTexture.getSubImage(34, 0, 34, 34));
		}

		for(int i = 83; i < 88; i++) { // Equipment
			this.slots[i] = new UiItemSlot(new Rectangle(0, 0, 52, 52), UiItemSlot.Type.ANY); // TODO
			this.slots[i].hide();
		}

		this.currentSlot = 0;
		this.slots[currentSlot].setActive(true);

		LastTry.input.addMouseListener(new MouseListener() {
			@Override
			public void mouseWheelMoved(int i) {
				slots[currentSlot].setActive(false);
				currentSlot += (i > 0) ? 1 : -1;

				if(currentSlot > 9) {
					currentSlot = 0;
				} else if(currentSlot < 0) {
					currentSlot = 9;
				}

				slots[currentSlot].setActive(true);
			}

			@Override
			public void mouseClicked(int i, int i1, int i2, int i3) {

			}

			@Override
			public void mousePressed(int i, int i1, int i2) {
				if(open) {
					return;
				}

				ItemHolder holder = getActiveHolder();
				Item item = holder.getItem();

				if(item != null) {
					if(item.use()) {
						int count = holder.getCount();

						if(count == 1) {
							slots[currentSlot].setItemHolder(new ItemHolder(null, 0, null));
						} else {
							holder.setCount(count - 1);
						}
					}
				}
			}

			@Override
			public void mouseReleased(int i, int i1, int i2) {

			}

			@Override
			public void mouseMoved(int i, int i1, int i2, int i3) {

			}

			@Override
			public void mouseDragged(int i, int i1, int i2, int i3) {

			}

			@Override
			public void setInput(Input input) {

			}

			@Override
			public boolean isAcceptingInput() {
				return true;
			}

			@Override
			public void inputEnded() {

			}

			@Override
			public void inputStarted() {

			}
		});
	}

	@Override
	public void render() {
		super.render();

		Item item = this.slots[this.currentSlot].getItem();

		if(item == null) {
			Assets.font.drawString(10, 5, "Inventory");
		} else {
			Modifier modifier = this.slots[this.currentSlot].getItemHolder().getModifier();

			if(modifier != null) {
				Assets.font.drawString(10, 5, String.format("%s %s", modifier.getName(), item.getName()));
			} else {
				Assets.font.drawString(10, 5, item.getName());
			}
		}

		for(int i = 0; i < 10; i++) {
			this.slots[i].render();
		}

		if(this.open) {
			for(int i = 10; i < 88; i++) {
				this.slots[i].render();
			}
		}

		if(currentItem != null) {
			currentItem.renderAt(LastTry.input.getMouseX() + 16, LastTry.input.getMouseY() + 16);
		}
	}

	public void setItem(ItemHolder holder, int index) {
		if(index < 0 || index > 88)  {
			return;
		}

		this.slots[index].setItemHolder(holder);
	}

	public ItemHolder getItemHolder(int index) {
		if(index < 0 || index > 88) {
			return null;
		}

		return this.slots[index].getItemHolder();
	}

	public ItemHolder getActiveHolder() {
		return this.getItemHolder(this.currentSlot);
	}

	public Item getItem(int index) {
		ItemHolder holder = this.getItemHolder(index);

		if(holder == null) {
			return null;
		}

		return holder.getItem();
	}

	public boolean add(ItemHolder holder) { // TODO: check if item is already in inventory
		UiItemSlot slot = this.getFirstFreeSlot(UiItemSlot.Type.ANY);

		if(slot != null) {
			slot.setItemHolder(holder);
			return true;
		}

		return false;
	}

	public UiItemSlot getFirstFreeSlot(UiItemSlot.Type type) {
		switch(type) {
			case ACCESSORY: return this.getFirstFreeSlot(68, 73);
			case AMMO: return this.getFirstFreeSlot(54, 58);
			case ANY: default: return this.getFirstFreeSlot(0, 50); // Main inventory
			case COIN: return this.getFirstFreeSlot(50, 54);
			case ARMOR: return this.getFirstFreeSlot(59, 62);
			case TRASH: return null;
			case DYE: return this.getFirstFreeSlot(78, 83);
			case VANITY: return this.getFirstFreeSlot(73, 78);
		}
	}

	public UiItemSlot getFirstFreeSlot(int start, int end) {
		if(start > end || start < 0 || end > 88) {
			return null;
		}

		for(int i = start; i < end; i++) {
			if(this.slots[i].getItem() == null) {
				return this.slots[i];
			}
		}

		return null;
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