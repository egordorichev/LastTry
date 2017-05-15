package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.egordorichev.lasttry.LastTry;

public class DesktopLauncher {
    /** App main */
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        config.width = 800;
        config.height = 600;
		config.vSyncEnabled = true;
	    config.addIcon("Icon.png", Files.FileType.Internal);

	    if (args.length > 0 && args[0].equals("-d")) {
			LastTry.release = false;
		}

        new LwjglApplication(new LastTry(), config);
    }
}