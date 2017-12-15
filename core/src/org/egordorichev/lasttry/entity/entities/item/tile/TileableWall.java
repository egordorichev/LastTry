package org.egordorichev.lasttry.entity.entities.item.tile;

import org.egordorichev.lasttry.entity.asset.Assets;

public class TileableWall extends Wall {
	public TileableWall(String id) {
		super(id);
	}

	/**
	 * Returns, if tile should tile to given tile
	 *
	 * @param neighbor Wall
	 * @param self     Self
	 * @return Should tile
	 */
	protected boolean shouldTile(String neighbor, String self) {
		Wall wall = (Wall) Assets.items.get(neighbor);
		return wall instanceof TileableWall;
	}
}