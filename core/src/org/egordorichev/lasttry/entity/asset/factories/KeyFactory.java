package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;

import java.util.Objects;

/**
 * Handles key bindings in json
 */
public class KeyFactory extends AssetFactory<Integer> {
	@Override
	public Integer parse(JsonValue asset) {
		String id = asset.asString().toUpperCase();

		// Here is a lil bug, It doesn't recognize SPACE key
		int key = (Objects.equals(id, "SPACE") ? Input.Keys.SPACE : Input.Keys.valueOf(id));

		Assets.keys.add(asset.name(), key);

		return key;
	}
}