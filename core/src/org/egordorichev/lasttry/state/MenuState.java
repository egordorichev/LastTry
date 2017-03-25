package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.ui.UiPanel;
import org.egordorichev.lasttry.ui.UiTextButton;

public class MenuState implements State {
	/** Panel, where all gui is stored */
	private UiPanel panel;

	public MenuState() {
		this.panel = new UiPanel(new Rectangle(0, 0, 0, 0)) {
			@Override
			public void addComponents() {
				add(new UiTextButton(new Rectangle(0, 0, 100, 32), Origin.CENTER, "Single Player") {
					@Override
					public void onClick() {
						panel.hide();
						LastTry.instance.setScreen(new GamePlayState());
					}
				});

				add(new UiTextButton(new Rectangle(0, 48, 100, 32), Origin.CENTER, "Multiplayer") {
					@Override
					public void onClick() {
						LastTry.log("Multiplayer is not implemented yet");
					}
				});

				add(new UiTextButton(new Rectangle(0, 96, 100, 32), Origin.CENTER, "Settings") {
					@Override
					public void onClick() {
						LastTry.log("Settings are not implemented yet");
					}
				});

				add(new UiTextButton(new Rectangle(0, 144, 100, 32), Origin.CENTER, "Exit") {
					@Override
					public void onClick() {
						Gdx.app.exit();
					}
				});
			}
		};

		LastTry.ui.add(this.panel);
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Assets.font.draw(LastTry.batch, "Done!", 10, 10);
		LastTry.ui.render();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {

	}
}