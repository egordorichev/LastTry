package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;

public class Item {
	/** Items identifier */
	protected short id;

	/** Items name */
	protected String name;

	/** Items texture */
	protected Texture texture;

	/** Items rarity */
	protected Rarity rarity;

	/** Time left for item be ready */
	protected float useDelay;

	/** Items use speed */
	protected int useSpeed;

	public Item(short id, String name, Rarity rarity, Texture texture) {
		if (Items.ITEM_CACHE[id] != null) {
			throw new RuntimeException("Items with id " + id + " already exists.");
		}

		Items.ITEM_CACHE[id] = this;

		this.texture = texture;
		this.id = id;
		this.name = name;
	}

	public Item(short id, String name, Texture texture) {
		this(id, name, Rarity.WHITE, texture);
	}

	/**
	 * Use item
	 * @return true if need to remove item from inventory
	 */
	public boolean use() {
		return false;
	}

	/**
	 * Updates item if it is being used
	 * @param dt The milliseconds passed since the last update.
	 */
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

	/** Renders the item in player hands */
	public void renderAnimation() {

	}

	/**
	 * Return the item's {@link #id ID}.
	 * @return Items ID.
	 */
	public short getId() {
		return this.id;
	}

	/**
	 * Return the item's rarity
	 * @return Items rarity
	 */
	public Rarity getRarity() {
		return this.rarity;
	}

	/**
	 * Return the item's {@link #name}.
	 * @return Items name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the items's {@link #texture}
	 * @return Items texture
	 */
	public Texture getTexture() {
		return this.texture;
	}

	/**
	 * Returns if player can use item
	 * @return True if player can use item
	 */
	public boolean canBeUsed() { // This is method, because it can depend on time and stuff
		return true;
	}

	/**
	 * Returns true, if item can be used continuously with single mouse click
	 * @return if item can be used continuously with single mouse click
	 */
	public boolean isAutoUse() {
		return false;
	}

	/**
	 * Returns true, if item can be used
	 * @return if item can be used
	 */
	public boolean isReady() {
		return this.useDelay == 0;
	}

	/**
	 * Retrieve an item instance from an item identifier.
	 * @param id Items ID.
	 * @return Items instance.
	 */
	public static Item fromID(int id) {
		if (id == 0) {
			return null;
		}

		return Items.ITEM_CACHE[id];
	}

	/** Returns max item in stack */
	public int getMaxInStack() {
		return 1;
	}
}
