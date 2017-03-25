package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.items.Coin;
import org.egordorichev.lasttry.item.items.Mushroom;
import org.egordorichev.lasttry.item.items.Pickaxe;
import org.egordorichev.lasttry.item.items.Sword;
import org.egordorichev.lasttry.item.items.ThornBlock;
import org.egordorichev.lasttry.item.block.BlockGround;
import org.egordorichev.lasttry.item.block.SlippyBlock;
import org.egordorichev.lasttry.item.block.Wall;
import org.egordorichev.lasttry.graphics.Textures;

public class Item {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static Item[] ITEM_CACHE = new Item[ItemID.count];
	public static Item dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Textures.dirtWallIcon, Textures.dirtWall);
	public static Item dirtBlock  = new BlockGround(ItemID.dirtBlock, "Dirt block", Textures.dirtIcon, Textures.dirt);
	public static Item grassBlock = new BlockGround(ItemID.grassBlock, "Grass block", Textures.grassIcon, Textures.grass);
	public static Item copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Textures.copperCoin);
	public static Item silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Textures.silverCoin);
	public static Item goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Textures.goldCoin);
	public static Item platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Textures.platinumCoin);
	public static Item woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Textures.woodenSword);
	public static Item gel = new Item(ItemID.gel, "Gel", Textures.gel);
	public static Item heart = new Item(ItemID.heart, "Heart", Textures.heart);
	public static Item mana = new Item(ItemID.mana, "Mana", Textures.mana);
	public static Item ebonstoneBlock = new BlockGround(ItemID.ebonstoneBlock, "Ebonstone block", Textures.ebonstoneIcon, Textures.ebonstone);
	public static Item corruptThornyBushes = new ThornBlock(ItemID.corruptThornyBushes, "Corrupt thorny bushes", Textures.corruptThornyBushes);
	public static Item purpleIceBlock = new SlippyBlock(ItemID.purpleIceBlock, "Purple ice block", Textures.purpleIceIcon, Textures.purpleIce);
	public static Item vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Textures.vileMushroom);
	public static Item crimstoneBlock = new BlockGround(ItemID.crimstoneBlock, "Crimstone block", Textures.crimstoneIcon, Textures.crimstone);
	public static Item redIceBlock = new SlippyBlock(ItemID.redIceBlock, "Red ice block", Textures.redIceIcon, Textures.redIce);
	public static Item viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Textures.viciousMushroom);
	public static Item sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", Textures.sandBlockIcon, Textures.sandBlock);
	public static Item ebonsandBlock = new BlockGround(ItemID.ebonsandBlock, "Ebonsand block", Textures.ebonsandIcon, Textures.ebonsand);
	public static Item crimsandBlock = new BlockGround(ItemID.crimsandBlock, "Crimsand block", Textures.crimsandIcon, Textures.crimsand);
	public static Item stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", Textures.stoneIcon, Textures.stone);
	public static Item ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Textures.ironPickaxe);

	/**
	 * Item identifier.
	 */
	protected short id;
	/**
	 * Item name.
	 */
	protected String name;
	/**
	 * Item texture.
	 */
	protected Texture texture;

	/**
	 * Item rarity
	 */
	protected Rarity rarity;

	public Item(short id, String name, Rarity rarity, Texture texture) {
		if (ITEM_CACHE[id] != null) {
			LastTry.log("Item with id " + id + " already exists.");
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		ITEM_CACHE[id] = this;

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

	/**
	 * Return the item's {@link #id ID}.
	 * 
	 * @return Item ID.
	 */
	public int getId() {
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