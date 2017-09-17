package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.apache.log4j.BasicConfigurator;
import org.egordorichev.lasttry.Args;
import org.egordorichev.lasttry.game.LastTry;
import org.egordorichev.lasttry.state.SplashState;

/** LastTry launcher */
public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
		config.height = 600;
		config.vSyncEnabled = true;
		config.fullscreen = false;
		config.resizable = false;
		config.addIcon("icon.png", Files.FileType.Internal);

		try {
			Args.parse(args, config);
		} catch (Exception exception) {
			exception.printStackTrace();
			return; // Important!
		}
		BasicConfigurator.configure();

		new LwjglApplication(new LastTry(new SplashState()), config);
	}
}