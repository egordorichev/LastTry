package org.egordorichev.lasttry.item;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.block.*;
import org.egordorichev.lasttry.item.block.plant.*;
import org.egordorichev.lasttry.item.items.*;
import org.egordorichev.lasttry.item.items.seeds.GrassSeeds;
import org.egordorichev.lasttry.item.items.seeds.Seeds;

public class ItemProvider {
	private static boolean loaded = false;

	public static void load() {
		if (loaded) {
			LastTry.warning("Trying to load items second time");
			return;
		}

		Items.dirtWall = new Wall(ItemID.dirtWall, "Dirt wall", Textures.dirtWallIcon, Textures.dirtWall);
		Items.dirtBlock= new BlockGround(ItemID.dirtBlock, "Dirt block", ToolPower.pickaxe(10), Textures.dirtIcon, Textures.dirt);
		Items.grassBlock = new Grass(ItemID.grassBlock, "Grass block", Textures.grassIcon, Textures.grass);
		Items.copperCoin = new Coin(ItemID.copperCoin, "Copper coin", Textures.copperCoin);
		Items.silverCoin = new Coin(ItemID.silverCoin, "Silver coin", Textures.silverCoin);
		Items.goldCoin = new Coin(ItemID.goldCoin, "Gold coin", Textures.goldCoin);
		Items.platinumCoin = new Coin(ItemID.platinumCoin, "Platinum coin", Textures.platinumCoin);
		Items.woodenSword = new Sword(ItemID.woodenSword, "Wooden Sword", 7.0f, 20, Textures.woodenSword);
		Items.gel = new org.egordorichev.lasttry.item.Item(ItemID.gel, "Gel", Textures.gel);
		Items.heart = new org.egordorichev.lasttry.item.Item(ItemID.heart, "Heart", Textures.heart);
		Items.mana = new org.egordorichev.lasttry.item.Item(ItemID.mana, "Mana", Textures.mana);
		Items.ebonstoneBlock = new EvilBlock(ItemID.ebonstoneBlock, "Ebonstone block", ToolPower.pickaxe(65), Textures.ebonstoneIcon, Textures.ebonstone);
		Items.corruptThornyBushes = new Block(ItemID.corruptThornyBushes, "Corrupt thorny bushes", false, ToolPower.pickaxe(10), null, Textures.corruptThornyBushes);
		Items.purpleIceBlock = new EvilBlock(ItemID.purpleIceBlock, "Purple ice block", ToolPower.pickaxe(10), Textures.purpleIceIcon, Textures.purpleIce);
		Items.vileMushroom = new Mushroom(ItemID.vileMushroom, "Vile mushroom", Textures.vileMushroom);
		Items.crimstoneBlock = new EvilBlock(ItemID.crimstoneBlock, "Crimstone block", ToolPower.pickaxe(65), Textures.crimstoneIcon, Textures.crimstone);
		Items.redIceBlock = new EvilBlock(ItemID.redIceBlock, "Red ice block", ToolPower.pickaxe(10), Textures.redIceIcon, Textures.redIce);
		Items.viciousMushroom = new Mushroom(ItemID.viciousMushroom, "Vicious mushroom", Textures.viciousMushroom);
		Items.sandBlock = new BlockGround(ItemID.sandBlock, "Sand block", ToolPower.pickaxe(10), Textures.sandBlockIcon, Textures.sandBlock);
		Items.ebonsandBlock = new EvilBlock(ItemID.ebonsandBlock, "Ebonsand block", ToolPower.pickaxe(10), Textures.ebonsandIcon, Textures.ebonsand);
		Items.crimsandBlock = new EvilBlock(ItemID.crimsandBlock, "Crimsand block", ToolPower.pickaxe(10), Textures.crimsandIcon, Textures.crimsand);
		Items.stoneBlock = new BlockGround(ItemID.stoneBlock, "Stone block", ToolPower.pickaxe(10), Textures.stoneIcon, Textures.stone);
		Items.ironPickaxe = new Pickaxe(ItemID.ironPickaxe, "Iron pickaxe", 5, 40, 19, Textures.ironPickaxe);
		Items.iceBlock = new SlippyBlock(ItemID.iceBlock, "Ice block", ToolPower.pickaxe(10), Textures.iceIcon, Textures.ice);
		Items.dayBloom = new DayBloom();
		Items.blinkRoot = new BlinkRoot();
		Items.moonGlow = new MoonGlow();
		Items.deathWeed = new DeathWeed();
		Items.waterLeaf = new WaterLeaf();
		Items.fireBlossom = new FireBlossom();
		Items.silverThorn = new SilverThorn();
		Items.dayBloomSeeds = new Seeds(ItemID.dayBloomSeeds, "Day Bloom Seeds", Textures.dayBloomSeeds, (Plant) Items.dayBloom);
		Items.blinkRootSeeds = new Seeds(ItemID.blinkRootSeeds, "Blink Root Seeds", Textures.blinkRootSeeds, (Plant) Items.blinkRoot);
		Items.moonGlowSeeds = new Seeds(ItemID.moonGlowSeeds, "Moon Glow Seeds", Textures.moonGlowSeeds, (Plant) Items.moonGlow);
		Items.deathWeedSeeds = new Seeds(ItemID.deathWeedSeeds, "Death Weed Seeds", Textures.deathWeedSeeds, (Plant) Items.deathWeed);
		Items.waterLeafSeeds = new Seeds(ItemID.waterLeafSeeds, "Water Leaf Seeds", Textures.waterLeafSeeds, (Plant) Items.waterLeaf);
		Items.fireBlossomSeeds = new Seeds(ItemID.fireBlossomSeeds, "Fire Blossom Seeds", Textures.fireBlossomSeeds, (Plant) Items.fireBlossom);
		Items.silverThornSeeds = new Seeds(ItemID.silverThornSeeds, "Silver Thorn Seeds", Textures.silverThornSeeds, (Plant) Items.silverThorn);
		Items.mudBlock = new BlockGround(ItemID.mudBlock, "Mud block", ToolPower.pickaxe(10), Textures.mudIcon, Textures.mud);
		Items.jungleGrassBlock = new JungleGrass();
		Items.jungleGrassSeeds = new GrassSeeds(ItemID.jungleGrassSeeds, "Jungle Grass Seeds", Textures.jungleGrassSeeds, (Grass) Items.jungleGrassBlock);
		Items.ashBlock = new BlockGround(ItemID.ashBlock, "Ash Block", ToolPower.pickaxe(10), Textures.ashBlockIcon, Textures.ashBlock);
		Items.snowBlock = new BlockGround(ItemID.snowBlock, "Snow Block", ToolPower.pickaxe(10), Textures.snowBlockIcon, Textures.snowBlock);
		Items.copperShortSword = new ShortSword(ItemID.copperShortSword, "Copper Short Sword", 5, 12, Textures.copperShortSword);
		Items.copperPickaxe = new Pickaxe(ItemID.copperPickaxe, "Copper Pickaxe", 4, 35, 22, Textures.copperPickaxe);
		Items.copperAxe = new Axe(ItemID.copperAxe, "Copper Axe", 3, 32, 29, Textures.copperAxe);
	}
}