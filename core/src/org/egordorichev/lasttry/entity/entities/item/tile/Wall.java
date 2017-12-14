package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.TileComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.TileHelper;
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
	 * Places a wall
	 *
	 * @param entity Item owner
	 * @return Should the item be removed from inventory
	 */
	@Override
	protected boolean onUse(Entity entity) {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= Block.SIZE;
		mouse.y /= Block.SIZE;

		World.instance.setWall(this.getComponent(IdComponent.class).id, (short) mouse.x, (short) mouse.y);
		World.instance.setData(TileHelper.create(), (short) mouse.x, (short) mouse.y);

		return true;
	}

	/**
	 * Renders the wall
	 *
	 * @param x Wall X
	 * @param y Wall Y
	 * @param data Block data
	 * @param light How light the wall is
	 */
	public void render(short x, short y, int data, float light) {
		Graphics.batch.setColor(light, light, light, 1.0f);

		int neighbors = this.getNeighbors(x, y);
		int variant = TileHelper.getVariant(data);

		Graphics.batch.draw(this.getComponent(TileComponent.class).tiles[variant][neighbors], x * Block.SIZE, y * Block.SIZE);
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
	 * Returns wall pattern
	 *
	 * @param data Info about the wall
	 * @return The pattern
	 */
	public int getVariant(int data) {
		return TileHelper.getVariant(data);
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