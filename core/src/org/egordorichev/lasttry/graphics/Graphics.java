package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphics {
	public static TextureRegion[] tileCracks;
	public static Texture skyTexture = new Texture(Gdx.files.internal(Textures.sky));
    public static Texture healthBarTexture;
    public static Texture healthBarFrameTexture;
    public static SpriteBatch batch;

    public static void load() {
		tileCracks = new TextureRegion[24];

		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				tileCracks[x * 4 + y] = new TextureRegion(Assets.getTexture(Textures.tileCracks), x * 18, y * 18, 18, 18);
			}
		}

	    healthBarTexture = Assets.getTexture(Textures.healthBar);
	    healthBarFrameTexture = Assets.getTexture(Textures.healthBarFrame);
	}
}