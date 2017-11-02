package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.util.log.Log;

/**
 * The main part of the world
 */
public class Block extends Item {
	public static short SIZE = 16;
	/**
	 * Block is collidable
	 */
	protected boolean solid = true;
	/**
	 * Block textures
	 */
	protected TextureRegion[][] tiles;

	public Block(String id) {
		super(id);
	}

	/**
	 * Renders the block
	 *
	 * @param x Block X
	 * @param y Block Y
	 */
	public void render(short x, short y) {
		Globals.batch.draw(this.tiles[0][0], x * SIZE, y * SIZE);
	}

	/**
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	@Override
	public void loadFields(JsonValue asset) {
		super.loadFields(asset);

		this.tiles = Assets.getTexture("blocks/" + this.id.replace(':', '_')).split(SIZE, SIZE);
		this.solid = asset.getBoolean("solid", true);
	}
}