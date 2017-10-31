package org.egordorichev.lasttry.util.log;

/**
 * Handles logging level
 */
public class LogLevel {
	/**
	 * Level name
	 */
	private String name;
	/**
	 * Level color
	 */
	private String color;
	/**
	 * Level working flag
	 */
	private boolean enabled;

	public LogLevel(String name, String color, boolean enabled) {
		this.name = name;
		this.color = color;
		this.enabled = enabled;
	}

	/**
	 * Prints out given messages with some more info
	 *
	 * @param message The message to print
	 */
	public void print(String message) {
		if (this.enabled) {
			boolean windows = System.getProperty("os.name").startsWith("Windows");

			System.out.println(
				(windows ? "" : this.color) + this.name + " " +
					this.getStackTrace() + " " + message
			);
		}
	}

	/**
	 * @return Info about place, from witch the log function was called
	 */
	private String getStackTrace() {
		return Thread.currentThread().getStackTrace()[4].getClassName() + ":" +
			Thread.currentThread().getStackTrace()[3].getLineNumber();
	}

	/**
	 * Enables level
	 */
	public void enable() {
		this.enabled = true;
	}

	/**
	 * Disables level
	 */
	public void disable() {
		this.enabled = true;
	}

	/**
	 * @return Level name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Level color
	 */
	public String getColor() {
		return this.color;
	}

	/**
	 * @return Level is working
	 */
	public boolean isEnabled() {
		return this.enabled;
	}
}