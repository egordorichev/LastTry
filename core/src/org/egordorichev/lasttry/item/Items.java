package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.block.*;
import org.egordorichev.lasttry.item.block.plant.*;
import org.egordorichev.lasttry.item.items.*;
import org.egordorichev.lasttry.item.items.seeds.*;

public class Items {
	/** Items lookup. Items ID used as the index */
	public static final Item[] ITEM_CACHE = new Item[ItemID.count];
	
	public static final Item dirtWall;
	public static final Item dirtBlock;
	public static final Item grassBlock;
	public static final Item copperCoin;
	public static final Item silverCoin;
	public static final Item goldCoin;
	public static final Item platinumCoin;
	public static final Item woodenSword;
	public static final Item gel;
	public static final Item heart;
	public static final Item mana;
	public static final Item ebonstoneBlock;
	public static final Item corruptThornyBushes;
	public static final Item purpleIceBlock;
	public static final Item vileMushroom;
	public static final Item crimstoneBlock;
	public static final Item redIceBlock;
	public static final Item viciousMushroom;
	public static final Item sandBlock;
	public static final Item ebonsandBlock;
	public static final Item crimsandBlock;
	public static final Item stoneBlock;
	public static final Item ironPickaxe;
	public static final Item iceBlock;
	public static final Item dayBloom;
	public static final Item blinkRoot;
	public static final Item moonGlow;
	public static final Item deathWeed;
	public static final Item waterLeaf;
	public static final Item fireBlossom ;
	public static final Item silverThorn;
	public static final Item dayBloomSeeds;
	public static final Item blinkRootSeeds;
	public static final Item moonGlowSeeds;
	public static final Item deathWeedSeeds;
	public static final Item waterLeafSeeds;
	public static final Item fireBlossomSeeds;
	public static final Item silverThornSeeds;
	public static final Item mudBlock;
	public static final Item jungleGrassBlock;
	public static final Item jungleGrassSeeds;
	public static final Item ashBlock;
	public static final Item snowBlock;
	public static final Item copperShortSword;
	public static final Item copperPickaxe;
	public static final Item copperAxe;

