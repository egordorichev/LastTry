package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import org.egordorichev.lasttry.Args;
import org.egordorichev.lasttry.LastTry;

/** LastTry launcher */
public class DesktopLauncher {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = 800;
		config.height = 600;
		config.vSyncEnabled = true;
		config.resizable = false;
		config.addIcon("Icon.png", Files.FileType.Internal);

		try {
			Args.parse(args);
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			return; // Arguments fail ;( Read the manual next time
		}

		new LwjglApplication(new LastTry(), config);
	}

	private static class ExitDumper extends SecurityManager {
		@Override
		public void checkExit(int status) {
			Thread.dumpStack();
		}
	}
}