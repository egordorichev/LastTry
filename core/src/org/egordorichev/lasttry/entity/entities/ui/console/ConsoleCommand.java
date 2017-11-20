package org.egordorichev.lasttry.entity.entities.ui.console;

/**
 * Represents one console command
 */
public class ConsoleCommand {
	/**
	 * The name of the command
	 */
	private String name;

	public ConsoleCommand(String name) {
		this.name = name;
	}

	/**
	 * Runs the command
	 *
	 * @param console Link to the console
	 * @param args Command args
	 */
	public void run(UiConsole console, String[] args) {

	}

	/**
	 * @return Command name
	 */
	public String getName() {
		return this.name;
	}
}