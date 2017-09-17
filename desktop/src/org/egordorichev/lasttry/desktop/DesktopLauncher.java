package org.egordorichev.lasttry.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.apache.log4j.BasicConfigurator;
import org.egordorichev.lasttry.core.context.Context;
import org.egordorichev.lasttry.game.LastTry;
import org.egordorichev.lasttry.game.state.GameState;

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
		BasicConfigurator.configure();

		new LwjglApplication(new LastTry(new GameState() {
			@Override
			public void load(Context rootContext) {

			}

			@Override
			public void update() {

			}

			@Override
			public void render(float deltaT) {

			}

			@Override
			public void dispose() {

			}
		}));
	}
}