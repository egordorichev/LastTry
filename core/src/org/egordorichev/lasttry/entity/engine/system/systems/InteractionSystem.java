package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.math.Vector3;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessages;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.item.tile.interactable.Interactable;
import org.egordorichev.lasttry.entity.entities.item.tile.interactable.InteractionComponent;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.util.collision.Collider;
import org.egordorichev.lasttry.util.input.Input;
import org.egordorichev.lasttry.util.input.SimpleInputProcessor;

import java.util.ArrayList;
import java.util.Objects;

public class InteractionSystem implements System, SimpleInputProcessor {
	/**
	 * List of interactable things
	 */
	private ArrayList<Entity> entities = new ArrayList<>();

	public InteractionSystem() {
		// Don't forget to add self to input processors
		Input.multiplexer.addProcessor(this);
	}

	/**
	 * Handles adding entities
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, SystemMessages.ENTITIES_UPDATED)) {
			this.entities = Engine.getEntitiesFor(InteractionComponent.class);
		}
	}

	/**
	 * Handles mouse press
	 *
	 * @param screenX Screen X
	 * @param screenY Screen Y
	 * @param pointer ???
	 * @param button Mouse button
	 * @return If the event was polled
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		CameraComponent camera = CameraSystem.instance.get("main").getComponent(CameraComponent.class);
		Vector3 mouse = camera.camera.unproject(new Vector3(screenX, screenY, 0));

		for (Entity entity : this.entities) {
			if (entity instanceof Interactable) {
				Interactable interactable = (Interactable) entity;

				PositionComponent position = entity.getComponent(PositionComponent.class);
				SizeComponent size = entity.getComponent(SizeComponent.class);
				boolean collides = false;

				if (position != null && size != null) {
					collides = Collider.testAABB(position.x, position.y, size.width, size.width, mouse.x, mouse.y, 1, 1);
				} else {
					collides = interactable.checkOverlap((int) mouse.x, (int) mouse.y);
				}

				// Test collision with the mouse
				if (collides) {
					interactable.onClick((int) mouse.x, (int) mouse.y);
					return true;
				}
			}
		}

		short x = (short) Math.floor(mouse.x / Block.SIZE);
		short y = (short) Math.floor(mouse.y / Block.SIZE);

		String id = World.instance.getBlock(x, y);

		if (id != null) {
			Block block = (Block) Assets.items.get(id);

			if (block instanceof Interactable) {
				Interactable interactable = (Interactable) block;
				interactable.onClick((int) mouse.x, (int) mouse.y);
			}
		}

		return false;
	}
}