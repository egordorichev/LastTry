package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.player.PlayerInfo;
import org.egordorichev.lasttry.entity.player.PlayerProvider;
import org.egordorichev.lasttry.entity.player.PlayerType;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.ui.UiPanel;
import org.egordorichev.lasttry.ui.UiTextButton;
import org.egordorichev.lasttry.ui.UiTextInput;
import org.egordorichev.lasttry.ui.UiTextLabel;

public class MenuState implements State {
	/** Panel, where the main menu is stored */
	private UiPanel mainMenu;

	/** Panel, where the player select menu is stored */
	private UiPanel playerSelect;

	/** Panel, where new player is created */
	private UiPanel playerNew;

	/** Panel, for selecting player's name */
	private UiPanel playerName;

	/** Player type */ // TODO: input it
	private PlayerType playerType = PlayerType.SOFTCORE;

	public MenuState() {
		this.mainMenu = new UiPanel() {
			@Override
			public void addComponents() {
				add(new UiTextButton(new Rectangle(0, 0, 150, 32), Origin.CENTER, "Single Player") {
					@Override
					public void onClick() {
						mainMenu.hide();
						playerSelect.show();
					}
				});

				add(new UiTextButton(new Rectangle(0, 48, 150, 32), Origin.CENTER, "Multiplayer") {
					@Override
					public void onClick() {
						LastTry.log("Multiplayer is not implemented yet");
					}
				});

				add(new UiTextButton(new Rectangle(0, 96, 120, 32), Origin.CENTER, "Settings") {
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

		this.playerSelect = new UiPanel() {
			@Override
			public void onShow() {
				clear();

				PlayerInfo[] players = PlayerProvider.getPlayers();

				for (int i = 0; i < players.length; i++) {
					add(new UiTextButton(new Rectangle(0, -48 + i * 48, 0, 0), Origin.CENTER, players[i].name) {
						@Override
						public void onClick() {
							// TODO
						}
					});
				}

				add(new UiTextButton(new Rectangle(-32, 144, 100, 32), Origin.CENTER, "New") {
					@Override
					public void onClick() {
						playerSelect.hide();
						playerNew.show();
					}
				});

				add(new UiTextButton(new Rectangle(32, 144, 100, 32), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						playerSelect.hide();
						mainMenu.show();
					}
				});
			}
		};

		this.playerSelect.hide();

		this.playerNew = new UiPanel() {
			@Override
			public void addComponents() {
				add(new UiTextButton(new Rectangle(0, -64, 100, 32), Origin.CENTER, "Hair") {
					@Override
					public void onClick() {
						LastTry.log("TODO");
					}
				});

				add(new UiTextButton(new Rectangle(0, -32, 100, 32), Origin.CENTER, "Eyes") {
					@Override
					public void onClick() {
						LastTry.log("TODO");
					}
				});

				add(new UiTextButton(new Rectangle(0, -0, 100, 32), Origin.CENTER, "Skin") {
					@Override
					public void onClick() {
						LastTry.log("TODO");
					}
				});

				add(new UiTextButton(new Rectangle(0, 32, 100, 32), Origin.CENTER, "Clothes") {
					@Override
					public void onClick() {
						LastTry.log("TODO");
					}
				});

				add(new UiTextButton(new Rectangle(0, 64, 100, 32), Origin.CENTER, "Softcore") {
					@Override
					public void onClick() {
						LastTry.log("TODO");
					}
				});

				add(new UiTextButton(new Rectangle(-32, 128, 100, 32), Origin.CENTER, "Create") {
					@Override
					public void onClick() {
						playerNew.hide();
						playerName.show();
					}
				});

				add(new UiTextButton(new Rectangle(32, 128, 100, 32), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						playerNew.hide();
						playerSelect.show();
					}
				});
			}
		};

		this.playerNew.hide();

		this.playerName = new UiPanel() {
			private UiTextInput nameInput;

			@Override
			public void addComponents() {
				add(new UiTextLabel(new Rectangle(0, -64, 100, 32), Origin.CENTER, "Enter new player name:") {
					@Override
					public void onClick() {
						playerName.hide();
						playerSelect.show();
					}
				});

				this.nameInput = new UiTextInput(new Rectangle(0, 0, 100, 32), Origin.CENTER);

				add(nameInput);

				add(new UiTextButton(new Rectangle(32, 128, 100, 32), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						playerName.hide();
						playerNew.show();
					}
				});

				add(new UiTextButton(new Rectangle(-32, 128, 100, 32), Origin.CENTER, "Create") {
					@Override
					public void onClick() {
						PlayerProvider.create(nameInput.getText(), playerType);

						playerName.hide();
						playerSelect.show();
					}
				});
			}
		};

		this.playerName.hide();

		LastTry.ui.add(this.mainMenu);
		LastTry.ui.add(this.playerSelect);
		LastTry.ui.add(this.playerNew);
		LastTry.ui.add(this.playerName);
	}

	/** Never used */
	@Override
	public void show() {

	}

	/**
	 * Renders and updates the splash
	 * @param delta delta from last update
	 */
	@Override
	public void render(float delta) {
		for (int i = 0; i < Gdx.graphics.getWidth() / 48 + 1; i++) {
			LastTry.batch.draw(Textures.sky, i * 48, 0);
		}

		LastTry.ui.render();
	}

	/** Never used */
	@Override
	public void resize(int width, int height) {

	}

	/** Never used */
	@Override
	public void pause() {

	}

	/** Never used */
	@Override
	public void resume() {

	}

	/** Never used */
	@Override
	public void hide() {
	}

	/** Never used */
	@Override
	public void dispose() {

	}
}