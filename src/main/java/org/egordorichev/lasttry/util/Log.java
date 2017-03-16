package org.egordorichev.lasttry.util;

public class Log {
	private boolean enabled;

	public Log() {
		this.enabled = true;
	}

	public void debug(String message) {
		System.out.print("DEBUG ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message);
	}

	public void warn(String message) {
		System.out.print("WARN ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message);
	}

	public void error(String message) {
		System.out.print("ERROR ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message);
	}

	public void info(String message) {
		System.out.print("INFO ");
		this.printPath(Thread.currentThread().getStackTrace());
		System.out.println(": " + message);
	}

	private void printPath(StackTraceElement[] stackTraceElements) {
		System.out.print(cdstackTraceElements[1].getClassName());
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