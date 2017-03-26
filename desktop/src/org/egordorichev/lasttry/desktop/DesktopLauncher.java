package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.egordorichev.lasttry.LastTry;

public class DesktopLauncher {
	/**
	 * App main
	 * @param arg never used
	 */
	public static void main(String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
		config.height = 600;

		new LwjglApplication(new LastTry(), config);
	}
}