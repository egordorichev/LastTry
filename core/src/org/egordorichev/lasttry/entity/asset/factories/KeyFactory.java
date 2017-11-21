package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;

/**
 * Handles key bindings in json
 */
public class KeyFactory extends AssetFactory<Integer> {
	@Override
	public Integer parse(JsonValue asset) {
		int key = asset.asInt();
		Assets.keys.add(asset.name(), key);

		return key;
	}

	private <T extends Enum<?>> T searchEnum(Class<T> enumeration, String search) {
		for (T each : enumeration.getEnumConstants()) {
			if (each.name().compareToIgnoreCase(search) == 0) {
				return each;
			}
		}

		return null;
	}
}