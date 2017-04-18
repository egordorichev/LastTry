package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.crash.Crash;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.player.*;
import org.egordorichev.lasttry.graphics.*;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.state.SplashState;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.util.Debug;
import org.egordorichev.lasttry.world.*;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.language.Language;
import java.util.Random;
import java.util.Locale;

/** Main game class */
public class LastTry extends Game {
	/** LastTry version */
	public static final Version version = new Version(0, 6);
	
	/** Random instance */
	public static final Random random = new Random();
	
	/** Camera */
	public static OrthographicCamera camera;
	
	/** UI Camera */
	public static OrthographicCamera uiCamera;
	
	/** Public sprite batch */
	public static SpriteBatch batch;
	
	/** Game viewport */
	public static Viewport viewport;
	
	/** Last Try instance */
	public static LastTry instance;

	/** Ui manager */
	public static UiManager ui;

	/** World instance */
	public static World world;

	/** Player instance */
	public static Player player;

	/** Environment instance*/
	public static Environment environment;

	/** PhysicBody manager instance*/
	public static EntityManager entityManager;

	/** Mod loader */
	public static ModLoader modLoader;

	/** Debug helper */
	public static Debug debug;

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
		debug = new Debug();

		Gdx.input.setInputProcessor(InputManager.multiplexer);
                
        Locale en_US = new Locale("en", "US");
        Language.load(en_US);
                
		Gdx.graphics.setTitle(this.getRandomWindowTitle());

		int width = Gdx.graphics.getWidth();
		int height = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(width, height);
		camera.setToOrtho(false, width, height);
		uiCamera = new OrthographicCamera(width, height);
		uiCamera.setToOrtho(false, width, height);
		viewport = new FitViewport(width, height);

		batch = new SpriteBatch();

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
		viewport.update(width, height);
	}

	/** Renders and updates the game */
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		batch.enableBlending();
		batch.begin();
		super.render();
		batch.end();
	}

	/** Handles game exit */
	@Override
	public void dispose() {
		if(player != null){
			PlayerIO.save();
		}

		if (world != null) {
			WorldIO.save();
		}

		Assets.dispose();
	}

	/**
	 * Returns random title for game the window
	 * @return random title for game the window
	 */
	private String getRandomWindowTitle() {
        String[] split = Language.text.get("windowTitles").split("//");
        return split[random.nextInt(split.length)];
	}

	/**
	 * Returns mouse X coordinate, under the world
	 * @return mouse X coordinate, under the world
	 */
	public static int getMouseXInWorld() {
		return (int) (player.physics.getCenterX() - Gdx.graphics.getWidth() / 2 + InputManager.getMousePosition().x);
	}

	/**
	 * Returns mouse Y coordinate, under the world
	 * @return mouse Y coordinate, under the world
	 */
	public static int getMouseYInWorld() {
		return (int) (player.physics.getCenterY() - Gdx.graphics.getHeight() / 2 + InputManager.getMousePosition().y);
	}

	/**
	 * Handles exception, if it is critical, exits the game
	 * @param exception exception to handle
	 */
	public static void handleException(Exception exception) {
		// TODO Exception was not displayed, when I attempted to load a texture that was not in the assets.
		Crash.report(Thread.currentThread(), exception);
	}

	public static void abort() {
		System.exit(1);
	}
}
