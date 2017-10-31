package org.egordorichev.lasttry.entity.asset;

import com.badlogic.gdx.utils.JsonValue;

/**
 * Handles assets creation
 */
public interface AssetFactory {
	/**
	 * Parses an asset
	 *
	 * @param asset Asset to parse
	 */
	void parse(JsonValue asset);
}