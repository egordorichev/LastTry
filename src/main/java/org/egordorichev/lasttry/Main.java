package org.egordorichev.lasttry;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.ScalableGame;

public class Main {
	/**
	 * App main. Creates a new game, setups the window and runs it
	 * @param arguments command line arguments, never used
	 */
	public static void main(String[] arguments) {
		try {
			LastTry.app = new AppGameContainer(new ScalableGame(new LastTry(), 800, 600, true));

			LastTry.app.setDisplayMode(800, 600, false);
			LastTry.app.setVSync(true);
			LastTry.app.setShowFPS(false);
			LastTry.app.start();
		} catch (Exception exception) {
			LastTry.handleException(exception);
		}
	}
}
