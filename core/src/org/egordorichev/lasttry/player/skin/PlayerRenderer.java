package org.egordorichev.lasttry.player.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.nio.ByteBuffer;

public class PlayerRenderer {
	public static final int TEXTURE_WIDTH = 40;
	public static final int TEXTURE_HEIGHT = 1120;

	public static Pixmap playerHead = new Pixmap(Gdx.files.internal("player_head.png"));
	public static Pixmap playerBody = new Pixmap(Gdx.files.internal("player_body.png"));
	public static Pixmap playerEyes = new Pixmap(Gdx.files.internal("player_eyes.png"));
	public static Pixmap playerHands = new Pixmap(Gdx.files.internal("player_hands.png"));
	public static Pixmap playerFeet = new Pixmap(Gdx.files.internal("player_feet.png"));

	/**
	 * Generates texture
	 *
	 * @param info Player render info
	 * @return New texture
	 */
	public static TextureRegion generateTextureRegion(PlayerRenderInfo info) {
		Pixmap pixmap = new Pixmap(TEXTURE_WIDTH, TEXTURE_HEIGHT, Pixmap.Format.RGBA8888);

		overlay(playerBody, info.skinColor);
		overlay(playerFeet, info.skinColor);
		overlay(playerHands, info.skinColor);
		overlay(playerHead, info.skinColor);
		overlay(playerEyes, info.eyesColor);

		pixmap.drawPixmap(playerBody, 0, 0);
		pixmap.drawPixmap(playerHands, 0, 0);
		pixmap.drawPixmap(playerFeet, 0, 0);
		pixmap.drawPixmap(playerHead, 0, 0);
		pixmap.drawPixmap(playerEyes, 0, 0);

		// Pixmap hair = new Pixmap(Gdx.files.internal("PlayerHair" + info.hairStyle + ".png"));
		// overlay(hair, info.hairColor);
		// pixmap.drawPixmap(hair, 0, 0);
		// TODO: fix

		return new TextureRegion(new Texture(pixmap));
	}

	/**
	 * Tints pixmap
	 *
	 * @param pixmap Pixmap, to tint
	 * @param color Color, to apply
	 */
	private static void overlay(Pixmap pixmap, Color color) {
		ByteBuffer bb = pixmap.getPixels();

		final float rTint = color.r;
		final float bTint = color.b;
		final float gTint = color.g;

		bb.rewind();
		final int limit = bb.limit();

		byte[] pixels = new byte[pixmap.getWidth() * pixmap.getHeight() * 4];

		// Overlay blend mode from:
		// http://docs.gimp.org/en/gimp-concepts-layer-modes.html

		for (int i = 0; i < limit; i += 4) {
			int r = bb.get() & 0xFF;
			int g = bb.get() & 0xFF;
			int b = bb.get() & 0xFF;
			byte a = bb.get();

			r = (int) (rTint * r);
			if (r > 255) r = 255;

			g = (int) (gTint * g);
			if (g > 255) g = 255;

			b = (int) (bTint * b);
			if (b > 255) b = 255;

			pixels[i + 0] = (byte) (r);
			pixels[i + 1] = (byte) (g);
			pixels[i + 2] = (byte) (b);
			pixels[i + 3] = a;
		}

		bb.clear();
		bb.put(pixels);
		bb.flip();
	}
}