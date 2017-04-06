package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;
import org.egordorichev.lasttry.LastTry;

public class Assets {
	public static AssetManager assetManager = new AssetManager();
	public static BitmapFont f18;
	public static BitmapFont f22;
	public static BitmapFont f24;

	public static boolean load() {
		return assetManager.update();
	}

	public static void dispose() {
		assetManager.dispose();
	}

	public static void loadTexture(String fileName) {
		assetManager.load(fileName, Texture.class);
	}

	public static Texture getTexture(String fileName) {
		return assetManager.get(fileName, Texture.class);
	}

	static {
		FreetypeFontLoader.FreeTypeFontLoaderParameter fontConfig = new FreetypeFontLoader.FreeTypeFontLoaderParameter();
		fontConfig.fontFileName = "font.ttf";
		fontConfig.fontParameters.size = 22;

		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameters.size = 18;
		f18 = generator.generateFont(parameters);
		parameters.size = 22;
		f22 = generator.generateFont(parameters);
		parameters.size = 24;
		f24 = generator.generateFont(parameters);

		generator.dispose();

		loadTexture(Textures.greenSlime);
		loadTexture(Textures.blueSlime);
		loadTexture(Textures.eyeOfCthulhu);

		loadTexture(Textures.dirt);
		loadTexture(Textures.dirtIcon);
		loadTexture(Textures.grass);
		loadTexture(Textures.grassIcon);
		loadTexture(Textures.ebonstoneIcon);
		loadTexture(Textures.ebonstone);
		loadTexture(Textures.corruptThornyBushes);
		loadTexture(Textures.purpleIceIcon);
		loadTexture(Textures.purpleIce);
		loadTexture(Textures.vileMushroom);
		loadTexture(Textures.crimstoneIcon);
		loadTexture(Textures.crimstone);
		loadTexture(Textures.redIceIcon);
		loadTexture(Textures.redIce);
		loadTexture(Textures.viciousMushroom);
		loadTexture(Textures.sandBlockIcon);
		loadTexture(Textures.sandBlock);
		loadTexture(Textures.ebonsandIcon);
		loadTexture(Textures.ebonsand);
		loadTexture(Textures.crimsandIcon);
		loadTexture(Textures.crimsand);
		loadTexture(Textures.stoneIcon);
		loadTexture(Textures.stone);
		loadTexture(Textures.ironPickaxe);
		loadTexture(Textures.dirtWall);
		loadTexture(Textures.dirtWallIcon);
		loadTexture(Textures.copperCoin);
		loadTexture(Textures.silverCoin);
		loadTexture(Textures.goldCoin);
		loadTexture(Textures.platinumCoin);
		loadTexture(Textures.woodenSword);
		loadTexture(Textures.gel);
		loadTexture(Textures.heart);
		loadTexture(Textures.mana);
		loadTexture(Textures.hp);
		loadTexture(Textures.radial);
		loadTexture(Textures.inventorySlot1);
		loadTexture(Textures.inventorySlot2);
		loadTexture(Textures.inventorySlot3);
		loadTexture(Textures.inventorySlot4);
		loadTexture(Textures.inventorySlot5);
		loadTexture(Textures.inventoryBack);
		loadTexture(Textures.forestBack);
		loadTexture(Textures.corruptionBack);
		loadTexture(Textures.crimsonBack);
		loadTexture(Textures.trash);
		loadTexture(Textures.ice);
		loadTexture(Textures.iceIcon);
		loadTexture(Textures.dayBloom);
		loadTexture(Textures.dayBloomIcon);
		loadTexture(Textures.blinkRoot);
		loadTexture(Textures.blinkRootIcon);
		loadTexture(Textures.moonGlow);
		loadTexture(Textures.moonGlowIcon);
		loadTexture(Textures.deathWeed);
		loadTexture(Textures.deathWeedIcon);
		loadTexture(Textures.waterLeaf);
		loadTexture(Textures.waterLeafIcon);
		loadTexture(Textures.fireBlossom);
		loadTexture(Textures.fireBlossomIcon);
		loadTexture(Textures.silverThorn);
		loadTexture(Textures.silverThornIcon);
		loadTexture(Textures.dayBloomSeeds);
		loadTexture(Textures.blinkRootSeeds);
		loadTexture(Textures.moonGlowSeeds);
		loadTexture(Textures.deathWeedSeeds);
		loadTexture(Textures.waterLeafSeeds);
		loadTexture(Textures.fireBlossomSeeds);
		loadTexture(Textures.silverThornSeeds);
		loadTexture(Textures.mud);
		loadTexture(Textures.mudIcon);
		loadTexture(Textures.jungleGrass);
		loadTexture(Textures.jungleGrassIcon);
		loadTexture(Textures.jungleGrassSeeds);
		loadTexture(Textures.ashBlockIcon);
		loadTexture(Textures.ashBlock);
		loadTexture(Textures.snowBlockIcon);
		loadTexture(Textures.snowBlock);
		loadTexture(Textures.copperShortSword);
		loadTexture(Textures.copperPickaxe);
		loadTexture(Textures.copperAxe);
		loadTexture(Textures.nullItem);
		loadTexture(Textures.wood);
		loadTexture(Textures.woodIcon);
		loadTexture(Textures.livingWood);
		loadTexture(Textures.tileCracks);

		loadTexture(Textures.ammoReservationBuff);
		loadTexture(Textures.archeryBuff);
		loadTexture(Textures.battleBuff);
		loadTexture(Textures.builderBuff);
		loadTexture(Textures.calmBuff);
		loadTexture(Textures.crateBuff);
		loadTexture(Textures.dangersenseBuff);
		loadTexture(Textures.enduranceBuff);
		loadTexture(Textures.featherfallBuff);
		loadTexture(Textures.fishingBuff);
		loadTexture(Textures.flipperBuff);
		loadTexture(Textures.gillsBuff);
		loadTexture(Textures.gravityBuff);
		loadTexture(Textures.heartreachBuff);
		loadTexture(Textures.hunterBuff);
		loadTexture(Textures.infernoBuff);
		loadTexture(Textures.invisibilityBuff);
		loadTexture(Textures.ironskinBuff);
		loadTexture(Textures.lifeforceBuff);
		loadTexture(Textures.lovestruckBuff);
		loadTexture(Textures.magicPowerBuff);
		loadTexture(Textures.manaRegenerationBuff);
		loadTexture(Textures.miningBuff);
		loadTexture(Textures.nightOwlBuff);
		loadTexture(Textures.obsidianSkinBuff);
		loadTexture(Textures.rageBuff);
		loadTexture(Textures.regenerationBuff);
		loadTexture(Textures.shineBuff);
		loadTexture(Textures.sonarBuff);
		loadTexture(Textures.spelunkerBuff);
		loadTexture(Textures.summoningBuff);
		loadTexture(Textures.swiftnessBuff);
		loadTexture(Textures.thornsBuff);
		loadTexture(Textures.titanBuff);
		loadTexture(Textures.warmthBuff);
		loadTexture(Textures.waterWalkingBuff);
		loadTexture(Textures.wrathBuff);
		loadTexture(Textures.wellFedBuff);
		loadTexture(Textures.cozyFireBuff);
		loadTexture(Textures.dryadsBlessingBuff);
		loadTexture(Textures.happyBuff);
		loadTexture(Textures.heartLampBuff);
		loadTexture(Textures.honeyBuff);
		loadTexture(Textures.peaceCandleBuff);
		loadTexture(Textures.starInABottleBuff);
		loadTexture(Textures.light);
		loadTexture(Textures.shadow);
		loadTexture(Textures.sky);

		loadTexture(Textures.corruptionWorld);
		loadTexture(Textures.crimsonWorld);
		loadTexture(Textures.corruptionHardmodeWorld);
		loadTexture(Textures.crimsonHardmodeWorld);
	}
}