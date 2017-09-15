package org.egordorichev.lasttry;

import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.state.GameState;

public interface Engine {
    void setGameState(GameState state);
    void setContext(Context context);
}
