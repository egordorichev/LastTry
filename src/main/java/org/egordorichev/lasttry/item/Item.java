package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.items.Coin;
import org.egordorichev.lasttry.item.items.Pickaxe;
import org.egordorichev.lasttry.item.items.Sword;
import org.egordorichev.lasttry.item.tiles.BlockGround;
import org.egordorichev.lasttry.item.tiles.Wall;
import org.egordorichev.lasttry.graphics.Assets;
import org.newdawn.slick.Image;

public class Item {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static Item[] ITEM_CACHE = new Item[64];
	public static Item dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Assets.dirtWallIcon, Assets.dirtWallTexture);
	public static Item dirtBlock  = new BlockGround(ItemID.dirtBlock, "Dirt block", Assets.dirtIcon, Assets.dirtTexture);
	public static Item grassBlock = new BlockGround(ItemID.grassBlock, "Grass block", Assets.grassIcon, Assets.grassTexture);
	public static Item copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Assets.copperCoinTexture);
	public static Item silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Assets.silverCoinTexture);
	public static Item goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Assets.goldCoinTexture);
	public static Item platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Assets.platinumCoinTexture);
	public static Item woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Assets.woodenSwordTexture);
	public static Item gel = new Item(ItemID.gel, "Gel", Assets.gelTexture);
	public static Item heart = new Item(ItemID.heart, "Heart", Assets.heartTexture);
	public static Item mana = new Item(ItemID.mana, "Mana", Assets.manaTexture);
	public static Item ebonstoneBlock = new BlockGround(ItemID.ebonstoneBlock, "Ebonstone block", Assets.ebonstoneIcon, Assets.ebonstoneTexture);
	public static Item corruptThornyBushes = new ThornBlock(ItemID.corruptThornyBushes, "Corrupt thorny bushes", Assets.corruptThornyBushesTexture);
	public static Item purpleIceBlock = new SlippyBlock(ItemID.purpleIceBlock, "Purple ice block", Assets.purpleIceIcon, Assets.purpleIceTexture);
	public static Item vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Assets.vileMushroomTexture);
	public static Item crimstoneBlock = new BlockGround(ItemID.crimstoneBlock, "Crimstone block", Assets.crimstoneIcon, Assets.crimstoneTexture);
	public static Item redIceBlock = new SlippyBlock(ItemID.redIceBlock, "Red ice block", Assets.redIceIcon, Assets.redIceTexture);
	public static Item viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Assets.viciousMushroomTexture);
	public static Item sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", Assets.sandBlockIcon, Assets.sandBlockTexture);
	public static Item ebonsandBlock = new BlockGround(ItemID.ebonsandBlock, "Ebonsand block", Assets.ebonsandIcon, Assets.ebonsandTexture);
	public static Item crimsandBlock = new BlockGround(ItemID.crimsandBlock, "Crimsand block", Assets.crimsandIcon, Assets.crimsandTexture);
	public static Item stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", Assets.stoneIcon, Assets.stoneTexture);
	public static Item ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Assets.ironPickaxeTexture);

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

	/**
	 * Item rarity
	 */
	protected Rarity rarity;

	public Item(int id, String name, Rarity rarity, Image texture) {
		if (ITEM_CACHE[id] != null) {
			LastTry.log("Item with id " + id + " already exists.");
			throw new RuntimeException("Item with id " + id + " already exists.");
		}

		ITEM_CACHE[id] = this;

		this.texture = texture;
		this.id = id;
		this.name = name;
	}

	public Item(int id, String name, Image texture) {
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
