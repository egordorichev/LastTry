package org.egordorichev.lasttry.entity.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.TargetComponent;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.system.System;

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

			if (component.target != null) {
				// TODO: move the camera
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