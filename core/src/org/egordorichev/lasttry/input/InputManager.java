package org.egordorichev.lasttry.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.Globals;

/**
 * Simple input manager
 */
public class InputManager {
	/**
	 * InputManager handler
	 */
	public static InputMultiplexer multiplexer = new InputMultiplexer();

	/**
	 * Last pressed mouse button
	 */
	private static int currentButton = -1;

	static {
		multiplexer.addProcessor(new DefaultInputProcessor() {
			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				currentButton = button;
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				currentButton = -1;
				return false;
			}
		});
	}

	/**
	 * Returns if key is down
	 *
	 * @param key
	 *            Key to check
	 * @return Key is down
	 */
	public static boolean isKeyDown(int key) {
		if (Globals.chat.isOpen()) {
			return false;
		}
		/*
		if (key != Keys.DEBUG_MODE && Globals.chat.isOpen()) {
			return false;
		}
		*/
		return Gdx.input.isKeyPressed(key);
	}

	/**
	 * Returns if key is up
	 *
	 * @param key
	 *            Key to check
	 * @return Key is up
	 */
	public static boolean isKeyUp(int key) {
		if (Globals.chat.isOpen()) {
			return false;
		}
		return !Gdx.input.isKeyPressed(key);
	}

	/**
	 * Returns if key was pressed in this frame
	 *
	 * @param key
	 *            Key to check
	 * @return Key was pressed in this frame
	 */
	public static boolean isKeyJustDown(int key) {
		if (Globals.chat.isOpen()) {
			return false;
		}
		/*
		if (key != Keys.DEBUG_MODE && Globals.chat.isOpen()) {
			return false;
		}
		 */
		return Gdx.input.isKeyJustPressed(key);
	}

	/**
	 * Checks mouse state
	 *
	 * @param button
	 *            Button to check
	 * @return Button is down
	 */
	public static boolean isMouseButtonPressed(int button) {
		if (Globals.chat.isOpen()) {
			return false;
		}
		return Gdx.input.isButtonPressed(button);
	}

	/**
	 * Returns mouse position
	 *
	 * @return Mouse position
	 */
	public static Vector2 getMousePosition() {
		return new Vector2(Gdx.input.getX(), Gdx.input.getY());
	}

	/**
	 * Returns if any mouse button was just pressed
	 *
	 * @return Any mouse button was just pressed
	 */
	public static boolean mouseButtonJustPressed() {
		if (currentButton != -1) {
			currentButton = -1;
			return true;
		}

		return false;
	}
}
