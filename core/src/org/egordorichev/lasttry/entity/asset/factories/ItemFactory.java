package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.item.Item;

/**
 * Parses items
 */
public class ItemFactory implements AssetFactory {
	/**
	 * Parses an asset
	 *
	 * @param asset Asset to parse
	 */
	@Override
	public void parse(JsonValue asset) {
		Item item = new Item(asset.name());

		Assets.items.add(asset.name(), item);
	}
}