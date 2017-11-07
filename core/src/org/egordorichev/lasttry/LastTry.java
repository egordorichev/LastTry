package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.boot.ArgumentParser;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.entity.entities.camera.Camera;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.game.state.InGameState;
import org.egordorichev.lasttry.game.state.State;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.util.log.Log;

/**
 * The main class of the game
 * Be careful with changing it ;)
 */
public class LastTry extends Game {
	/**
	 * The default window title
	 */
	private String title;
	/**
	 * The current game state
	 */
	private State state;

	/**
	 * All init happens here
	 */
	@Override
	public void create() {
		try {
			ArgumentParser.parse();
		} catch (RuntimeException exception) {
			Log.error(exception.getMessage());
			Log.error("Failed to parse arguments aborting");

			Gdx.app.exit();
			return;
		}

		Gdx.graphics.setWindowedMode(800, 600);

		this.title = "LastTry " + Version.STRING;
		this.setState(new InGameState());

		Assets.load();
		Engine.init();
	}

	/**
	 * Sets LT state
	 *
	 * @param state New state
	 */
	public void setState(State state) {
		this.state = state;
		this.setScreen(state);

		Log.info("Set LT state to " + state.getClass());

		// Here you can save/load stuff
	}

	/**
	 * Handles window resizing
	 *
	 * @param width New window width
	 * @param height New window height
	 */
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Engine.sendMessage("resize");
	}

	/**
	 * Renders the game
	 */
	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		this.state.update(delta);

		CameraComponent camera = Camera.instance.getComponent(CameraComponent.class);
		camera.camera.update();

		Graphics.batch.setProjectionMatrix(camera.camera.combined);

		Gdx.gl.glClearColor( 0.3f, 0.3f, 0.4f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Graphics.batch.begin();
		this.state.render();
		Graphics.batch.end();

		Gdx.graphics.setTitle(this.title + " " + Gdx.graphics.getFramesPerSecond() + " FPS");
	}

	/**
	 * Saves the game
	 */
	@Override
	public void dispose() {
		super.dispose();
		Engine.sendMessage("save");
	}
}