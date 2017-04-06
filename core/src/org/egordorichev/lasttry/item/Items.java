package org.egordorichev.lasttry.item;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.block.*;
import org.egordorichev.lasttry.item.block.plant.*;
import org.egordorichev.lasttry.item.block.workingstation.WorkBench;
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
	public static final Item livingWood;
	public static final Item wood;
	public static final Item workBench;

	static {
		if (!Bootstrap.isLoaded()) {
			LastTry.error("Trying to access items class before bootstrap");
		}

		dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Assets.getTexture(Textures.dirtWallIcon), Assets.getTexture(Textures.dirtWall));
		dirtBlock= new BlockGround(ItemID.dirtBlock, "Dirt block", ToolPower.pickaxe(10), Assets.getTexture(Textures.dirtIcon), Assets.getTexture(Textures.dirt));
		grassBlock = new Grass(ItemID.grassBlock, "Grass block", Assets.getTexture(Textures.grassIcon), Assets.getTexture(Textures.grass));
		copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Assets.getTexture(Textures.copperCoin));
		silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Assets.getTexture(Textures.silverCoin));
		goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Assets.getTexture(Textures.goldCoin));
		platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Assets.getTexture(Textures.platinumCoin));
		woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Assets.getTexture(Textures.woodenSword));
		gel = new org.egordorichev.lasttry.item.Item(ItemID.gel, "Gel", Assets.getTexture(Textures.gel));
		heart = new org.egordorichev.lasttry.item.Item(ItemID.heart, "Heart", Assets.getTexture(Textures.heart));
		mana = new org.egordorichev.lasttry.item.Item(ItemID.mana, "Mana", Assets.getTexture(Textures.mana));
		ebonstoneBlock = new EvilBlock(ItemID.ebonstoneBlock, "Ebonstone block", ToolPower.pickaxe(65), Assets.getTexture(Textures.ebonstoneIcon), Assets.getTexture(Textures.ebonstone));
		corruptThornyBushes = new Block(ItemID.corruptThornyBushes, "Corrupt thorny bushes", false, ToolPower.pickaxe(10), null, Assets.getTexture(Textures.corruptThornyBushes));
		purpleIceBlock = new EvilBlock(ItemID.purpleIceBlock, "Purple ice block", ToolPower.pickaxe(10), Assets.getTexture(Textures.purpleIceIcon), Assets.getTexture(Textures.purpleIce));
		vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Assets.getTexture(Textures.vileMushroom));
		crimstoneBlock = new EvilBlock(ItemID.crimstoneBlock, "Crimstone block", ToolPower.pickaxe(65), Assets.getTexture(Textures.crimstoneIcon), Assets.getTexture(Textures.crimstone));
		redIceBlock = new EvilBlock(ItemID.redIceBlock, "Red ice block", ToolPower.pickaxe(10), Assets.getTexture(Textures.redIceIcon), Assets.getTexture(Textures.redIce));
		viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Assets.getTexture(Textures.viciousMushroom));
		sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", ToolPower.pickaxe(10), Assets.getTexture(Textures.sandBlockIcon), Assets.getTexture(Textures.sandBlock));
		ebonsandBlock = new EvilBlock(ItemID.ebonsandBlock, "Ebonsand block", ToolPower.pickaxe(10), Assets.getTexture(Textures.ebonsandIcon), Assets.getTexture(Textures.ebonsand));
		crimsandBlock = new EvilBlock(ItemID.crimsandBlock, "Crimsand block", ToolPower.pickaxe(10), Assets.getTexture(Textures.crimsandIcon), Assets.getTexture(Textures.crimsand));
		stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", ToolPower.pickaxe(10), Assets.getTexture(Textures.stoneIcon), Assets.getTexture(Textures.stone));
		ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Assets.getTexture(Textures.ironPickaxe));
		iceBlock = new SlippyBlock(ItemID.iceBlock, "Ice block", ToolPower.pickaxe(10), Assets.getTexture(Textures.iceIcon), Assets.getTexture(Textures.ice));
		dayBloom = new DayBloom();
		blinkRoot = new BlinkRoot();
		moonGlow = new MoonGlow();
		deathWeed = new DeathWeed();
		waterLeaf = new WaterLeaf();
		fireBlossom = new FireBlossom();
		silverThorn = new SilverThorn();
		dayBloomSeeds = new Seeds(ItemID.dayBloomSeeds, "Day Bloom Seeds", Assets.getTexture(Textures.dayBloomSeeds), (Plant) Items.dayBloom);
		blinkRootSeeds = new Seeds(ItemID.blinkRootSeeds, "Blink Root Seeds", Assets.getTexture(Textures.blinkRootSeeds), (Plant) Items.blinkRoot);
		moonGlowSeeds = new Seeds(ItemID.moonGlowSeeds, "Moon Glow Seeds", Assets.getTexture(Textures.moonGlowSeeds), (Plant) Items.moonGlow);
		deathWeedSeeds = new Seeds(ItemID.deathWeedSeeds, "Death Weed Seeds", Assets.getTexture(Textures.deathWeedSeeds), (Plant) Items.deathWeed);
		waterLeafSeeds = new Seeds(ItemID.waterLeafSeeds, "Water Leaf Seeds", Assets.getTexture(Textures.waterLeafSeeds), (Plant) Items.waterLeaf);
		fireBlossomSeeds = new Seeds(ItemID.fireBlossomSeeds, "Fire Blossom Seeds", Assets.getTexture(Textures.fireBlossomSeeds), (Plant) Items.fireBlossom);
		silverThornSeeds = new Seeds(ItemID.silverThornSeeds, "Silver Thorn Seeds", Assets.getTexture(Textures.silverThornSeeds), (Plant) Items.silverThorn);
		mudBlock = new BlockGround(ItemID.mudBlock, "Mud block", ToolPower.pickaxe(10), Assets.getTexture(Textures.mudIcon), Assets.getTexture(Textures.mud));
		jungleGrassBlock = new JungleGrass();
		jungleGrassSeeds = new GrassSeeds(ItemID.jungleGrassSeeds, "Jungle Grass Seeds", Assets.getTexture(Textures.jungleGrassSeeds), (Grass) Items.jungleGrassBlock);
		ashBlock = new BlockGround(ItemID.ashBlock, "Ash Block", ToolPower.pickaxe(10), Assets.getTexture(Textures.ashBlockIcon), Assets.getTexture(Textures.ashBlock));
		snowBlock = new BlockGround(ItemID.snowBlock, "Snow Block", ToolPower.pickaxe(10), Assets.getTexture(Textures.snowBlockIcon), Assets.getTexture(Textures.snowBlock));
		copperShortSword = new ShortSword(ItemID.copperShortSword, "Copper Short Sword", 5, 12, Assets.getTexture(Textures.copperShortSword));
		copperPickaxe = new Pickaxe(ItemID.copperPickaxe, "Copper Pickaxe", 4, 35, 22, Assets.getTexture(Textures.copperPickaxe));
		copperAxe = new Axe(ItemID.copperAxe, "Copper Axe", 3, 32, 29, Assets.getTexture(Textures.copperAxe));
		livingWood = new LivingWood(ItemID.livingWood, "Living wood", Assets.getTexture(Textures.nullItem), Assets.getTexture(Textures.livingWood));
		wood = new Wood(ItemID.wood, "Wood", Assets.getTexture(Textures.woodIcon), Assets.getTexture(Textures.wood));
		workBench = new WorkBench(ItemID.workBench, "Work Bench", Assets.getTexture(Textures.workBench), 2, 1);
	}

	public static void load() {

	}
}