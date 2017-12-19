package org.egordorichev.lasttry.entity.entities.item.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Tile;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.TileHelper;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;

public class Pickaxe extends Item {
	public Pickaxe(String id) {
		super(id);

		this.getComponent(ItemUseComponent.class).autoUse = true;
	}

	@Override
	protected boolean onUse(Entity entity) {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= Tile.SIZE;
		mouse.y /= Tile.SIZE;

		short x = (short) mouse.x;
		short y = (short) mouse.y;

		String id = World.instance.getBlock(x, y);
		if (id != null) {
			Block block = (Block) Assets.items.get(id);

			int data = World.instance.getData(x, y);
			int health = block.getHealth(data);

			health -= 1;

			if (health == 0) {
				World.instance.setBlock(null, x, y);
				World.instance.setData(TileHelper.main.create(), x, y);
			} else {
				World.instance.setData(block.getHelper().setBlockHealth(data, health), x, y);
			}
		}

		return false;
	}

	@Override
	protected boolean canBeUsed() {
		// TODO: check radius

		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= Tile.SIZE;
		mouse.y /= Tile.SIZE;

		return World.instance.getBlock((short) mouse.x, (short) mouse.y) != null;
	}
}