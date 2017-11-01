package org.egordorichev.lasttry.core.boot;

/**
 * Handles one shell command
 */
public class Command {
	/**
	 * Command name
	 */
	private String name;
	/**
	 * Shortcut
	 */
	private String shortName;

	public Command(String name, String shortName) {
		this.name = name;
		this.shortName = shortName;
	}

	/**
	 * The actual command
	 *
	 * @param args Command args
	 * @return True, if there was a error
	 */
	public boolean call(String[] args) {
		return false;
	}

	/**
	 * @return Command name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * @return Command short name
	 */
	public String getShortName() {
		return this.shortName;
	}
}