package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

public class Assets {
	public static BitmapFont f18;
	public static BitmapFont f22;
	public static BitmapFont f24;
	public static TextureAtlas textures;

	private static boolean loaded = false;

	static {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {

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

						textures = new TextureAtlas(Gdx.files.internal("textures.atlas"));
						loaded = true;
					}
				});
			}
		}).start();
	}

	public static TextureRegion getTextureRegion(String name) {
		return textures.findRegion(name);
	}

	public static void load() {

	}

	public static boolean isLoaded() {
		return loaded;
	}

	public static void dispose() {
		textures.dispose();
	}
}