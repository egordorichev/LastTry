package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

public class Assets {
	public static Image dirtTileTexture = loadImage("DirtTile.png");
	public static Image dirtTileIcon = loadImage("DirtTileIcon.png");
	public static Image grassTileTexture = loadImage("GrassTile.png");
	public static Image grassTileIcon = loadImage("GrassTileIcon.png");
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
	public static Image heartTexture = loadImage("Heart.png");
	public static Image manaTexture = loadImage("Mana.png");
	public static Image hpTexture = loadImage("HP.png");
	public static Image radialTexture = loadImage("Radial.png");
	public static Image inventorySlot1Texture = loadImage("InventorySlot1.png");
	public static Image inventorySlot2Texture = loadImage("InventorySlot2.png");
	public static Image inventorySlot3Texture = loadImage("InventorySlot3.png");
	public static Image inventorySlot4Texture = loadImage("InventorySlot4.png");
	public static Image inventoryBackTexture = loadImage("InventoryBack.png");
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