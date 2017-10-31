package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.game.state.InGameState;
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
		this.setState(new InGameState());
	}

	@Override
	public void render() {
		this.state.update(Gdx.graphics.getDeltaTime());
		this.state.render();
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