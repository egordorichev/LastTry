package org.egordorichev.lasttry.entity.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import org.egordorichev.lasttry.entity.asset.factories.CreatureFactory;
import org.egordorichev.lasttry.entity.asset.factories.CreaturesDataFactory;
import org.egordorichev.lasttry.entity.asset.factories.ItemFactory;
import org.egordorichev.lasttry.entity.asset.storages.CreaturesData;
import org.egordorichev.lasttry.entity.asset.storages.Items;
import org.egordorichev.lasttry.util.log.Log;

public class Assets {
	/**
	 * Item holder
	 */
	public static Items items = new Items();
	/**
	 * Creatures data holder
	 */
	public static CreaturesData creaturesData = new CreaturesData();
	/**
	 * Creates creatures
	 */
	public static CreatureFactory creatures = new CreatureFactory();
	/**
	 * Small font
	 */
	public static BitmapFont f14;
	/**
	 * Big font
	 */
	public static BitmapFont f24;
	/**
	 * The manager for the textures
	 */
	private static AssetManager manager = new AssetManager();

	/**
	 * Loads all assets
	 */
	public static void load() {
		Log.debug("Loading assets");

		loadFonts();
		loadTextures();
		loadJSON();
	}

	/**
	 * Returns a texture from the texture atlas
	 *
	 * @param name Region name
	 * @return The region or null, if it is not found
	 */
	public static TextureRegion getTexture(String name) {
		return manager.get("atlas/textures.atlas", TextureAtlas.class).findRegion(name);
	}

	/**
	 * Loads fonts
	 */
	private static void loadFonts() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("stark.otf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameters = new FreeTypeFontGenerator.FreeTypeFontParameter();

		parameters.size = 14;
		f14 = generator.generateFont(parameters);

		parameters.size = 24;
		f24 = generator.generateFont(parameters);
	}

	/**
	 * Loads textures
	 */
	private static void loadTextures() {
		manager.load("atlas/textures.atlas", TextureAtlas.class);
		manager.finishLoading();
	}

	/**
	 * Loads all json based data
	 */
	private static void loadJSON() {
		AssetLoader.load("items", new ItemFactory());
		AssetLoader.load("creatures", new CreaturesDataFactory());
	}
}