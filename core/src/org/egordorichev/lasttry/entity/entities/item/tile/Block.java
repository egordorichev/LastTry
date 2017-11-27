package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SolidComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.TileComponent;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.binary.BinaryPacker;

import java.util.Objects;

/**
 * The main part of the world
 */
public class Block extends Item {
	/**
	 * Block size
	 */
	public static short SIZE = 8;
	/**
	 * Max light level
	 */
	public static short MAX_LIGHT = 16;

	public Block(String id) {
		super(id);

		this.addComponent(CollisionComponent.class);
		this.addComponent(TileComponent.class, SolidComponent.class);

		this.getComponent(ItemUseComponent.class).autoUse = true;
	}

	/**
	 * Places a block
	 *
	 * @param entity Item owner
	 * @return Should the item be removed from inventory
	 */
	@Override
	protected boolean onUse(Entity entity) {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= SIZE;
		mouse.y /= SIZE;

		World.instance.setBlock(this.getComponent(IdComponent.class).id, (short) mouse.x, (short) mouse.y);

		return true;
	}

	/**
	 * @return Can be block used
	 */
	@Override
	protected boolean canBeUsed() {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= SIZE;
		mouse.y /= SIZE;

		return (World.instance.getBlock((short) mouse.x, (short) mouse.y) == null
			&& (World.instance.getBlock((short) (mouse.x - 1), (short) mouse.y) != null
			|| World.instance.getBlock((short) (mouse.x + 1), (short) mouse.y) != null
			|| World.instance.getBlock((short) mouse.x, (short) (mouse.y - 1)) != null
			|| World.instance.getBlock((short) mouse.x, (short) (mouse.y + 1)) != null));
	}

	/**
	 * Renders the block
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @param neighbors Byte packed with neighbors
	 * @param light How light this block is
	 */
	public void render(short x, short y, int neighbors, float light) {
		Graphics.batch.setColor(light, light, light, 1.0f);
		Graphics.batch.draw(this.getComponent(TileComponent.class).tiles[0][neighbors], x * SIZE, y * SIZE);
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
			Assets.getTexture("blocks/" + this.getComponent(IdComponent.class).id.replace(':', '_')
				+ "_tiles").split(SIZE, SIZE);

		this.getComponent(SolidComponent.class).solid =
			asset.getBoolean("solid", true);
	}

	/**
	 * Returns amount of tileable neighbors
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Packed binary
	 */
	public int getNeighbors(short x, short y) {
		String id = this.getComponent(IdComponent.class).id;

		boolean top = this.shouldTile(World.instance.getBlock(x, (short) (y + 1)), id);
		boolean right = this.shouldTile(World.instance.getBlock((short) (x + 1), y), id);
		boolean bottom = this.shouldTile(World.instance.getBlock(x, (short) (y - 1)), id);
		boolean left = this.shouldTile(World.instance.getBlock((short) (x - 1), y), id);

		return BinaryPacker.pack(top, right, bottom, left);
	}

	/**
	 * @return Block emits light
	 */
	public boolean isEmitter() {
		return false;
	}

	/**
	 * Returns, if blocks should tile
	 *
	 * @param neighbor Block type
	 * @param self Self type
	 * @return Should tile
	 */
	protected boolean shouldTile(String neighbor, String self) {
		return Objects.equals(neighbor, self);
	}
}