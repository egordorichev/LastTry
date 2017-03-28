package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {
	public Textures textures;
	public Fonts fonts;
	private static boolean loaded = false;

	public static void load() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				FreetypeFontLoader.FreeTypeFontLoaderParameter fontConfig = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
				fontConfig.fontFileName = "font.ttf";
				fontConfig.fontParameters.size = 22;

				FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
				FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();

				parameters.size = 22;
				Fonts.f22 = generator.generateFont(parameters);
				parameters.size = 24;
				Fonts.f24 = generator.generateFont(parameters);
				parameters.size = 18;
				Fonts.f18 = generator.generateFont(parameters);

				generator.dispose();

				Textures.greenSlime = load("GreenSlime.png");
				Textures.blueSlime = load("BlueSlime.png");
				Textures.eyeOfCthulhu = load("EyeOfCthulhu.png");
				Textures.dirt = load("DirtTile.png");
				Textures.dirtIcon = load("DirtIcon.png");
				Textures.grass = load("GrassTile.png");
				Textures.grassIcon = load("GrassIcon.png");
				Textures.ebonstoneIcon = load("EbonstoneIcon.png");
				Textures.ebonstone = load("EbonstoneTile.png");
				Textures.corruptThornyBushes = load("CorruptThornyBushesTile.png");
				Textures.purpleIceIcon = load("PurpleIceIcon.png");
				Textures.purpleIce = load("PurpleIceTile.png");
				Textures.vileMushroom = load("VileMushroom.png");
				Textures.crimstoneIcon = load("CrimstoneIcon.png");
				Textures.crimstone = load("CrimstoneTile.png");
				Textures.redIceIcon = load("RedIceIcon.png");
				Textures.redIce = load("RedIceTile.png");
				Textures.viciousMushroom = load("ViciousMushroom.png");
				Textures.sandBlockIcon = load("SandIcon.png");
				Textures.sandBlock = load("SandTile.png");
				Textures.ebonsandIcon = load("EbonsandIcon.png");
				Textures.ebonsand = load("EbonsandTile.png");
				Textures.crimsandIcon = load("CrimsandIcon.png");
				Textures.crimsand = load("CrimsandTile.png");
				Textures.stoneIcon = load("StoneIcon.png");
				Textures.stone = load("StoneTile.png");
				Textures.ironPickaxe = load("IronPickaxe.png");
				Textures.dirtWall = load("DirtWall.png");
				Textures.dirtWallIcon = load("DirtWallIcon.png");
				Textures.copperCoin = load("CopperCoin.png");
				Textures.silverCoin = load("SilverCoin.png");
				Textures.goldCoin = load("GoldCoin.png");
				Textures.platinumCoin = load("PlatinumCoin.png");
				Textures.woodenSword = load("WoodenSword.png");
				Textures.gel = load("Gel.png");
				Textures.heart = load("Heart.png");
				Textures.mana = load("Mana.png");
				Textures.hp = load("HP.png");
				Textures.radial = load("Radial.png");
				Textures.inventorySlot1 = load("InventorySlot1.png");
				Textures.inventorySlot2 = load("InventorySlot2.png");
				Textures.inventorySlot3 = load("InventorySlot3.png");
				Textures.inventorySlot4 = load("InventorySlot4.png");
				Textures.inventorySlot5 = load("InventorySlot5.png");
				Textures.inventoryBack = load("InventoryBack.png");
				Textures.forestBack = load("ForestBack.png");
				Textures.corruptionBack = load("CorruptionBack.png");
				Textures.crimsonBack = load("CrimsonBack.png");
				Textures.trash = load("Trash.png");
				Textures.ammoReservationBuff = load("AmmoReservationBuff.png");
				Textures.archeryBuff = load("ArcheryBuff.png");
				Textures.battleBuff = load("BattleBuff.png");
				Textures.builderBuff = load("BuilderBuff.png");
				Textures.calmBuff = load("CalmBuff.png");
				Textures.crateBuff = load("CrateBuff.png");
				Textures.dangersenseBuff = load("DangersenseBuff.png");
				Textures.enduranceBuff = load("EnduranceBuff.png");
				Textures.featherfallBuff = load("FeatherfallBuff.png");
				Textures.fishingBuff = load("FishingBuff.png");
				Textures.flipperBuff = load("FlipperBuff.png");
				Textures.gillsBuff = load("GillsBuff.png");
				Textures.gravityBuff = load("GravityBuff.png");
				Textures.heartreachBuff = load("HeartreachBuff.png");
				Textures.hunterBuff = load("HunterBuff.png");
				Textures.infernoBuff = load("InfernoBuff.png");
				Textures.invisibilityBuff = load("InvisibilityBuff.png");
				Textures.ironskinBuff = load("IronskinBuff.png");
				Textures.lifeforceBuff = load("LifeforceBuff.png");
				Textures.lovestruckBuff = load("LovestruckBuff.png");
				Textures.magicPowerBuff = load("MagicPowerBuff.png");
				Textures.manaRegenerationBuff = load("ManaRegenerationBuff.png");
				Textures.miningBuff = load("MiningBuff.png");
				Textures.nightOwlBuff = load("NightOwlBuff.png");
				Textures.obsidianSkinBuff = load("ObsidianSkinBuff.png");
				Textures.rageBuff = load("RageBuff.png");
				Textures.regenerationBuff = load("RegenerationBuff.png");
				Textures.shineBuff = load("ShineBuff.png");
				Textures.sonarBuff = load("SonarBuff.png");
				Textures.spelunkerBuff = load("SpelunkerBuff.png");
				Textures.summoningBuff = load("SummoningBuff.png");
				Textures.swiftnessBuff = load("SwiftnessBuff.png");
				Textures.thornsBuff = load("ThornsBuff.png");
				Textures.titanBuff = load("TitanBuff.png");
				Textures.warmthBuff = load("WarmthBuff.png");
				Textures.waterWalkingBuff = load("WaterWalkingBuff.png");
				Textures.wrathBuff = load("WrathBuff.png");
				Textures.wellFedBuff = load("WellFedBuff.png");
				Textures.cozyFireBuff = load("CozyFireBuff.png");
				Textures.dryadsBlessingBuff = load("DryadsBlessingBuff.png");
				Textures.happyBuff = load("HappyBuff.png");
				Textures.heartLampBuff = load("HeartLampBuff.png");
				Textures.honeyBuff = load("HoneyBuff.png");
				Textures.peaceCandleBuff = load("PeaceCandleBuff.png");
				Textures.starInABottleBuff = load("StarInABottleBuff.png");
				Textures.light = load("Light.png");
				Textures.shadow = load("Shadow.png");
				Textures.sky = load("Sky.png");

				loaded = true;
			}
		}).run(); // TODO: replace with start
	}

	private static Texture load(String path) {
		return new Texture(Gdx.files.internal(path));
	}

	public static boolean isLoaded() {
		return loaded;
	}
}