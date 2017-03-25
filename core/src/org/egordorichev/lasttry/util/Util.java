package org.egordorichev.lasttry.util;

import com.badlogic.gdx.InputMultiplexer;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Util {
	/** Input handler */
	public static InputMultiplexer multiplexer = new InputMultiplexer();

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
}