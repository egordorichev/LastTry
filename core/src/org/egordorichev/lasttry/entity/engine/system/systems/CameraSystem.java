package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.TargetComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.engine.SystemMessages;
import org.egordorichev.lasttry.entity.engine.system.System;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.graphics.PerfectViewport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Handles cameras
 */
public class CameraSystem implements System {
	/**
	 * The main instance
	 */
	public static CameraSystem instance;
	/**
	 * All in-game cameras
	 */
	private HashMap<String, Entity> cameras;
	/**
	 * The viewport
	 */
	private PerfectViewport viewport;

	public CameraSystem() {
		instance = this;
		this.cameras = new HashMap<>();
		this.viewport = new PerfectViewport();
	}

	/**
	 * Updates the cameras position
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Map.Entry<String, Entity> pair : this.cameras.entrySet()) {
			Camera camera = (Camera) pair.getValue();
			TargetComponent component = camera.getComponent(TargetComponent.class);
			CameraComponent cam = camera.getComponent(CameraComponent.class);

			if (component.target != null) {
				Entity target = component.target;
				PositionComponent position = target.getComponent(PositionComponent.class);
				SizeComponent size = target.getComponent(SizeComponent.class);

				float x = position.x + size.width / 2;
				float y = position.y + size.height / 2;
				float dx = x - cam.camera.position.x;
				float dy = y - cam.camera.position.y;

				if (Math.abs(dx) + Math.abs(dy) > 20) {
					cam.camera.position.x += dx * 4f * delta;
					cam.camera.position.y += dy * 4f * delta;
				}

				SizeComponent worldSize = World.instance.getComponent(SizeComponent.class);

				float width = worldSize.width * Chunk.SIZE * Block.SIZE;
				float height = worldSize.height * Chunk.SIZE * Block.SIZE;
				float halfDisplayWidth = cam.camera.viewportWidth / 2;
				float halfDisplayHeight = cam.camera.viewportHeight / 2;

				// Auto floor it:
				cam.camera.position.x = (int) Math.max(Math.min(cam.camera.position.x, width - halfDisplayWidth - Block.SIZE), halfDisplayWidth + Block.SIZE);
				cam.camera.position.y = (int) Math.max(Math.min(cam.camera.position.y, height - halfDisplayHeight - Block.SIZE), halfDisplayHeight + Block.SIZE);
			}

			cam.camera.update();
		}
	}

	/**
	 * Reacts on window resizing
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, SystemMessages.WINDOW_RESIZED)) {
			int width = Gdx.graphics.getWidth();
			int height = Gdx.graphics.getHeight();

			if (width < 320 || height < 180) {
				width = 320;
				height = 180;

				Gdx.graphics.setWindowedMode(width, height);
			}

			for (Map.Entry<String, Entity> pair : this.cameras.entrySet()) {
				Camera camera = (Camera) pair.getValue();
				CameraComponent component = camera.getComponent(CameraComponent.class);
				component.camera.update();
			}

			this.viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		} else if (Objects.equals(message, SystemMessages.ENTITIES_UPDATED)) {
			ArrayList<Entity> entities = Engine.getEntitiesFor(TargetComponent.class, CameraComponent.class, IdComponent.class);
			this.cameras.clear();

			for (Entity entity : entities) {
				this.cameras.put(entity.getComponent(IdComponent.class).id, entity);
			}

			if (this.cameras.size() > 0) {
				CameraComponent camera = this.get("main").getComponent(CameraComponent.class);
				this.viewport.setCamera(camera.camera);
			}
		}
	}

	/**
	 * Returns camera with that id
	 *
	 * @param id Camera id
	 * @return Camera with that id
	 */
	public Camera get(String id) {
		return (Camera) this.cameras.get(id);
	}
}