package org.egordorichev.lasttry.entity.entities.item.tool;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.Item;
import org.egordorichev.lasttry.entity.entities.item.ItemUseComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.world.World;

public class Pickaxe extends Item {
	public Pickaxe(String id) {
		super(id);

		this.getComponent(ItemUseComponent.class).autoUse = true;
	}

	@Override
	protected boolean onUse(Entity entity) {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= Block.SIZE;
		mouse.y /= Block.SIZE;

		World.instance.setBlock(null, (short) mouse.x, (short) mouse.y);

		return false;
	}

	@Override
	protected boolean canBeUsed() {
		// TODO: check radius

		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

		mouse.x /= Block.SIZE;
		mouse.y /= Block.SIZE;

		return World.instance.getBlock((short) mouse.x, (short) mouse.y) != null;
	}
}