package org.egordorichev.lasttry;

import com.badlogic.gdx.Game;
import org.egordorichev.lasttry.game.state.LoadState;
import org.egordorichev.lasttry.game.state.State;
import org.egordorichev.lasttry.util.log.Log;

/**
 * The main class of the game
 * Be careful with changing it ;)
 */
public class LastTry extends Game {
	/**
	 * All init happens here
	 */
	@Override
	public void create() {
		this.setState(new LoadState());
	}

	/**
	 * Wrapper around setting state
	 * @param state New state
	 */
	public void setState(State state) {
		this.setScreen(state);

		// Here goes all state changing stuff
		// Like logging and saving

		Log.info("LT state is now " + state.getName() + " state");
	}
}