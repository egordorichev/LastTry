package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.core.Version;
import org.egordorichev.lasttry.core.boot.ArgumentParser;

/** LastTry launcher */
public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "LastTry " + Version.STRING;
		config.width = 320;
		config.height = 180;
		config.vSyncEnabled = true;
		config.fullscreen = false;
		config.resizable = true;

		// config.addIcon("icon.png", Files.FileType.Internal);

		ArgumentParser.setArgs(args);

		new LwjglApplication(new LastTry());
	}
}