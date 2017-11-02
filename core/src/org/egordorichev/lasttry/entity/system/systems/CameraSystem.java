package org.egordorichev.lasttry.entity.system.systems;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.system.System;

import java.util.Objects;

public class CameraSystem implements System {
	/**
	 * Sets the camera
	 */
	public void update(float delta) {
		Globals.camera.set();
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

			Globals.camera.resize(width, height);
		}
	}
}