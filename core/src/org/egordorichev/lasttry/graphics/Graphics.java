package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphics {
	public static TextureRegion[] tileCracks;
	public static Texture skyTexture;
	public static Texture healthBarTexture;
	public static Texture healthBarFrameTexture;
	public static SpriteBatch batch;

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
	}
}