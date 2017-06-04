package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;

import java.awt.Rectangle;
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
     * Runs the callable in a thread on repeat. The exectuon is delayed each
     * time by the given time in seconds.
     * 
     * @param callable
     *            thread to run
     * @param time
     *            delay in seconds, before next run
     */
    public static void runDelayedThreadSeconds(Callable callable, int time) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                callable.call();
            }
        }, 0, time, TimeUnit.SECONDS);
    }

    /**
     * Runs the callable in a thread on repeat. The exectuon is delayed each
     * time by the given time in milliseconds.
     * 
     * @param callable
     *            thread to run
     * @param time
     *            delay in milliseconds, before next run
     */
    public static void runDelayedThreadMillis(Callable callable, int time) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutor.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                callable.call();
            }
        }, 0, time, TimeUnit.MILLISECONDS);
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

        ScheduledFuture<?> scheduledFuture = scheduledExecutorService.schedule(new Runnable() {
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

    public static void delete(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                for (File child : file.listFiles()) {
                    delete(child);
                }
            }
            file.delete();
        }
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase(Locale.ROOT).contains("win");
    }

    public static void expand(Rectangle rectangle, int expansion) {
        rectangle.x -= expansion;
        rectangle.y -= expansion;
        rectangle.width += expansion * 2;
        rectangle.height += expansion * 2;
    }

    public static boolean inRange(float value, float min, float max) {
        return value >= min && value <= max;
    }

}
