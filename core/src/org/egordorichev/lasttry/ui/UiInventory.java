package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.input.DefaultInputProcessor;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.inventory.Inventory;
import org.egordorichev.lasttry.inventory.InventoryOwner;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.language.Language;
import org.egordorichev.lasttry.util.Util;

public class UiInventory extends UiComponent implements UiScreen, UiToggleScreen, Inventory<UiItemSlot> {
	/**
	 * The active slot on the hotbar.
	 */
	public int activeSlot = 0;
	public UiItemSlot[] slots;
	/**
	 * The item currently clicked in the inventory. This is not to be confused
	 * with the current active item which is what the entity uses with their
	 * main action input <i>(Like a pickaxe being used for mining)</i>
	 */
	private ItemHolder selectedItem = new ItemHolder(null, 0);
	/**
	 * The entity that owns the inventory.
	 */
	private InventoryOwner<UiItemSlot> owner;
	private boolean open;

	public UiInventory(int size, InventoryOwner<UiItemSlot> owner) {
		super(new Rectangle(0, 0, 0, 0));
		this.owner = owner;
		this.slots = new UiItemSlot[88];

		int x = 10;
		int y = 30;

		for (int i = 0; i < 10; i++) { // Hot bar
			this.slots[i] = new UiItemSlot(new Rectangle(x + i * 54, y, 52, 52), UiItemSlot.Type.ANY, i);
		}

		for (int i = 10; i < 50; i++) { // Main inventory
			this.slots[i] = new UiItemSlot(new Rectangle(x + (i % 10) * 54, y + 54 * (i / 10), 52, 52),
					UiItemSlot.Type.ANY, i);
		}

		for (int i = 50; i < 54; i++) { // Coins
			this.slots[i] = new UiItemSlot(new Rectangle(x + 542, y + 100 + (i - 50) * 34, 32, 32),
					UiItemSlot.Type.COIN, i);
		}

		for (int i = 54; i < 58; i++) { // Ammo
			this.slots[i] = new UiItemSlot(new Rectangle(x + 576, y + 100 + (i - 54) * 34, 32, 32),
					UiItemSlot.Type.AMMO, i);
		}

		// Trash
		this.slots[58] = new UiItemSlot(new Rectangle(x + 486, y + 270, 52, 52), UiItemSlot.Type.TRASH, Origin.TOP_LEFT,
				new TextureRegion(Assets.getTexture("trash"), 0, 0, 32, 32), 58);

		for (int i = 59; i < 62; i++) { // Armor
			this.slots[i] = new UiItemSlot(new Rectangle(10, 280 + (i - 59) * 54, 52, 52), UiItemSlot.Type.ARMOR,
					Origin.BOTTOM_RIGHT,
					new TextureRegion(Assets.getTexture("inventory_back"), 0, 68 - (i - 59) * 34, 34, 34), i);
		}

		for (int i = 62; i < 65; i++) { // Vanity Armor
			this.slots[i] = new UiItemSlot(new Rectangle(64, 280 + (i - 62) * 54, 52, 52), UiItemSlot.Type.VANITY,
					Origin.BOTTOM_RIGHT,
					new TextureRegion(Assets.getTexture("inventory_back"), 0, 170 - (i - 62) * 34, 34, 34), i);
		}

		for (int i = 65; i < 68; i++) { // Armor Dye
			this.slots[i] = new UiItemSlot(new Rectangle(118, 280 + (i - 65) * 54, 52, 52), UiItemSlot.Type.DYE,
					Origin.BOTTOM_RIGHT, new TextureRegion(Assets.getTexture("inventory_back"), 34, 0, 34, 34), i);
		}

		for (int i = 68; i < 73; i++) { // Accessories
			this.slots[i] = new UiItemSlot(new Rectangle(10, 10 + (i - 68) * 54, 52, 52), UiItemSlot.Type.ACCESSORY,
					Origin.BOTTOM_RIGHT, new TextureRegion(Assets.getTexture("inventory_back"), 68, 34, 34, 34), i);
		}

		for (int i = 73; i < 78; i++) { // Vanity Accessories
			this.slots[i] = new UiItemSlot(new Rectangle(64, 10 + (i - 73) * 54, 52, 52),
					UiItemSlot.Type.VANITY_ACCESSORY, Origin.BOTTOM_RIGHT,
					new TextureRegion(Assets.getTexture("inventory_back"), 68, 0, 34, 34), i);
		}

		for (int i = 78; i < 83; i++) { // Accessories Dyes
			this.slots[i] = new UiItemSlot(new Rectangle(118, 10 + (i - 78) * 54, 52, 52), UiItemSlot.Type.DYE,
					Origin.BOTTOM_RIGHT, new TextureRegion(Assets.getTexture("inventory_back"), 34, 0, 34, 34), i);
		}

		for (int i = 83; i < 88; i++) { // Equipment
			this.slots[i] = new UiItemSlot(new Rectangle(0, 0, 52, 52), UiItemSlot.Type.ANY, i); // TODO
			this.slots[i].hide();
		}

		this.slots[activeSlot].setActive(true);

		InputManager.multiplexer.addProcessor(new DefaultInputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				switch (keycode) {
					case Keys.HOTBAR_SLOT_0:
						setHotbarSlot(0);
						break;
					case Keys.HOTBAR_SLOT_1:
						setHotbarSlot(1);
						break;
					case Keys.HOTBAR_SLOT_2:
						setHotbarSlot(2);
						break;
					case Keys.HOTBAR_SLOT_3:
						setHotbarSlot(3);
						break;
					case Keys.HOTBAR_SLOT_4:
						setHotbarSlot(4);
						break;
					case Keys.HOTBAR_SLOT_5:
						setHotbarSlot(5);
						break;
					case Keys.HOTBAR_SLOT_6:
						setHotbarSlot(6);
						break;
					case Keys.HOTBAR_SLOT_7:
						setHotbarSlot(7);
						break;
					case Keys.HOTBAR_SLOT_8:
						setHotbarSlot(8);
						break;
					case Keys.HOTBAR_SLOT_9:
						setHotbarSlot(9);
						break;
				}

				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				ItemHolder holder = getActiveItem();

				if (holder != null && holder.getItem() != null && !holder.getItem().isReady()) {
					return false;
				}

				slots[activeSlot].setActive(false);
				activeSlot += (amount > 0) ? 1 : -1;

				if (activeSlot > 9) {
					activeSlot = 0;
				} else if (activeSlot < 0) {
					activeSlot = 9;
				}

				slots[activeSlot].setActive(true);

				return false;
			}
		});
	}

	private void updateItems() {
		if (InputManager.isMouseButtonPressed(Input.Buttons.LEFT)
				|| InputManager.isMouseButtonPressed(Input.Buttons.RIGHT)) {

			if (this.isOpen()) {
				return;
			}

			ItemHolder holder = getActiveItem();

			if (holder != null) {
				Item item = holder.getItem();

				if (item != null && item.isReady() && (item.isAutoUse() || InputManager.mouseButtonJustPressed())) {
					short x = (short) (LastTry.getMouseXInWorld() / Block.SIZE);
					short y = (short) (LastTry.getMouseYInWorld() / Block.SIZE);

					if (item.canBeUsed(x, y) && item.use(x, y)) {
						int count = holder.getCount();

						if (count == 1) {
							slots[activeSlot].setItemHolder(new ItemHolder(null, 0));
						} else {
							holder.setCount(count - 1);
						}
					}
				}
			}
		}
	}

	@Override
	public void render() {
		super.render();
		this.updateItems();

		ItemHolder holder = this.slots[this.activeSlot].getItemHolder();

		if (holder.isEmpty()) {
			Util.drawWithShadow(Assets.f22, Language.text.get("inventory"), 10, Gdx.graphics.getHeight() - 8);
		} else {
			Util.drawWithShadow(Assets.f22, holder.asInfo(), 10, Gdx.graphics.getHeight() - 8);
		}

		for (int i = 0; i < 10; i++) {
			this.slots[i].render();
		}

		if (this.isOpen()) {
			for (int i = 10; i < 88; i++) {
				this.slots[i].render();
			}
		}

		if (getSelectedItem() != null) {
			getSelectedItem().renderAt((int) InputManager.getMousePosition().x + 16,
					Gdx.graphics.getHeight() - (int) InputManager.getMousePosition().y - 16);
		}
	}

	@Override
	public UiItemSlot getFirstFreeSlot(UiItemSlot.Type type) {
		switch (type) {
			case ACCESSORY:
				return this.getFirstFreeSlot(68, 73);
			case AMMO:
				return this.getFirstFreeSlot(54, 58);
			case ANY:
			default:
				return this.getFirstFreeSlot(0, 50); // Main inventory
			case COIN:
				return this.getFirstFreeSlot(50, 54);
			case ARMOR:
				return this.getFirstFreeSlot(59, 62);
			case TRASH:
				return null;
			case DYE:
				return this.getFirstFreeSlot(78, 83);
			case VANITY:
				return this.getFirstFreeSlot(73, 78);
		}
	}

	private void setHotbarSlot(int slot) throws IllegalArgumentException {
		if (slot > 10) {
			throw new IllegalArgumentException("Slot is out of range");
		}

		slots[activeSlot].setActive(false);
		activeSlot = slot;
		slots[activeSlot].setActive(true);
	}

	public void trash(ItemHolder holder) {
		this.slots[58].setItemHolder(holder);
	}

	@Override
	public void onUIOpen() {
		this.open = true;
	}

	@Override
	public void onUIClose() {
		this.open = false;
	}

	public UiItemSlot[] getSlots() {
		return slots;
	}

	@Override
	public boolean isOpen() {
		return open;
	}

	@Override
	public void setOpen(boolean open) {
		this.open = open;
	}

	@Override
	public InventoryOwner<UiItemSlot> getOwner() {
		return owner;
	}

	@Override
	public UiItemSlot getSlot(int index) {
		return slots[index];
	}

	@Override
	public ItemHolder getSelectedItem() {
		return selectedItem;
	}

	@Override
	public void setSelectedItem(ItemHolder selectedItem) {
		this.selectedItem = selectedItem;
	}

	@Override
	public int getActiveSlot() {
		return this.activeSlot;
	}

	@Override
	public int getMaxInventorySize() {
		return 88;
	}
}
