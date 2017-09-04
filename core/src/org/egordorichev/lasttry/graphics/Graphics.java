package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Helper class for different effects
 */
public class Graphics {
	/**
	 * Tile cracks textures
	 */
	public static TextureRegion[] tileCracks;
	/**
	 * Skybox texture
	 */
	public static Texture skyTexture;
	/**
	 * Health bar texture
	 */
	public static Texture healthBarTexture;
	/**
	 * Health bar frame texture
	 */
	public static Texture healthBarFrameTexture;
	/**
	 * Water texture
	 */
	public static TextureRegion[][] waterTexture;
	/**
	 * Sprite batch
	 */
	public static SpriteBatch batch;

	/**
	 * Loads textures
	 */
	public static void load() {
		tileCracks = new TextureRegion[24];

		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				tileCracks[x * 4 + y] = new TextureRegion(new Texture(Gdx.files.internal("tile_cracks.png")), x * 16, y * 16, 16, 16);
			}
		}

		skyTexture = new Texture(Gdx.files.internal("sky.png"));
		healthBarTexture = new Texture(Gdx.files.internal("health_bar.png"));
		healthBarFrameTexture = new Texture(Gdx.files.internal("health_bar_frame.png"));
		waterTexture = Assets.getTexture("lt_water").split(16, 16);
	}
}