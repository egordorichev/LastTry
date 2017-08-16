package org.egordorichev.lasttry.effect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.util.Log;

import java.util.HashMap;

/**
 * Effect handler
 */
public class Effects {
	/**
	 * Items storage
	 */
	public static HashMap<String, Effect> EFFECT_CACHE = new HashMap<>();

	/**
	 * Loads items
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to load effects before bootstrap");
			return;
		}

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(Gdx.files.internal("data/effects.json"));

			for (JsonValue effect : root) {
				try {
					EFFECT_CACHE.put(effect.name(), Effect.load(effect));
				} catch (Exception exception) {
					Log.error(exception.getMessage());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load effects");
		}
	}
}