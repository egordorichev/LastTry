package org.egordorichev.lasttry.item;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.core.Bootstrap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;

public class Items {
	private static final Logger logger = LoggerFactory.getLogger(Items.class);
	/**
	 * Items storage
	 */
	public static HashMap<String, Item> ITEM_CACHE = new HashMap<>();

	/**
	 * Loads items
	 */
	public static void load() {
		if (!Bootstrap.isLoaded()) {
			logger.error("Trying to load items before bootstrap");
			return;
		}

		try {
			JsonReader jsonReader = new JsonReader();
			JsonValue root = jsonReader.parse(Gdx.files.internal("data/items.json"));

			for (JsonValue item : root) {
				try {
					ITEM_CACHE.put(item.name(), Item.load(item));
				} catch (Exception exception) {
					logger.error(exception.getMessage());
				}
			}
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("Failed to load items");
		}
	}
}