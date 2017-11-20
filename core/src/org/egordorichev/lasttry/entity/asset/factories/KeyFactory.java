package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Handles key bindings in json
 */
public class KeyFactory extends AssetFactory<Integer> {
	@Override
	public Integer parse(JsonValue asset) {
		String id = asset.asString();
		int key = Input.Keys.valueOf(id.toUpperCase());

		Assets.keys.add(asset.name(), key);

		return key;
	}
}