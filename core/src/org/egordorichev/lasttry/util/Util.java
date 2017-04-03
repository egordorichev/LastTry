package org.egordorichev.lasttry.util;

import org.egordorichev.lasttry.LastTry;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Util {

    public static int random(int min, int max) {
        return LastTry.random.nextInt((max - min) + 1) + min;
    }

    /**
     * Runs callable in a thread every time seconds
     *
     * @param callable thread to run
     * @param time     delay, before next run
     */
    public static void runInThread(Callable callable, int time) {
        ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutor.scheduleAtFixedRate(callable::call, 0, time, TimeUnit.SECONDS);
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