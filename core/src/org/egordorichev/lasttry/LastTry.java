package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.boot.ArgumentParser;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.engine.Engine;
import org.egordorichev.lasttry.game.state.InGameState;
import org.egordorichev.lasttry.game.state.State;
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

		Globals.init();
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

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		Engine.sendMessage("resize");
	}

	@Override
	public void render() {
		float delta = Gdx.graphics.getDeltaTime();
		this.state.update(delta);

		Gdx.gl.glClearColor( 0.1f, 0.1f, 0.1f, 1 );
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Globals.batch.begin();
		this.state.render();
		Globals.batch.end();

		Gdx.graphics.setTitle(this.title + " " + Gdx.graphics.getFramesPerSecond() + " FPS");
	}
}