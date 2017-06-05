package org.egordorichev.lasttry;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Log;

import java.util.HashMap;

public class Items {
	/**
	 * Items storage
	 */
	public static HashMap<String, Item> ITEM_CACHE = new HashMap<>();
	/**
	 * Loads items
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			Log.error("Trying to load items before bootstrap");
			return;
		}

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(Gdx.files.internal("data/creatures.json"));

			for (JsonValue item : root) {
				try {
					ITEM_CACHE.put(item.name(), Item.load(root));
				} catch (Exception exception) {
					Log.error("Failed to parse " + item.name());
					exception.printStackTrace();
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load items");
		}
	}
}