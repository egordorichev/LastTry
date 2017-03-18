package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.items.Coin;
import org.egordorichev.lasttry.item.tiles.BlockGround;
import org.egordorichev.lasttry.item.tiles.Wall;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Image;

public class Item {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static Item[] ITEM_CACHE = new Item[32];
	public static Item dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Assets.dirtWallIcon, Assets.dirtWallTexture);
	public static Item dirtBlock  = new BlockGround(ItemID.dirtBlock, "Dirt block", true, Assets.dirtTileIcon, Assets.dirtTileTexture);
	public static Item grassBlock = new BlockGround(ItemID.grassBlock, "Grass block", true, Assets.grassTileIcon, Assets.grassTileTexture);
	public static Item copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Assets.copperCoinTexture);
	public static Item silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Assets.silverCoinTexture);
	public static Item goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Assets.goldCoinTexture);
	public static Item platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Assets.platinumCoinTexture);

	// TODO

	// public static Item heart = new Item(ItemID.heart, "Heart", Assets.heartTexture);
	// public static Item mana = new Item(ItemID.mana, "Heart", Assets.manaTexture);

	/**
	 * Item identifier.
	 */
	protected int id;
	/**
	 * Item name.
	 */
	protected String name;
	/**
	 * Item texture.
	 */
	protected Image texture;

	public Item(int id, String name, Image texture) {
		if (ITEM_CACHE[id] != null) {
			LastTry.log("Item with id " + id + " already exists.");
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		ITEM_CACHE[id] = this;

		this.texture = texture;
		this.id = id;
		this.name = name;
	}

	/**
	 * Uses item
	 */
	public void use() {

	}

	/**
	 * Updates item if it is being used
	 * @param dt update delta
	 */
	public void update(int dt) {

	}

	/**
	 * Return the item's {@link #id ID}.
	 * 
	 * @return Item ID.
	 */
	public int getId() {
		return this.id;
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
	public Image getTexture() {
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

		return ITEM_CACHE[id];
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
