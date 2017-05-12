package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.Crash;
import org.egordorichev.lasttry.graphics.*;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.state.SplashState;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Debug;
import org.egordorichev.lasttry.language.Language;

import java.util.Random;
import java.util.Locale;

/** Main game class */
public class LastTry extends Game {
	/** LastTry version */
	public static final Version version = new Version(0.0, 11, "alpha");
	
	/** Random instance */
	public static final Random random = new Random();

	/** Last Try instance */
	public static LastTry instance;

	/** Ui manager */
	public static UiManager ui;

	/** Debug helper */
	public static Debug debug;

	/** Shows, if this is a release */
	public static boolean release = true;

	/** Creates first-priority instances */
	@Override
	public void create() {
		Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, Throwable throwable) {
				Crash.report(thread, throwable);
			}
		});

		instance = this;

		Camera.create(800, 600);
		Language.load(new Locale("en", "US"));

		Gdx.input.setInputProcessor(InputManager.multiplexer);
		Gdx.graphics.setTitle(this.getRandomWindowTitle());

		Graphics.batch = new SpriteBatch();

		debug = new Debug();
		ui = new UiManager();

		this.setScreen(new SplashState());
	}

	/**
	 * Handles window resize
	 * @param width  new window width
	 * @param height new window height
	 */
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Camera.resize(width, height);
	}

	/** Renders and updates the game */
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		Graphics.batch.enableBlending();
		Graphics.batch.begin();
		super.render();
		Graphics.batch.end();
	}

	/** Handles game exit */
	@Override
	public void dispose() {
		Globals.dispose();
		Assets.dispose();
	}

	/**
	 * Returns random title for game the window
	 * @return random title for game the window
	 */
	private String getRandomWindowTitle() {
        String[] split = Language.text.get("windowTitles").split("//");
        return split[random.nextInt(split.length)] + " " + version.toString();
	}

	/**
	 * Returns mouse X coordinate, under the world
	 * @return mouse X coordinate, under the world
	 */
	public static int getMouseXInWorld() {
		return (int) (Globals.player.physics.getCenterX() - Gdx.graphics.getWidth() / 2 + InputManager.getMousePosition().x);
	}

	/**
	 * Returns mouse Y coordinate, under the world
	 * @return mouse Y coordinate, under the world
	 */
	public static int getMouseYInWorld() {
		return (int) (Globals.player.physics.getCenterY() + Gdx.graphics.getHeight() / 2 - InputManager.getMousePosition().y);
	}

	/**
	 * Handles exception, if it is critical, exits the game
	 * @param exception exception to handle
	 */
	public static void handleException(Exception exception) {
		Crash.report(Thread.currentThread(), exception);
	}

	public static void abort() {
		System.exit(1);
	}
}
