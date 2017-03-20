package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.util.Debug;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldProvider;
import org.egordorichev.lasttry.mod.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

public class LastTry extends StateBasedGame {
	public static GameContainer container;
	public static Input input;
	public static Graphics graphics;
	public static World world;
	public static Player player;
	public static Camera camera;
	public static ModLoader modLoader;
	public static UiManager ui;
	public static AppGameContainer app;
	public static Debug debug;
	public final static Log log = new Log();
	public final static Random random = new Random(System.currentTimeMillis());

	public LastTry() {
		super(new String[] { "LastTry: Dig Peon, Dig!", "LastTry: Epic Dirt", "LastTry: Hey Guys!",
				"LastTry: Sand is Overpowered", "LastTry: Part 3: The Return of the Guide", "LastTry: A Bunnies Tale",
				"LastTry: Dr. Bones and The Temple of Blood Moon", "LastTry: Slimeassic Park",
				"LastTry: The Grass is Greener on This Side",
				"LastTry: Small Blocks, Not for Children Under the Age of 5", "LastTry: Digger T' Blocks",
				"LastTry: There is No Cow Layer", "LastTry: Suspicous Looking Eyeballs", "LastTry: Purple Grass!",
				"LastTry: Noone Dug Behind!", "LastTry: Shut Up and Dig Gaiden!" }[random.nextInt(16)]);
	}

	@Override
	public void initStatesList(GameContainer gameContainer) throws SlickException {
		container = gameContainer;
		camera = new Camera();
		input = gameContainer.getInput();
		graphics = gameContainer.getGraphics();
		ui = new UiManager();
		debug = new Debug();
		graphics.setBackground(new Color(129, 207, 224));

		this.addState(new SplashState());
	}

	@Override
	public boolean closeRequested() {
		if (world != null) {
			WorldProvider.save(world);
		}
		System.exit(0);
		return false;
	}

	public static int getWindowWidth() {
		return container.getWidth();
	}

	public static int getWindowHeight() {
		return container.getHeight();
	}

	public static void log(String msg) {
		// TODO: Log with levels rather than enforcing all logs to be printed
		//
		// Even better, create a more vibrant logging system.
		//
		// Ideas:
		// - Logging shouldn't take TOO much space (messages should be compact)
		// - Logging should be able to tell what context it is from
		// - - Ex: "World: Loading..."
		// - - Ex: "Player: Player died at [X, Y]"
		log.info(msg);
	}

	public static void handleException(Exception exception) {
		exception.printStackTrace();
	}

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
