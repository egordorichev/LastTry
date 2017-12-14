package org.egordorichev.lasttry.entity.asset.factories;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.AssetFactory;
import org.egordorichev.lasttry.entity.asset.Assets;

public class CreaturesDataFactory extends AssetFactory<JsonValue> {
	/**
	 * Creates creature data from json
	 *
	 * @param asset Data to parse
	 */
	@Override
	public JsonValue parse(JsonValue asset) {
		Assets.creaturesData.add(asset.name(), asset);
		return asset;
	}
}