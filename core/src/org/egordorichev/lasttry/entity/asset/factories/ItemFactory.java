package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.util.log.Log;

import java.lang.reflect.Constructor;

/**
 * Parses items
 */
public class ItemFactory extends AssetFactory<Item> {
	/**
	 * Parses an asset
	 *
	 * @param asset Asset to parse
	 */
	@Override
	public Item parse(JsonValue asset) {
		String type = "org.egordorichev.lasttry.entity.entities.item." + asset.getString("type", "Item");

		try {
			Class<?> clazz = Class.forName(type);
			Constructor<?> constructor = clazz.getConstructor(String.class);

			Item item = (Item) constructor.newInstance(asset.name);
			item.loadFields(asset);

			Assets.items.add(asset.name(), item);

			return item;
		} catch (Exception exception) {
			exception.printStackTrace();
			Log.error("Failed to create item " + asset.name);
		}

		return null;
	}
}