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

	private boolean enabled;

	public Log() {
		this.enabled = true;
	}

	public void debug(String message) {
		this.printMessage(ANSI_GREEN + "DEBUG", message);
	}

	public void warn(String message) {
		this.printMessage(ANSI_PURPLE + "WARN", message);
	}

	public void error(String message) {
		this.printMessage(ANSI_RED + "ERROR", message);
	}

	public void info(String message) {
		this.printMessage(ANSI_BLUE + "INFO", message);
	}

	private void printMessage(String start, String message) {
		System.out.print(start + " ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(" " + message + ANSI_RESET);
	}

	private void printPath(StackTraceElement[] stackTraceElements) {
		System.out.print(stackTraceElements[3].getClassName());
	}

	public void enable() {
		this.enabled = true;
	}

	public void disable() {
		this.enabled = false;
	}

	public boolean isEnabled() {
		return this.enabled;
	}
}