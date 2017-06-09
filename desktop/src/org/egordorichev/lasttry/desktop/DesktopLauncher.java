package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;
import org.egordorichev.lasttry.Args;
import org.egordorichev.lasttry.LastTry;

/** LastTry launcher */
public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
		config.height = 600;
		config.vSyncEnabled = true;
		config.fullscreen = false;
		config.resizable = false;
		config.addIcon("Icon.png", Files.FileType.Internal);

		try {
			Args.parse(args, config);
		} catch (Exception exception) {
			exception.printStackTrace();
			return; // Important!
		}

		new LwjglApplication(new LastTry(config.width, config.height), config);
	}
}