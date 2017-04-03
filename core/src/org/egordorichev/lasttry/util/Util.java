package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Util {
	/** Input handler */
	public static InputMultiplexer multiplexer = new InputMultiplexer();

	/** Last pressed mouse button */
	private static int currentButton = -1;

	static {
		multiplexer.addProcessor(new InputProcessor() {
			@Override
			public boolean keyDown(int keycode) {
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

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

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(int amount) {
				return false;
			}
		});
	}

	public static float map(float value, float inMin, float inMax, float outMin, float outMax) {
		return (value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
	}

	public static int random(int min, int max) {
		return LastTry.random.nextInt((max - min) + 1) + min;
	}

	public static boolean mouseButtonJustPressed() {
		if (currentButton != -1) {
			currentButton = -1;
			return true;
		}

		return false;
	}

	/**
	 * Runs callable in a thread every time seconds
	 * @param callable thread to run
	 * @param time delay, before next run
	 */
	public static void runInThread(Callable callable, int time) {
		ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

		scheduledExecutor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				callable.call();
			}
		}, 0, time, TimeUnit.SECONDS);
	}

	public static boolean fileExists(String path) {
		File file = new File(path);

		if (!file.exists()) {
			return false;
		}

		if (file.length() == 0) {
			return false;
		}

		return true;
	}
}