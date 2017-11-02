package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.entities.item.Item;

/**
 * Block, but in the BG
 */
public class Wall extends Item {
	/**
	 * Wall textures
	 */
	protected TextureRegion tiles;

	public Wall(String id) {
		super(id);
	}

	/**
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	@Override
	public void loadFields(JsonValue asset) {
		super.loadFields(asset);

		// TODO
	}
}