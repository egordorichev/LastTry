package org.egordorichev.lasttry.util;

import jdk.nashorn.internal.codegen.CompilerConstants;
import org.egordorichev.lasttry.LastTry;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.input.InputManager;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Util {
	public static float map(float value, float inMin, float inMax, float outMin, float outMax) {
		return (value - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
	}

	public static int random(int min, int max) {
		return LastTry.random.nextInt((max - min) + 1) + min;
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

	public static void oneTimeRunInThread(Callable callable) {

		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.submit(new Runnable() {
			@Override
			public void run() {
				callable.call();
			};
		});
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
