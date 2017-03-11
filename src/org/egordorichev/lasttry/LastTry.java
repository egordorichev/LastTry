package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.world.World;
import org.newdawn.slick.*;

public class LastTry extends BasicGame {
	private static int windowWidth;
	private static int windowHeight;
	private GameContainer container;

	public static Input input;
	public static World world;
	public static Player player;
	public static Camera camera;

	public LastTry() {
		super("LastTry");
	}

	@Override
	public void init(GameContainer gameContainer) throws SlickException {
		this.container = gameContainer;
		this.container.getGraphics().setBackground(new Color(129, 207, 224));

		camera = new Camera();
		input = this.container.getInput();

		windowWidth = this.container.getWidth();
		windowHeight = this.container.getHeight();

		world = new World("test");

		player = new Player("George");
		player.spawn(10, 10);
	}

	@Override
	public void update(GameContainer gameContainer, int dt) throws SlickException {
		if(input.isKeyDown(Input.KEY_W)) {
			player.moveBy(0, -1);
		} else if(input.isKeyDown(Input.KEY_A)) {
			player.moveBy(-1, 0);
		} else if(input.isKeyDown(Input.KEY_S)) {
			player.moveBy(0, 1);
		} else if(input.isKeyDown(Input.KEY_D)) {
			player.moveBy(1, 0);
		}

		world.update(dt);
		player.update(dt);

		camera.setPosition(Math.min(world.getWidth() * Block.size - windowWidth, Math.max(0, player.getX() + player.getWidth() / 2 - windowWidth / 2)),
			Math.min(world.getWidth() * Block.size - windowHeight, Math.max(0, player.getY() + player.getHeight() / 2 - windowHeight / 2)));
	}

	@Override
	public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
		camera.set(graphics);
		world.render();
		player.render();
		camera.unset(graphics);
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