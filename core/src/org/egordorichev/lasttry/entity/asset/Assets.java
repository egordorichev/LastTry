package org.egordorichev.lasttry.entity.asset;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import org.egordorichev.lasttry.entity.asset.factories.ItemFactory;
import org.egordorichev.lasttry.entity.asset.storages.Items;
import org.egordorichev.lasttry.util.log.Log;

public class Assets {
	/**
	 * Item holder
	 */
	public static Items items = new Items();
	/**
	 * The manager for the textures
	 */
	private static AssetManager manager = new AssetManager();

	/**
	 * Loads all assets
	 */
	public static void load() {
		Log.debug("Loading assets");

		loadTextures();
		loadJSON();
	}

	/**
	 * Loads textures
	 */
	private static void loadTextures() {
		manager.load("textures.atlas", TextureAtlas.class);
	}

	/**
	 * Loads all json based data
	 */
	private static void loadJSON() {
		AssetLoader.load("items", new ItemFactory());
	}
}