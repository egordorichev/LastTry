package org.egordorichev.lasttry.entity.engine.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.PositionComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.component.TargetComponent;
import org.egordorichev.lasttry.entity.component.physics.VelocityComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.engine.system.System;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Handles cameras
 */
public class CameraSystem implements System {
	/**
	 * All in-game cameras
	 */
	private ArrayList<Entity> cameras;

	/**
	 * Updates the cameras position
	 *
	 * @param delta Time, since the last frame
	 */
	@Override
	public void update(float delta) {
		for (Entity entity : this.cameras) {
			Camera camera = (Camera) entity;
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
			}
		}
	}

	/**
	 * Reacts on window resizing
	 *
	 * @param message Message from the engine
	 */
	@Override
	public void handleMessage(String message) {
		if (Objects.equals(message, "resize")) {
			int width = Gdx.graphics.getWidth();
			int height = Gdx.graphics.getHeight();

			if (width < 800 || height < 600) {
				width = 800;
				height = 600;

				Gdx.graphics.setWindowedMode(width, height);
			}

			for (Entity entity : this.cameras) {
				Camera camera = (Camera) entity;
				CameraComponent component = camera.getComponent(CameraComponent.class);

				// TODO: resize the view
				component.camera.update();
			}
		} else if (Objects.equals(message, "entity_added")) {
			this.cameras = Engine.getEntitiesFor(TargetComponent.class, CameraComponent.class);
		}
	}
}