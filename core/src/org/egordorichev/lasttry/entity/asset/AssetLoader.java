package org.egordorichev.lasttry.entity.asset;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Handles assets
 */
public class AssetLoader {
	@SuppressWarnings("rawtypes")
	public static void load(String file, AssetFactory factory) {
		JsonReader reader = new JsonReader();

		try {
			JsonValue root = reader.parse(Gdx.files.internal("data/" + file + ".json"));

			for (JsonValue asset : root) {
				factory.parse(asset);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load " + file + " assets");
		}
	}
}