package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;
import java.io.File;
import java.util.Locale;
import java.util.concurrent.*;

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

	public static void futureOneTimeRunInThread(Callable callable, long delay, TimeUnit timeUnit) {

		ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);

		ScheduledFuture<?> scheduledFuture = scheduledExecutorService
				.schedule(
				new Runnable() {
					@Override
					public void run() {
						callable.call();
					}
				}, delay, timeUnit);
		
		// TODO: execute scheduledFuture 
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
	
	public static boolean isWindows() {
		return  System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
	}
}
