package org.egordorichev.lasttry;

import org.egordorichev.lasttry.ui.UiButton;
import org.egordorichev.lasttry.ui.UiPanel;
import org.egordorichev.lasttry.ui.UiTextButton;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MenuState extends BasicGameState {
	/** Here all the state gui is placed */
	private UiPanel panel;

	public MenuState() {
		this.panel = new UiPanel(new Rectangle(0, 0, 0, 0)) {
			@Override
			public void addComponents() {
				add(new UiTextButton(new Rectangle(0, 0, 72, 32), Origin.CENTER, "Single Player") {
					@Override
					public void onClick() {
						panel.hide();
						LastTry.setState(new GamePlayState());
					}
				});
			}
		};

		LastTry.ui.add(this.panel);
	}

	/**
	 * Returns state ID
	 * @return state ID
	 */
	public int getID() {
		return 2;
	}

	/** Init all */
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		// Is not called by Slick
	}

	/** Render gui */
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {

		LastTry.graphics.resetTransform();
		LastTry.ui.render();
	}

	/** Update all */
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int dt) throws SlickException {

	}
}