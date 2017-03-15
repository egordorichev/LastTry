package org.egordorichev.lasttry;

import org.egordorichev.lasttry.util.Assets;
import org.newdawn.slick.*;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.*;

public class SplashState extends BasicGameState {
	private Image splash;
	private boolean loaded;

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		this.splash = new Image("assets/images/splash" + (LastTry.random.nextInt(5) + 1) + ".png");
		this.loaded = false;

		new Thread() {
			@Override
			public void run() {
				Assets.preload();
				loaded = true;
			}
		}.run();
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		this.splash.draw(0, 0, 800, 600);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
		if(this.loaded) {
			stateBasedGame.addState(new GamePlayState());
			stateBasedGame.enterState(1);
		}
	}
}
