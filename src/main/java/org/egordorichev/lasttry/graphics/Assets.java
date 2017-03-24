package org.egordorichev.lasttry.graphics;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.*;
import org.newdawn.slick.util.ResourceLoader;

public class Assets {
	public static FontWrapper font = loadFont("font.ttf", java.awt.Font.PLAIN, 22, java.awt.Color.white);
	public static FontWrapper largeFont = loadFont("font.ttf", java.awt.Font.PLAIN, 26, java.awt.Color.white);
	public static FontWrapper smallFont = loadFont("font.ttf", java.awt.Font.PLAIN, 18, java.awt.Color.white);

	public static Image dirtTexture = loadImage("DirtTile.png");
	public static Image dirtIcon = loadImage("DirtIcon.png");
	public static Image grassTexture = loadImage("GrassTile.png");
	public static Image grassIcon = loadImage("GrassIcon.png");
	public static Image ebonstoneIcon = loadImage("EbonstoneIcon.png");
	public static Image ebonstoneTexture = loadImage("EbonstoneTile.png");
	public static Image corruptThornyBushesTexture = loadImage("EbonstoneTile.png");
	public static Image purpleIceIcon = loadImage("PurpleIceIcon.png");
	public static Image purpleIceTexture = loadImage("PurpleIceTile.png");
	public static Image vileMushroomTexture = loadImage("VileMushroom.png");
	public static Image crimstoneIcon = loadImage("CrimstoneIcon.png");
	public static Image crimstoneTexture = loadImage("CrimstoneTile.png");
	public static Image redIceIcon = loadImage("RedIceIcon.png");
	public static Image redIceTexture = loadImage("RedIceTile.png");
	public static Image viciousMushroomTexture = loadImage("ViciousMushroom.png");
	public static Image sandBlockIcon = loadImage("SandIcon.png");
	public static Image sandBlockTexture = loadImage("SandTile.png");
	public static Image ebonsandIcon = loadImage("EbonsandIcon.png");
	public static Image ebonsandTexture = loadImage("EbonsandTile.png");
	public static Image crimsandIcon = loadImage("CrimsandIcon.png");
	public static Image crimsandTexture = loadImage("CrimsandTile.png");
	public static Image stoneIcon = loadImage("StoneIcon.png");
	public static Image stoneTexture = loadImage("StoneTile.png");
	public static Image ironPickaxeTexture = loadImage("IronPickaxe.png");

	public static Image dirtWallTexture = loadImage("DirtWall.png");
	public static Image dirtWallIcon = loadImage("DirtWallIcon.png");
	public static Image greenSlimeTexture = loadImage("GreenSlime.png");
	public static Image blueSlimeTexture = loadImage("BlueSlime.png");
	public static Image playerTexture = loadImage("Player.png");
	public static Image eyeOfCthulhuTexture = loadImage("EyeOfCthulhu.png");
	public static Image copperCoinTexture = loadImage("CopperCoin.png");
	public static Image silverCoinTexture = loadImage("SilverCoin.png");
	public static Image goldCoinTexture = loadImage("GoldCoin.png");
	public static Image platinumCoinTexture = loadImage("PlatinumCoin.png");
	public static Image woodenSwordTexture = loadImage("WoodenSword.png");
	public static Image gelTexture = loadImage("Gel.png");
	public static Image heartTexture = loadImage("Heart.png");
	public static Image manaTexture = loadImage("Mana.png");
	public static Image hpTexture = loadImage("HP.png");
	public static Image radialTexture = loadImage("Radial.png");
	public static Image inventorySlot1Texture = loadImage("InventorySlot1.png");
	public static Image inventorySlot2Texture = loadImage("InventorySlot2.png");
	public static Image inventorySlot3Texture = loadImage("InventorySlot3.png");
	public static Image inventorySlot4Texture = loadImage("InventorySlot4.png");
	public static Image inventorySlot5Texture = loadImage("InventorySlot5.png");
	public static Image inventoryBackTexture = loadImage("InventoryBack.png");
	public static Image forestBackTexture = loadImage("ForestBackground.png");
	public static Image corruptionBackTexture = loadImage("CorruptionBackground.png");
	public static Image crimsonBackTexture = loadImage("CrimsonBackground.png");
	public static Image trashTexture = loadImage("Trash.png");

	// Buffs

