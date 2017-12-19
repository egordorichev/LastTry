package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.TileHelper;
import org.egordorichev.lasttry.entity.entities.world.World;

/**
 * Block, but in the BG
 */
public class Wall extends Tile {
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

		this.getComponent(TileComponent.class).tiles =
			Assets.getTexture("walls/" + this.getComponent(IdComponent.class).id.replace(':', '_')
				+ "_tiles").split(Block.SIZE, Block.SIZE);
	}

	/**
	 * Places self on given cords
	 *
	 * @param x In World X
	 * @param y In World Y
	 */
	@Override
	protected void place(short x, short y) {
		World.instance.setWall(this.getComponent(IdComponent.class).id, x, y);
		World.instance.setData(TileHelper.create(), x, y);
	}

	/**
	 * Returns a neighbor at that pos
	 *
	 * @param x Neighbor X
	 * @param y Neighbor Y
	 * @return It's ID
	 */
	protected String getNeighbor(short x, short y) {
		return World.instance.getWall(x, y);
	}
}