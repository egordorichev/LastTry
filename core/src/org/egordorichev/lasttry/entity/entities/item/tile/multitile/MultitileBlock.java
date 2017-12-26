package org.egordorichev.lasttry.entity.entities.item.tile.multitile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.MultitileHelper;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.TileHelper;
import org.egordorichev.lasttry.entity.entities.world.World;

/**
 * Represents block larger, then one tile
 */
public class MultitileBlock extends Block {
	public MultitileBlock(String id) {
		super(id);

		this.addComponent(MultitileSizeComponent.class);
	}

	/**
	 * Loads data from json
	 * @param asset Json asset
	 */
	@Override
	public void loadFields(JsonValue asset) {
		super.loadFields(asset);

		MultitileSizeComponent size = this.getComponent(MultitileSizeComponent.class);

		size.width = asset.getInt("tile_width", 2);
		size.height = asset.getInt("tile_height", 2);
	}

	/**
	 * Places the tile
	 *
	 * @param x In World X
	 * @param y In World Y
	 */
	@Override
	protected void place(short x, short y) {
		MultitileSizeComponent size = this.getComponent(MultitileSizeComponent.class);

		for (short xx = 0; xx < size.width; xx++) {
			for (short yy = 0; yy < size.height; yy++) {
				World.instance.setBlock(this.getComponent(IdComponent.class).id, (short) (xx + x), (short) (yy + y));
				World.instance.setData(TileHelper.multitile.create(xx, (short) (size.height - yy - 1)), (short) (xx + x), (short) (yy + y));
			}
		}
	}

	/**
	 * @return Can the item be used at mouse position
	 */
	@Override
	protected boolean canBeUsed() {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= SIZE;
		mouse.y /= SIZE;

		short x = (short) mouse.x;
		short y = (short) mouse.y;

		MultitileSizeComponent size = this.getComponent(MultitileSizeComponent.class);

		for (short xx = x; xx < x + size.width; xx++) {
			for (short yy = y; yy < y + size.height; yy++) {
				if (World.instance.getBlock(xx, yy) != null) {
					return false;
				}
			}
		}

		return true;
	}

	@Override
	public TileHelper getHelper() {
		return TileHelper.multitile;
	}

	/**
	 * Returns texture part
	 *
	 * @param x Tile X
	 * @param y Tile Y
	 * @return Texture part
	 */
	@Override
	public int getNeighbors(short x, short y) {
		int data = World.instance.getData(x, y);
		return (short) ((MultitileHelper) this.getHelper()).getX(data);
	}

	/**
	 * Tile variant
	 *
	 * @param data Info about the wall
	 * @return Tile variant
	 */
	@Override
	public int getVariant(int data) {
		return (short) ((MultitileHelper) this.getHelper()).getY(data);
	}
}