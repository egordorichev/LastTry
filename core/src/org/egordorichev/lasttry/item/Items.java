package org.egordorichev.lasttry.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.util.Log;

import java.lang.reflect.InvocationTargetException;
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
			JsonValue root = jsonReader.parse(new FileHandle(System.getProperty("user.home") + "/.LastTry/data/items.json"));

			for (JsonValue item : root) {
				try {
					ITEM_CACHE.put(item.name(), Item.load(item));
				} catch (Exception exception) {
					Log.error(exception.getMessage());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to load items");
		}
	}
}