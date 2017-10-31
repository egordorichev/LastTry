package org.egordorichev.lasttry.util.log;

/**
 * Main log class
 */
public class Log {
	/**
	 * Set of logging levels
	 */
	private static LogLevel[] levels;

	static {
		levels = new LogLevel[4];
		levels[0] = new LogLevel("INFO", AsciiColors.GREEN, true);
		levels[1] = new LogLevel("DEBUG", AsciiColors.BLUE, true);
		levels[2] = new LogLevel("ERROR", AsciiColors.RED, true);
		levels[3] = new LogLevel("WARNING", AsciiColors.PURPLE, true);
	}

	/**
	 * Prints out a message with info level
	 *
	 * @param message Message to print
	 */
	public static void info(String message) {
		levels[0].print(message);
	}

	/**
	 * Prints out a message with debug level
	 *
	 * @param message Message to print
	 */
	public static void debug(String message) {
		levels[1].print(message);
	}

	/**
	 * Prints out a message with error level
	 *
	 * @param message Message to print
	 */
	public static void error(String message) {
		levels[2].print(message);
	}

	/**
	 * Prints out a message with warning level
	 *
	 * @param message Message to print
	 */
	public static void warning(String message) {
		levels[3].print(message);
	}
}