	static {
		if (!Bootstrap.isLoaded()) {
			LastTry.error("Trying to access items class before bootstrap");
		}

		dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Textures.dirtWallIcon, Textures.dirtWall);
		dirtBlock= new BlockGround(ItemID.dirtBlock, "Dirt block", ToolPower.pickaxe(10), Textures.dirtIcon, Textures.dirt);
		grassBlock = new Grass(ItemID.grassBlock, "Grass block", Textures.grassIcon, Textures.grass);
		copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Textures.copperCoin);
		silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Textures.silverCoin);
		goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Textures.goldCoin);
		platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Textures.platinumCoin);
		woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Textures.woodenSword);
		gel = new org.egordorichev.lasttry.item.Item(ItemID.gel, "Gel", Textures.gel);
		heart = new org.egordorichev.lasttry.item.Item(ItemID.heart, "Heart", Textures.heart);
		mana = new org.egordorichev.lasttry.item.Item(ItemID.mana, "Mana", Textures.mana);
		ebonstoneBlock = new EvilBlock(ItemID.ebonstoneBlock, "Ebonstone block", ToolPower.pickaxe(65), Textures.ebonstoneIcon, Textures.ebonstone);
		corruptThornyBushes = new Block(ItemID.corruptThornyBushes, "Corrupt thorny bushes", false, ToolPower.pickaxe(10), null, Textures.corruptThornyBushes);
		purpleIceBlock = new EvilBlock(ItemID.purpleIceBlock, "Purple ice block", ToolPower.pickaxe(10), Textures.purpleIceIcon, Textures.purpleIce);
		vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Textures.vileMushroom);
		crimstoneBlock = new EvilBlock(ItemID.crimstoneBlock, "Crimstone block", ToolPower.pickaxe(65), Textures.crimstoneIcon, Textures.crimstone);
		redIceBlock = new EvilBlock(ItemID.redIceBlock, "Red ice block", ToolPower.pickaxe(10), Textures.redIceIcon, Textures.redIce);
		viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Textures.viciousMushroom);
		sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", ToolPower.pickaxe(10), Textures.sandBlockIcon, Textures.sandBlock);
		ebonsandBlock = new EvilBlock(ItemID.ebonsandBlock, "Ebonsand block", ToolPower.pickaxe(10), Textures.ebonsandIcon, Textures.ebonsand);
		crimsandBlock = new EvilBlock(ItemID.crimsandBlock, "Crimsand block", ToolPower.pickaxe(10), Textures.crimsandIcon, Textures.crimsand);
		stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", ToolPower.pickaxe(10), Textures.stoneIcon, Textures.stone);
		ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Textures.ironPickaxe);
		iceBlock = new SlippyBlock(ItemID.iceBlock, "Ice block", ToolPower.pickaxe(10), Textures.iceIcon, Textures.ice);
		dayBloom = new DayBloom();
		blinkRoot = new BlinkRoot();
		moonGlow = new MoonGlow();
		deathWeed = new DeathWeed();
		waterLeaf = new WaterLeaf();
		fireBlossom = new FireBlossom();
		silverThorn = new SilverThorn();
		dayBloomSeeds = new Seeds(ItemID.dayBloomSeeds, "Day Bloom Seeds", Textures.dayBloomSeeds, (Plant) Items.dayBloom);
		blinkRootSeeds = new Seeds(ItemID.blinkRootSeeds, "Blink Root Seeds", Textures.blinkRootSeeds, (Plant) Items.blinkRoot);
		moonGlowSeeds = new Seeds(ItemID.moonGlowSeeds, "Moon Glow Seeds", Textures.moonGlowSeeds, (Plant) Items.moonGlow);
		deathWeedSeeds = new Seeds(ItemID.deathWeedSeeds, "Death Weed Seeds", Textures.deathWeedSeeds, (Plant) Items.deathWeed);
		waterLeafSeeds = new Seeds(ItemID.waterLeafSeeds, "Water Leaf Seeds", Textures.waterLeafSeeds, (Plant) Items.waterLeaf);
		fireBlossomSeeds = new Seeds(ItemID.fireBlossomSeeds, "Fire Blossom Seeds", Textures.fireBlossomSeeds, (Plant) Items.fireBlossom);
		silverThornSeeds = new Seeds(ItemID.silverThornSeeds, "Silver Thorn Seeds", Textures.silverThornSeeds, (Plant) Items.silverThorn);
		mudBlock = new BlockGround(ItemID.mudBlock, "Mud block", ToolPower.pickaxe(10), Textures.mudIcon, Textures.mud);
		jungleGrassBlock = new JungleGrass();
		jungleGrassSeeds = new GrassSeeds(ItemID.jungleGrassSeeds, "Jungle Grass Seeds", Textures.jungleGrassSeeds, (Grass) Items.jungleGrassBlock);
		ashBlock = new BlockGround(ItemID.ashBlock, "Ash Block", ToolPower.pickaxe(10), Textures.ashBlockIcon, Textures.ashBlock);
		snowBlock = new BlockGround(ItemID.snowBlock, "Snow Block", ToolPower.pickaxe(10), Textures.snowBlockIcon, Textures.snowBlock);
		copperShortSword = new ShortSword(ItemID.copperShortSword, "Copper Short Sword", 5, 12, Textures.copperShortSword);
		copperPickaxe = new Pickaxe(ItemID.copperPickaxe, "Copper Pickaxe", 4, 35, 22, Textures.copperPickaxe);
		copperAxe = new Axe(ItemID.copperAxe, "Copper Axe", 3, 32, 29, Textures.copperAxe);
	}

	public static void load() {

	}
}