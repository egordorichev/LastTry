package org.egordorichev.lasttry;

import org.egordorichev.lasttry.util.Assets;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;

public class SplashState extends BasicGameState {
	private final static boolean SKIP_SPLASH = false;
	private final static int SPLASH_COUNT = 1;
	private Image splash;
	private boolean fadeUp = true;
	private int delay = 60;
	private boolean loaded;

	@Override
	public int getID() {
		return 0;
	}

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
				Assets.preload();
				loaded = true;
			}
		}.run();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {
		if (Display.wasResized()) {
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight()); // Doesnt
																			// work,
																			// FIXME
		}

		float animSpeed = 0.02f;
		if (fadeUp) {
			// Fade into splash
			if (this.splash.getAlpha() < 1.0f) {
				this.splash.setAlpha(this.splash.getAlpha() + animSpeed);
			} 
			// Stay on splash for roughly a second
			else if (delay > 0) {
				delay--;
			} 
			// Begin fade-out
			else {
				fadeUp = false;
			}
		} else {
			if (this.splash.getAlpha() > 0.1f) {
				this.splash.setAlpha(this.splash.getAlpha() - animSpeed);
			}
		}

		this.splash.draw(0, 0, 800, 600);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
		// Skip if not loaded
		if (!this.loaded) {
			return;
		}
		boolean fadeComplete = !fadeUp && this.splash.getAlpha() <= 0.1f;
		if (SKIP_SPLASH || (fadeComplete)) {
			stateBasedGame.addState(new GamePlayState());
			stateBasedGame.enterState(1);
		}
	}
}
