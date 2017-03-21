package org.egordorichev.lasttry;

import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.graphics.Assets;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;

public class SplashState extends BasicGameState {
	/**
	 * Indicates if it can skip the splash, after loading content
	 */
	private final static boolean SKIP_SPLASH = true;
	
	/**
	 * Total splashes count, will be selected random with name LastTry.random.nextInt(SPLASH_COUNT) + ".png"
	 */
	private final static int SPLASH_COUNT = 1;
	
	/**
	 * Fade in and fade out speed
	 */
	private final static float ANIMATION_SPEED = 0.2f;
	
	/**
	 * The splash texture
	 */
	private Image splash;
	
	/**
	 * Indicates, if splash is fading out or in
	 */
	private boolean fadeUp = true;
	
	/**
	 * Time, splash takes to play
	 */
	private int delay = 60;
	
	/**
	 * Indicates, if the assets are ready
	 */
	private volatile boolean loaded;
	
	/**
	 * Message, showed on screen, while loading
	 */
	private volatile String loadMessage;

	/**
	 * @return state id, used for switching to other game states
	 */
	@Override
	public int getID() {
		return 0;
	}

	/**
	 * Loads the splash, and starts assets loader thread
	 */
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		this.splash = new Image("assets/images/splashes/" + (LastTry.random.nextInt(SPLASH_COUNT) + 1) + ".png");
		this.splash.setAlpha(0.1f);

		Image cursor = new Image("assets/images/Cursor.png");
		gameContainer.setMouseCursor(cursor, 0, 0);

		this.loaded = false;

		Display.setResizable(true);

		new Thread() {
			@Override
			public void run() {
				loadMessage = "Loading assets...";
				Assets.preload();
				loadMessage = "Loading items...";
				Item.preload();
				loadMessage = "Done!";
				loaded = true;
			}
		}.run();
	}

	/**
	 * Renders the splash
	 */
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {

		if (Display.wasResized()) {
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
			// Does not work, FIXME
		}
		
		this.splash.draw(0, 0, 800, 600);
		Assets.smallFont.drawString(10, 10, loadMessage);
	}

	/** 
	 * Updates splash animation
	 */
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int dt) throws SlickException {
		if (this.fadeUp) {
			if (this.splash.getAlpha() < 1.0f) { // Fade into splash
				this.splash.setAlpha(this.splash.getAlpha() + ANIMATION_SPEED);
			} else if (this.delay > 0) { // Stay on splash for roughly a second
				this.delay--;
			} else { // Begin fade-out
				this.fadeUp = false;
			}
		} else {
			if (this.splash.getAlpha() > 0.1f) {
				this.splash.setAlpha(this.splash.getAlpha() - ANIMATION_SPEED);
			}
		}
		
		// Skip if not loaded
		if (!this.loaded) {
			return;
		}
		
		boolean animationComplete = !this.fadeUp && this.splash.getAlpha() <= 0.1f;
		
		if (SKIP_SPLASH || (animationComplete)) {
			stateBasedGame.addState(new GamePlayState());
			stateBasedGame.enterState(1);
		}
	}
}
