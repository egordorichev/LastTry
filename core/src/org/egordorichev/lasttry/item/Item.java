package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;

public class Item {
	protected short id;
	protected String name;
	protected Texture texture;
	protected Rarity rarity;
	protected float useDelay;
	protected int useSpeed;

	public Item(short id, String name, Rarity rarity, Texture texture) {
		if (Items.ITEM_CACHE[id] != null) {
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		Items.ITEM_CACHE[id] = this;

		this.texture = texture;
		this.id = id;
		this.name = name;
	}

	public Item(short id, String name, Texture texture) {
		this(id, name, Rarity.WHITE, texture);
	}

	public boolean use() {
		return false;
	}

	public void update(int dt) {
		if (this.isReady()) {
			return;
		}

		this.useDelay = Math.max(0, this.useDelay - 1);
		this.onUpdate();

		if (this.isReady()) {
			this.onUseEnd();
		}
	}

	protected boolean onUse() {
		return false;
	}

	protected void onUpdate() {

	}

	protected boolean onUseEnd() {
		return false;
	}

	public void renderAnimation() {

	}

	public short getID() {
		return this.id;
	}

	public Rarity getRarity() {
		return this.rarity;
	}

	public String getName() {
		return this.name;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public boolean canBeUsed() {
		return true;
	}

	public boolean isAutoUse() {
		return false;
	}

	public boolean isReady() {
		return this.useDelay == 0;
	}

	public static Item fromID(int id) {
		if (id <= 0 || id >= ItemID.count) {
			return null;
		}

		return Items.ITEM_CACHE[id];
	}

	public int getMaxInStack() {
		return 999;
	}
}
