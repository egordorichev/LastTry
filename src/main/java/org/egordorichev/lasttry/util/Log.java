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
		if (!this.enabled) {
			return;
		}

		System.out.print(ANSI_GREEN + "DEBUG ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message + ANSI_RESET);
	}

	public void warn(String message) {
		if (!this.enabled) {
			return;
		}

		System.out.print(ANSI_RED + "WARN ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message + ANSI_RESET);
	}

	public void error(String message) {
		if (!this.enabled) {
			return;
		}

		System.out.print(ANSI_RED + "ERROR ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message + ANSI_RESET);
	}

	public void info(String message) {
		if (!this.enabled) {
			return;
		}

		System.out.print(ANSI_BLUE + "INFO ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message + ANSI_RESET);
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
