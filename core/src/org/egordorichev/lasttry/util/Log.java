package org.egordorichev.lasttry.util;

public class Log {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private static boolean enabled;

    public static void debug(String message) {
        printMessage(ANSI_GREEN + "DEBUG", message);
    }

    public static void warn(String message) {
        printMessage(ANSI_PURPLE + "WARN", message);
    }

    public static void error(String message) {
        printMessage(ANSI_RED + "ERROR", message);
    }

    public static void info(String message) {
        printMessage(ANSI_BLUE + "INFO", message);
    }

    private static void printMessage(String start, String message) {
        System.out.print(start + " ");
        printPath(Thread.currentThread().getStackTrace());
        System.out.println(" " + message + ANSI_RESET);
        System.out.flush();
    }

    private static void printPath(StackTraceElement[] stackTraceElements) {
        System.out.print(stackTraceElements[3].getClassName());
    }

    public static void enable() {
        enabled = true;
    }

    public static void disable() {
        enabled = false;
    }

    public static boolean isEnabled() {
        return enabled;
    }
}