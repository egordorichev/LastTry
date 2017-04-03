package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.player.Player;
import org.egordorichev.lasttry.item.items.*;
import org.egordorichev.lasttry.item.block.*;
import org.egordorichev.lasttry.item.block.plant.*;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.items.seeds.GrassSeeds;
import org.egordorichev.lasttry.item.items.seeds.Seeds;

public class Item {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static Item[] ITEM_CACHE = new Item[ItemID.count];
	public static Item dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Textures.dirtWallIcon, Textures.dirtWall);
	public static Item dirtBlock  = new BlockGround(ItemID.dirtBlock, "Dirt block", Textures.dirtIcon, Textures.dirt);
	public static Item grassBlock = new Grass(ItemID.grassBlock, "Grass block", Textures.grassIcon, Textures.grass);
	public static Item copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Textures.copperCoin);
	public static Item silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Textures.silverCoin);
	public static Item goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Textures.goldCoin);
	public static Item platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Textures.platinumCoin);
	public static Item woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Textures.woodenSword);
	public static Item gel = new Item(ItemID.gel, "Gel", Textures.gel);
	public static Item heart = new Item(ItemID.heart, "Heart", Textures.heart);
	public static Item mana = new Item(ItemID.mana, "Mana", Textures.mana);
	public static Item ebonstoneBlock = new EvilBlock(ItemID.ebonstoneBlock, "Ebonstone block", Textures.ebonstoneIcon, Textures.ebonstone);
	public static Item corruptThornyBushes = new ThornBlock(ItemID.corruptThornyBushes, "Corrupt thorny bushes", Textures.corruptThornyBushes);
	public static Item purpleIceBlock = new EvilBlock(ItemID.purpleIceBlock, "Purple ice block", Textures.purpleIceIcon, Textures.purpleIce);
	public static Item vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Textures.vileMushroom);
	public static Item crimstoneBlock = new EvilBlock(ItemID.crimstoneBlock, "Crimstone block", Textures.crimstoneIcon, Textures.crimstone);
	public static Item redIceBlock = new EvilBlock(ItemID.redIceBlock, "Red ice block", Textures.redIceIcon, Textures.redIce);
	public static Item viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Textures.viciousMushroom);
	public static Item sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", Textures.sandBlockIcon, Textures.sandBlock);
	public static Item ebonsandBlock = new EvilBlock(ItemID.ebonsandBlock, "Ebonsand block", Textures.ebonsandIcon, Textures.ebonsand);
	public static Item crimsandBlock = new EvilBlock(ItemID.crimsandBlock, "Crimsand block", Textures.crimsandIcon, Textures.crimsand);
	public static Item stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", Textures.stoneIcon, Textures.stone);
	public static Item ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Textures.ironPickaxe);
	public static Item iceBlock = new SlippyBlock(ItemID.iceBlock, "Ice block", Textures.iceIcon, Textures.ice);
	public static Item dayBloom = new DayBloom();
	public static Item blinkRoot = new BlinkRoot();
	public static Item moonGlow = new MoonGlow();
	public static Item deathWeed = new DeathWeed();
	public static Item waterLeaf = new WaterLeaf();
	public static Item fireBlossom = new FireBlossom();
	public static Item silverThorn = new SilverThorn();
	public static Item dayBloomSeeds = new Seeds(ItemID.dayBloomSeeds, "Day Bloom Seeds", Textures.dayBloomSeeds, (Plant) Item.dayBloom);
	public static Item blinkRootSeeds = new Seeds(ItemID.blinkRootSeeds, "Blink Root Seeds", Textures.blinkRootSeeds, (Plant) Item.blinkRoot);
	public static Item moonGlowSeeds = new Seeds(ItemID.moonGlowSeeds, "Moon Glow Seeds", Textures.moonGlowSeeds, (Plant) Item.moonGlow);
	public static Item deathWeedSeeds = new Seeds(ItemID.deathWeedSeeds, "Death Weed Seeds", Textures.deathWeedSeeds, (Plant) Item.deathWeed);
	public static Item waterLeafSeeds = new Seeds(ItemID.waterLeafSeeds, "Water Leaf Seeds", Textures.waterLeafSeeds, (Plant) Item.waterLeaf);
	public static Item fireBlossomSeeds = new Seeds(ItemID.fireBlossomSeeds, "Fire Blossom Seeds", Textures.fireBlossomSeeds, (Plant) Item.fireBlossom);
	public static Item silverThornSeeds = new Seeds(ItemID.silverThornSeeds, "Silver Thorn Seeds", Textures.silverThornSeeds, (Plant) Item.silverThorn);
	public static Item mudBlock = new BlockGround(ItemID.mudBlock, "Mud block", Textures.mudIcon, Textures.mud);
	public static Item jungleGrassBlock = new JungleGrass();
	public static Item jungleGrassSeeds = new GrassSeeds(ItemID.jungleGrassSeeds, "Jungle Grass Seeds", Textures.jungleGrassSeeds, (Grass) Item.jungleGrassBlock);
	public static Item ashBlock = new BlockGround(ItemID.ashBlock, "Ash Block", Textures.ashBlockIcon, Textures.ashBlock);
	public static Item snowBlock = new BlockGround(ItemID.snowBlock, "Snow Block", Textures.snowBlockIcon, Textures.snowBlock);

	/** Item identifier */
	protected short id;

	/** Item name */
	protected String name;

	/** Item texture */
	protected Texture texture;

	/** Item rarity */
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