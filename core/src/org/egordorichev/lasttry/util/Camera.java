package org.egordorichev.lasttry.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.egordorichev.lasttry.entity.Entity;

public class Camera extends Entity {
	public static Viewport viewport;
	public static OrthographicCamera game;
	public static OrthographicCamera ui;

	public static void create(int width, int height) {
		game = new OrthographicCamera(width, height);
		game.setToOrtho(false, width, height);
		ui = new OrthographicCamera(width, height);
		ui.setToOrtho(false, width, height);
		viewport = new FitViewport(width, height);
	}

	public static void resize(int width, int height) {
		viewport.update(width, height);
	}
}