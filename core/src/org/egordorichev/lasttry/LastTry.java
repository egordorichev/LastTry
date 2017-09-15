package org.egordorichev.lasttry;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.egordorichev.lasttry.core.Crash;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.injection.ContextImpl;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.language.Language;
import org.egordorichev.lasttry.state.GameState;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.ui.UiManagerImpl;
import org.egordorichev.lasttry.util.Camera;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

/**
 * Main game class
 */
public class LastTry implements ApplicationListener, Engine {
	public static final Version version = new Version(0.0, 18, "alpha");
	public static final Random random = new Random();
	public static boolean release = true;
	public static boolean noLight = false;
	public static boolean storeRelative = true;
	public static float gammaStrength = 1.0f;
	public static float gammaMinimum = 0f;
	public static String defaultWorldName = "default";
	public static String defaultPlayerName = "default";

	private GameState state = null;


	private Context rootContext = new ContextImpl();


	public LastTry(GameState state){
		this.state = state;

	}

	/**
	 * Creates first-priority instances
	 */
	@Override
	public void create() {
		rootContext.bindInstance(Engine.class,this);

		//binds UIManager
		rootContext.bindInstance(UiManager.class,new UiManagerImpl());

		Thread.currentThread().setUncaughtExceptionHandler(Crash::report);

		Gdx.graphics.setCursor(Gdx.graphics.newCursor(new Pixmap(Gdx.files.internal("cursor.png")), 0, 0));

		Language.load(new Locale("en", "US"));

		Gdx.input.setInputProcessor(InputManager.multiplexer);
		Gdx.graphics.setTitle(LastTry.getRandomWindowTitle());

		Graphics.batch = new SpriteBatch();
	}

	/**
	 * Handles window resize
	 *
	 * @param width
	 *            new window width
	 * @param height
	 *            new window height
	 */
	@Override
	public void resize(int width, int height) {
		Camera.resize(width, height);
	}

	/**
	 * Renders and updates the game
	 */
	@Override
	public void render() {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 1);

		Graphics.batch.enableBlending();
		Graphics.batch.begin();

		this.state.update();

		//don't render if in headless mode
		if(Gdx.app.getType() != Application.ApplicationType.HeadlessDesktop) {
			this.state.render(Gdx.graphics.getDeltaTime());
		}

		Graphics.batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	/**
	 * Handles game exit
	 */
	@Override
	public void dispose() {
		Assets.dispose();
	}

	/**
	 * Returns random title for game the window
	 *
	 * @return random title for game the window
	 */
	private static String getRandomWindowTitle() {
		String date = new SimpleDateFormat("MMdd").format(new Date());

		if (date.equals("0629") || date.equals("0610")) {
			return "Happy Birthday!" + " " + version.toString();
		}

		String[] split = Language.text.get("windowTitles").split("//");
		return split[random.nextInt(split.length)] + " " + version.toString();
	}

//	/**
//	 * Returns mouse X coordinate, under the world
//	 *
//	 * @return mouse X coordinate, under the world
//	 */
//	public static int getMouseXInWorld() {
//		return (int) (Globals.getPlayer().physics.getCenterX() - Gdx.graphics.getWidth() / 2
//				+ InputManager.getMousePosition().x);
//	}

//	/**
//	 * Returns mouse Y coordinate, under the world
//	 *
//	 * @return mouse Y coordinate, under the world
//	 */
//	public static int getMouseYInWorld() {
//		return (int) (Globals.getPlayer().physics.getCenterY() + Gdx.graphics.getHeight() / 2
//				- InputManager.getMousePosition().y);
//	}

	/**
	 * Handles exception, if it is critical, exits the game
	 *
	 * @param exception
	 *            exception to handle
	 */
	public static void handleException(Exception exception) {
		Crash.report(Thread.currentThread(), exception);
	}

	/**
	 * Aborts the process
	 */
	public static void abort() {
		System.exit(1);
	}

	@Override
	public void setGameState(GameState state) {
		if(state  != null)
			this.state.hide();
		this.state = state;
		if(this.state != null){
			this.state.load(rootContext);
			this.state.show();
		}
	}

	@Override
	public void setContext(Context context) {
		this.rootContext = context;
	}

}
