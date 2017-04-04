package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.block.*;
import org.egordorichev.lasttry.item.block.plant.*;
import org.egordorichev.lasttry.item.items.*;
import org.egordorichev.lasttry.item.items.seeds.GrassSeeds;
import org.egordorichev.lasttry.item.items.seeds.Seeds;

public class Item {

	/** Item identifier */
	protected short id;

	/** Item name */
	protected String name;

	/** Item texture */
	protected Texture texture;

	/** Item rarity */
	protected Rarity rarity;

	/** Time left for item be ready */
	protected float useDelay;

	/** Item use speed */
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

	}

	/** Renders the item in player hands */
	public void renderAnimation() {

	}

	/**
	 * Return the item's {@link #id ID}.
	 *
	 * @return Item ID.
	 */
	public short getId() {
		return this.id;
	}

	/**
	 * Return the item's rarity
	 * @return Item rarity
	 */
	public Rarity getRarity() {
		return this.rarity;
	}

	/**
	 * Return the item's {@link #name}.
	 *
	 * @return Item name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Return the items's {@link #texture}
	 *
	 * @return Item texture
	 */
	public Texture getTexture() {
		return this.texture;
	}

	/**
	 * Returns if player can use item
	 *
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
	 *
	 * @param id
	 *            Item ID.
	 * @return Item instance.
	 */
	public static Item fromID(int id) {
		if (id == 0) {
			return null;
		}

		return Items.ITEM_CACHE[id];
	}

	/**
	 * Returns max item in stack
	 */
	public int getMaxInStack() {
		return 1;
	}

	/**
	 * Loads all fields
	 */
	public static void preload() {

	}
}