	public static Image ammoReservationBuffTexture = loadImage("AmmoReservationBuff.png");
	public static Image archeryBuffTexture = loadImage("ArcheryBuff.png");
	public static Image battleBuffTexture = loadImage("BattleBuff.png");
	public static Image builderBuffTexture = loadImage("BuilderBuff.png");
	public static Image calmBuffTexture = loadImage("CalmBuff.png");
	public static Image crateBuffTexture = loadImage("CrateBuff.png");
	public static Image dangersenseBuffTexture = loadImage("DangersenseBuff.png");
	public static Image enduranceBuffTexture = loadImage("EnduranceBuff.png");
	public static Image featherfallBuffTexture = loadImage("FeatherfallBuff.png");
	public static Image fishingBuffTexture = loadImage("FishingBuff.png");
	public static Image flipperBuffTexture = loadImage("FlipperBuff.png");
	public static Image gillsBuffTexture = loadImage("GillsBuff.png");
	public static Image gravityBuffTexture = loadImage("GravityBuff.png");
	public static Image heartreachBuffTexture = loadImage("HeartreachBuff.png");
	public static Image hunterBuffTexture = loadImage("HunterBuff.png");
	public static Image infernoBuffTexture = loadImage("InfernoBuff.png");
	public static Image invisibilityBuffTexture = loadImage("InvisibilityBuff.png");
	public static Image ironskinBuffTexture = loadImage("IronskinBuff.png");
	public static Image lifeforceBuffTexture = loadImage("LifeforceBuff.png");
	public static Image lovestruckBuffTexture = loadImage("LovestruckBuff.png");
	public static Image magicPowerBuffTexture = loadImage("MagicPowerBuff.png");
	public static Image manaRegenerationBuffTexture = loadImage("ManaRegenerationBuff.png");
	public static Image miningBuffTexture = loadImage("MiningBuff.png");
	public static Image nightOwlBuffTexture = loadImage("NightOwlBuff.png");
	public static Image obsidianSkinBuffTexture = loadImage("ObsidianSkinBuff.png");
	public static Image rageBuffTexture = loadImage("RageBuff.png");
	public static Image regenerationBuffTexture = loadImage("RegenerationBuff.png");
	public static Image shineBuffTexture = loadImage("ShineBuff.png");
	public static Image sonarBuffTexture = loadImage("SonarBuff.png");
	public static Image spelunkerBuffTexture = loadImage("SpelunkerBuff.png");
	public static Image summoningBuffTexture = loadImage("SummoningBuff.png");
	public static Image swiftnessBuffTexture = loadImage("SwiftnessBuff.png");
	public static Image thornsBuffTexture = loadImage("ThornsBuff.png");
	public static Image titanBuffTexture = loadImage("TitanBuff.png");
	public static Image warmthBuffTexture = loadImage("WarmthBuff.png");
	public static Image waterWalkingBuffTexture = loadImage("WaterWalkingBuff.png");
	public static Image wrathBuffTexture = loadImage("WrathBuff.png");
	public static Image wellFedBuffTexture = loadImage("WellFedBuff.png");
	public static Image cozyFireBuffTexture = loadImage("CozyFireBuff.png");
	public static Image dryadsBlessingFireBuffTexture = loadImage("DryadsBlessingBuff.png");
	public static Image happyBuffTexture = loadImage("HappyBuff.png");
	public static Image heartLampBuffTexture = loadImage("HeartLampBuff.png");
	public static Image honeyBuffTexture = loadImage("HoneyBuff.png");
	public static Image peaceCandleBuffTexture = loadImage("PeaceCandleBuff.png");
	public static Image starInABottleBuffTexture = loadImage("StarInABottleBuff.png");

	public static Image lightTexture = loadImage("Light.png");
	public static Image shadowTexture = loadImage("Shadow.png");

	public static Image loadImage(String path) {
		try {
			Image image = new Image("assets/images/" + path);
			image.setFilter(Image.FILTER_NEAREST);

			return image;
		} catch(SlickException exception) {
			LastTry.handleException(exception);
			LastTry.log("Failed to load '" + path + "' (Not found)");
			System.exit(-1);
		}

		return null;
	}

	public static FontWrapper loadFont(String path, int style, int size, java.awt.Color color) {
		return new FontWrapper("assets/fonts/" + path, style, size, color);
	}

	public static void preload() {

	}
}