package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.player.PlayerInfo;
import org.egordorichev.lasttry.entity.player.PlayerProvider;
import org.egordorichev.lasttry.entity.player.PlayerType;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.ui.*;
import org.egordorichev.lasttry.world.WorldInfo;
import org.egordorichev.lasttry.world.WorldProvider;

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

	/** Panel, used for world selection */
	private UiPanel worldSelect;

	/** Panel, used for generating new world */
	private UiPanel worldNew;

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
			private UiCardHolder playerCards;
			private PlayerInfo[] playerInfos;
			private PlayerInfo selectedPlayer;

			@Override
			public void onShow() {
				clear();

				playerInfos = PlayerProvider.getPlayers();

				playerCards = new UiCardHolder(new Rectangle(0, -250, 256, 0), Origin.CENTER) {
					@Override
					public void addComponents() {
						for (int i = 0; i < playerInfos.length; i++) {
							final int current = i;

							add(new UiPlayerCard(new Rectangle(0, 0, 0, 0), Origin.TOP_LEFT,
									playerInfos[i]) {

								@Override
								public void onClick() {
									selectPlayer(current);
								}
							});
						}
					}
				};

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

				add(playerCards);
			}

			private void selectPlayer(int player) {
				selectedPlayer = playerInfos[player];
				playerSelect.hide();
				worldSelect.show();
			}
		};

		this.playerSelect.hide();

		this.worldSelect = new UiPanel() { // TODO
			private UiCardHolder worldCards;
			private WorldInfo[] worldInfos;
			private WorldInfo selectedWorld;

			@Override
			public void addComponents() {
				clear();

				worldInfos = WorldProvider.getWorlds();

				worldCards = new UiCardHolder(new Rectangle(0, -250, 256, 0), Origin.CENTER) {
					@Override
					public void addComponents() {
						for (int i = 0; i < worldInfos.length; i++) {
							final int current = i;

							add(new UiWorldCard(new Rectangle(0, 0, 0, 0), Origin.TOP_LEFT,
									worldInfos[i]) {

								@Override
								public void onClick() {
									selectWorld(current);
								}
							});
						}
					}
				};

				add(new UiTextButton(new Rectangle(-32, 144, 100, 32), Origin.CENTER, "New") {
					@Override
					public void onClick() {
						worldSelect.hide();
						worldNew.show();
					}
				});

				add(new UiTextButton(new Rectangle(32, 144, 100, 32), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						worldSelect.hide();
						playerSelect.show();
					}
				});

				add(worldCards);
			}

			private void selectWorld(int world) {
				this.selectedWorld = this.worldInfos[world];
				worldSelect.hide();
				LastTry.instance.setScreen(new GamePlayState()); // TODO: pass the player and the world
			}
		};

		this.worldSelect.hide();

		this.worldNew = new UiPanel() {
			@Override
			public void onShow() {
				LastTry.instance.setScreen(new GamePlayState());

			}
		};

		this.worldNew.hide();

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
		LastTry.ui.add(this.worldSelect);
		LastTry.ui.add(this.worldNew);
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