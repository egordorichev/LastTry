package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.block.*;
import org.egordorichev.lasttry.item.block.plant.*;
import org.egordorichev.lasttry.item.items.*;
import org.egordorichev.lasttry.item.items.seeds.GrassSeeds;
import org.egordorichev.lasttry.item.items.seeds.Seeds;

public class Items {
	/**
	 * Item lookup. Item ID used as the index.
	 */
	public static Item[] ITEM_CACHE = new Item[ItemID.count];
	public static Item dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Textures.dirtWallIcon, Textures.dirtWall);
	public static Item dirtBlock  = new BlockGround(ItemID.dirtBlock, "Dirt block", ToolPower.pickaxe(10), Textures.dirtIcon, Textures.dirt);
	public static Item grassBlock = new Grass(ItemID.grassBlock, "Grass block", Textures.grassIcon, Textures.grass);
	public static Item copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Textures.copperCoin);
	public static Item silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Textures.silverCoin);
	public static Item goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Textures.goldCoin);
	public static Item platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Textures.platinumCoin);
	public static Item woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Textures.woodenSword);
	public static Item gel = new Item(ItemID.gel, "Gel", Textures.gel);
	public static Item heart = new Item(ItemID.heart, "Heart", Textures.heart);
	public static Item mana = new Item(ItemID.mana, "Mana", Textures.mana);
	public static Item ebonstoneBlock = new EvilBlock(ItemID.ebonstoneBlock, "Ebonstone block", ToolPower.pickaxe(65), Textures.ebonstoneIcon, Textures.ebonstone);
	public static Item corruptThornyBushes = new Block(ItemID.corruptThornyBushes, "Corrupt thorny bushes", false, ToolPower.pickaxe(10), null, Textures.corruptThornyBushes);
	public static Item purpleIceBlock = new EvilBlock(ItemID.purpleIceBlock, "Purple ice block", ToolPower.pickaxe(10), Textures.purpleIceIcon, Textures.purpleIce);
	public static Item vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Textures.vileMushroom);
	public static Item crimstoneBlock = new EvilBlock(ItemID.crimstoneBlock, "Crimstone block", ToolPower.pickaxe(65), Textures.crimstoneIcon, Textures.crimstone);
	public static Item redIceBlock = new EvilBlock(ItemID.redIceBlock, "Red ice block", ToolPower.pickaxe(10), Textures.redIceIcon, Textures.redIce);
	public static Item viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Textures.viciousMushroom);
	public static Item sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", ToolPower.pickaxe(10), Textures.sandBlockIcon, Textures.sandBlock);
	public static Item ebonsandBlock = new EvilBlock(ItemID.ebonsandBlock, "Ebonsand block", ToolPower.pickaxe(10), Textures.ebonsandIcon, Textures.ebonsand);
	public static Item crimsandBlock = new EvilBlock(ItemID.crimsandBlock, "Crimsand block", ToolPower.pickaxe(10), Textures.crimsandIcon, Textures.crimsand);
	public static Item stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", ToolPower.pickaxe(10), Textures.stoneIcon, Textures.stone);
	public static Item ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Textures.ironPickaxe);
	public static Item iceBlock = new SlippyBlock(ItemID.iceBlock, "Ice block", ToolPower.pickaxe(10), Textures.iceIcon, Textures.ice);
	public static Item dayBloom = new DayBloom();
	public static Item blinkRoot = new BlinkRoot();
	public static Item moonGlow = new MoonGlow();
	public static Item deathWeed = new DeathWeed();
	public static Item waterLeaf = new WaterLeaf();
	public static Item fireBlossom = new FireBlossom();
	public static Item silverThorn = new SilverThorn();
	public static Item dayBloomSeeds = new Seeds(ItemID.dayBloomSeeds, "Day Bloom Seeds", Textures.dayBloomSeeds, (Plant) dayBloom);
	public static Item blinkRootSeeds = new Seeds(ItemID.blinkRootSeeds, "Blink Root Seeds", Textures.blinkRootSeeds, (Plant) blinkRoot);
	public static Item moonGlowSeeds = new Seeds(ItemID.moonGlowSeeds, "Moon Glow Seeds", Textures.moonGlowSeeds, (Plant) moonGlow);
	public static Item deathWeedSeeds = new Seeds(ItemID.deathWeedSeeds, "Death Weed Seeds", Textures.deathWeedSeeds, (Plant) deathWeed);
	public static Item waterLeafSeeds = new Seeds(ItemID.waterLeafSeeds, "Water Leaf Seeds", Textures.waterLeafSeeds, (Plant) waterLeaf);
	public static Item fireBlossomSeeds = new Seeds(ItemID.fireBlossomSeeds, "Fire Blossom Seeds", Textures.fireBlossomSeeds, (Plant) fireBlossom);
	public static Item silverThornSeeds = new Seeds(ItemID.silverThornSeeds, "Silver Thorn Seeds", Textures.silverThornSeeds, (Plant) silverThorn);
	public static Item mudBlock = new BlockGround(ItemID.mudBlock, "Mud block", ToolPower.pickaxe(10), Textures.mudIcon, Textures.mud);
	public static Item jungleGrassBlock = new JungleGrass();
	public static Item jungleGrassSeeds = new GrassSeeds(ItemID.jungleGrassSeeds, "Jungle Grass Seeds", Textures.jungleGrassSeeds, (Grass) jungleGrassBlock);
	public static Item ashBlock = new BlockGround(ItemID.ashBlock, "Ash Block", ToolPower.pickaxe(10), Textures.ashBlockIcon, Textures.ashBlock);
	public static Item snowBlock = new BlockGround(ItemID.snowBlock, "Snow Block", ToolPower.pickaxe(10), Textures.snowBlockIcon, Textures.snowBlock);

}
