package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Screen;

/**
 * Basic interface, does not bring anything new
 */
public interface State extends Screen {	@Override
	default void pause() {

	}

	@Override
	default void resume() {

	}

	@Override
	default void hide() {

	}

	@Override
	default void dispose() {

	}

	@Override
	default void show() {

	}

	@Override
	default void resize(int width, int height) {

	}
}