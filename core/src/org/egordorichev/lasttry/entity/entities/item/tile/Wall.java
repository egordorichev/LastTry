package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.TileComponent;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.binary.BinaryPacker;

import java.util.Objects;

/**
 * Block, but in the BG
 */
public class Wall extends Item {
	public Wall(String id) {
		super(id);

		this.addComponent(TileComponent.class);

		this.getComponent(ItemUseComponent.class).autoUse = true;
	}

	/**
	 * Renders the wall
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @param light How light the wall is
	 */
	public void render(short x, short y, byte light) {
		float value = -((float) light);
		Graphics.batch.setColor(value, value, value, 1);

		int neighbors = this.getNeighbors(x, y);

		Graphics.batch.draw(this.getComponent(TileComponent.class).tiles[0][neighbors], x * Block.SIZE, y * Block.SIZE);
		Graphics.batch.setColor(1, 1, 1, 1);
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
	 * Returns amount of tileable neighbors
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @return Packed binary
	 */
	public int getNeighbors(short x, short y) {
		String id = this.getComponent(IdComponent.class).id;

		boolean top = this.shouldTile(World.instance.getWall(x, (short) (y + 1)), id);
		boolean right = this.shouldTile(World.instance.getWall((short) (x + 1), y), id);
		boolean bottom = this.shouldTile(World.instance.getWall(x, (short) (y - 1)), id);
		boolean left = this.shouldTile(World.instance.getWall((short) (x - 1), y), id);

		return BinaryPacker.pack(top, right, bottom, left);
	}

	/**
	 * Returns, if wall should tile to given wall
	 *
	 * @param neighbor Wall
	 * @param self Self
	 * @return Should tile
	 */
	protected boolean shouldTile(String neighbor, String self) {
		return Objects.equals(neighbor, self);
	}
}