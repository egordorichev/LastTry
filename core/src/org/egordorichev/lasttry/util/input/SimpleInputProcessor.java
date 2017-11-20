package org.egordorichev.lasttry.util.input;

import com.badlogic.gdx.InputProcessor;

/**
 * Hides some stuff, so we don't need to implement every method
 * In child classes
 */
public interface SimpleInputProcessor extends InputProcessor {
	@Override
	default boolean keyDown(int keycode) {
		return false;
	}

	@Override
	default boolean keyUp(int keycode) {
		return false;
	}

	@Override
	default boolean keyTyped(char character) {
		return false;
	}

	@Override
	default boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	default boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	default boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	default boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	default boolean scrolled(int amount) {
		return false;
	}
}