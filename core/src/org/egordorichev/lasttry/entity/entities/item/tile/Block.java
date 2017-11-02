package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.entities.item.Item;

public class Block extends Item {
	public static short SIZE = 16;
	/**
	 * Block is collidable
	 */
	protected boolean solid = true;
	/**
	 * Block textures
	 */
	protected TextureRegion tiles;

	public Block(String id) {
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

		this.solid = asset.getBoolean("solid", true);
	}
}