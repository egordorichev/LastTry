package org.egordorichev.lasttry.state;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import org.egordorichev.lasttry.injection.Context;

public interface GameState {

    /** Called when this screen becomes the current screen for a {@link Game}. */
    default void show (){}

    /** Called when this screen is no longer the current screen for a {@link Game}. */
    default void hide (){}

    void load(Context rootContext);

    /* update with the game context */
    void update();

    /** Called when the screen should render itself.
     * @param context the context. */
    void render (float deltaT);

    /** @see ApplicationListener#resize(int, int) */
    default void resize (int width, int height){}

    /** @see ApplicationListener#pause() */
    default void pause (){}

    /** @see ApplicationListener#resume() */
    default void resume (){}

}
