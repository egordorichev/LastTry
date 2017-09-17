package org.egordorichev.lasttry;

import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.game.state.GameState;

public interface Engine {
	void setGameState(GameState state);
	void setContext(Context context);
}