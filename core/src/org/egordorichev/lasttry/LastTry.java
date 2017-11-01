package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import org.egordorichev.lasttry.core.boot.ArgumentParser;
import org.egordorichev.lasttry.game.state.LoadState;
import org.egordorichev.lasttry.game.state.State;
import org.egordorichev.lasttry.util.log.Log;

/**
 * The main class of the game
 * Be careful with changing it ;)
 */
public class LastTry extends Game {
	/**
	 * Current game state
	 */
	private State state;

	/**
	 * All init happens here
	 */
	@Override
	public void create() {
		Globals.init();

		try {
			ArgumentParser.parse();
		} catch (RuntimeException exception) {
			Log.error(exception.getMessage());
			Log.error("Failed to parse arguments aborting");
			return;
		}

		this.setState(new LoadState());
	}

	@Override
	public void render() {
		this.state.update(Gdx.graphics.getDeltaTime());

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		Globals.batch.begin();
		this.state.render();
		Globals.batch.end();
	}

	/**
	 * Wrapper around setting state
	 * @param state New state
	 */
	public void setState(State state) {
		this.setScreen(state);
		this.state = state;

		// Here goes all state changing stuff
		// Like logging and saving

		Log.info("LT state is now " + state.getName() + " state");
	}
}