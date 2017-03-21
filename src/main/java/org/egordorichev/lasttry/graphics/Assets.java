package org.egordorichev.lasttry.graphics;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class Assets {
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

	public static UnicodeFont font = loadFont("font.ttf", 22);
	public static UnicodeFont smallFont = loadFont("font.ttf", 18);

	public static Image loadImage(String path) {
		try {
			Image image = new Image("assets/images/" + path);
			image.setFilter(Image.FILTER_NEAREST);

			return image;
		} catch(SlickException exception) {
			LastTry.log("Failed to load '" + path + "' (Not found)");
			System.exit(-1);
		}

		return null;
	}

	public static UnicodeFont loadFont(String path, int size) {
		try {
			java.awt.Font font = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT,
					ResourceLoader.getResourceAsStream("assets/fonts/" + path));

			font = font.deriveFont(java.awt.Font.PLAIN, size);

			org.newdawn.slick.UnicodeFont uniFont = new org.newdawn.slick.UnicodeFont(font);
			uniFont.addAsciiGlyphs();
			ColorEffect a = new org.newdawn.slick.font.effects.ColorEffect();

			uniFont.getEffects().add(a);
			uniFont.loadGlyphs();

			return uniFont;
		} catch(Exception exception) {
			LastTry.handleException(exception);
		}

		return null;
	}

	public static void preload() {

	}
}