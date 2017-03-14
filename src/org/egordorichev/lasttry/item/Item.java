package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.item.blocks.BlockGround;
import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.Image;

public class Item {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static Item[] ITEM_CACHE = new Item[32];
	public static Item dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Assets.dirtWallTexture);
	public static Item dirtBlock  = new BlockGround(ItemID.dirtBlock, "Dirt block", true, Assets.dirtTileTexture);
	public static Item grassBlock = new BlockGround(ItemID.grassBlock, "Grass block", true, Assets.grassTileTexture);
	public static Item copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Assets.copperCoinTexture);
	public static Item silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Assets.silverCoinTexture);
	public static Item goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Assets.goldCoinTexture);
	public static Item platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Assets.platinumCoinTexture);

	/**
	 * Item identifier.
	 */
	protected int id;
	/**
	 * Item name.
	 */
	protected String name;
	/**
	 * Type of item. Possible values:
	 * <ul>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#ITEM Item}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#BLOCK Block}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#WALL Wall}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#TOOL Tool}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#CONSUMABLE Consumable}
	 * </li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#ARMOR Armor}</li>
	 * <li>{@link org.egordorichev.lasttry.item.Item.Type#ACCESSORY Accessory}
	 * </li>
	 * </ul>
	 */
	protected Type type;

	/**
	 * Item texture.
	 */
	protected Image texture;

	/**
	 * Type category enum.
	 */
	public enum Type {
		ITEM, BLOCK, WALL, TOOL, CONSUMABLE, ARMOR, ACCESSORY
	}

	public Item(int id, String name, Type type) {
		if (ITEM_CACHE[id] != null) {
			System.out.println("Item with id " + id + " already exists.");
			throw new RuntimeException("Item with id " + id + " already exists.");
		}
		ITEM_CACHE[id] = this;
		this.id = id;
		this.name = name;
		this.type = type;
	}

	/**
	 * TODO
	 */
	public void use() {
		// TODO
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
	 * Return the item's {@link #type}.
	 * 
	 * @return Item type.
	 */
	public Type getType() {
		return this.type;
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
	 * Returns if player can use {@link #item}
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
	public static Item fromId(int id) {
		if (id == 0) {
			return null;
		}

		return ITEM_CACHE[id];
	}

	/**
	 * Loads all fields
	 */
	public static void preload() {

	}
}