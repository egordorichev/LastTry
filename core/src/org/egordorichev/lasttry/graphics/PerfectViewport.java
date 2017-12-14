package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Pixel perfect viewport
 */
public class PerfectViewport extends Viewport {
	@Override
	public void update(int screenWidth, int screenHeight, boolean centerCamera) {
		int size = Math.min(screenWidth / Display.WIDTH, screenHeight / Display.HEIGHT);

		int width = size * Display.WIDTH;
		int height = size * Display.HEIGHT;

		setScreenBounds((screenWidth - width) / 2,  (screenHeight - height) / 2, width, height);
		setWorldSize(Display.WIDTH, Display.HEIGHT);
		apply(centerCamera);
	}
}