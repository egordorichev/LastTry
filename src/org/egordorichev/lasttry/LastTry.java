package org.egordorichev.lasttry;

import org.egordorichev.lasttry.world.World;
import org.newdawn.slick.*;

public class LastTry extends BasicGame {
	private static int windowWidth;
	private static int windowHeight;
	private GameContainer container;

	public static Input input;
	public static World world;

	public LastTry() {
		super("LastTry");
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		this.container = gameContainer;
		this.container.getGraphics().setBackground(new Color(129, 207, 224));

		input = this.container.getInput();

		windowWidth = this.container.getWidth();
		windowHeight = this.container.getHeight();

		world = new World("test");

		world.spawnEnemy("Green Slime", 64, 64);
	}

	@Override
	public void update(GameContainer gameContainer, int dt) throws SlickException {
		world.update(dt);
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		Camera.set(graphics);
		world.render();
		Camera.unset(graphics);
	}

	@Override
	public boolean closeRequested() {
		world.save();
		System.exit(0);
		return false;
	}

	@Override
	public void keyPressed(int key, char c) {

	}

	@Override
	public void keyReleased(int key, char c) {

	}

	public static int getWindowWidth() {
		return windowWidth;
	}

	public static int getWindowHeight() {
		return windowHeight;
	}

	public static void main(String[] arguments) {
		try {
			AppGameContainer app = new AppGameContainer(new LastTry());

			app.setDisplayMode(640, 480, false);
			app.setVSync(true);
			app.start();
		} catch(SlickException exception) {
			exception.printStackTrace();
		}
	}
}