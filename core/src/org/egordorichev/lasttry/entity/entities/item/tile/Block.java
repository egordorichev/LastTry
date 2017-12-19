package org.egordorichev.lasttry.entity.entities.item.tile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SolidComponent;
import org.egordorichev.lasttry.entity.component.physics.CollisionComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.TileHelper;
import org.egordorichev.lasttry.entity.entities.world.World;

/**
 * The main part of the world
 */
public class Block extends Tile {
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

		this.addComponent(SolidComponent.class);
		this.addComponent(CollisionComponent.class);
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
	 * Sets field according to the json asset
	 *
	 * @param asset Json asset
	 */
	@Override
	public void loadFields(JsonValue asset) {
		super.loadFields(asset);

		this.getComponent(TileComponent.class).tiles =
			Assets.getTexture("blocks/" + this.getComponent(IdComponent.class).id.replace(':', '_')
				+ "_tiles").split(Block.SIZE, Block.SIZE);

		this.getComponent(SolidComponent.class).solid =
			asset.getBoolean("solid", true);
	}

	/**
	 * @return Block emits light
	 */
	public boolean isEmitter() {
		return false;
	}

	/**
	 * Places self on given cords
	 *
	 * @param x In World X
	 * @param y In World Y
	 */
	@Override
	protected void place(short x, short y) {
		World.instance.setBlock(this.getComponent(IdComponent.class).id, x, y);
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
		return World.instance.getBlock(x, y);
	}
}