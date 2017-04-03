package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.player.PlayerInfo;
import org.egordorichev.lasttry.entity.player.PlayerProvider;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.ui.*;
import org.egordorichev.lasttry.world.*;
import org.egordorichev.lasttry.world.environment.Environment;

public class MenuState implements State {
	/** Panel, where the main menu is stored */
	private UiPanel mainMenu;

	/** Panel, where the player select menu is stored */
	private UiPanel playerSelect;

	/** Panel, where new player is created */
	private UiPanel playerNew;

	/** Panel, for selecting player's name */
	private UiPanel playerName;

	/** Player info */
	private PlayerInfo playerInfo;

	/** Panel, used for world selection */
	private UiPanel worldSelect;

	/** Panel, used for generating new world */
	private UiPanel worldNew;

	/** Panel, used for selecting world evil */
	private UiPanel worldEvil;

	/** Panel, used for world naming */
	private UiPanel worldName;

	/** World info */
	private WorldInfo worldInfo;

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
				LastTry.playerInfo = playerInfos[player];
				playerSelect.hide();
				worldSelect.show();
			}
		};

		this.playerSelect.hide();

		this.worldSelect = new UiPanel() { // TODO
			private UiCardHolder worldCards;
			private WorldInfo[] worldInfos;

			@Override
			public void onShow() {
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
				LastTry.worldInfo = this.worldInfos[world];
				worldSelect.hide();
				LastTry.instance.setScreen(new LoadState());
			}
		};

		this.worldSelect.hide();

		this.worldNew = new UiPanel() {
			@Override
			public void addComponents() {
				worldInfo = new WorldInfo();

				add(new UiTextLabel(new Rectangle(0, -100, 0, 0), Origin.CENTER, "Select world size:"));

				add(new UiTextButton(new Rectangle(0, 0, 0, 0), Origin.CENTER, "Small") {
					@Override
					public void onClick() {
						worldInfo.size = WorldSize.SMALL;
						next();
					}
				});

				add(new UiTextButton(new Rectangle(0, 50, 0, 0), Origin.CENTER, "Medium") {
					@Override
					public void onClick() {
						worldInfo.size = WorldSize.MEDIUM;
						next();
					}
				});

				add(new UiTextButton(new Rectangle(0, 100, 0, 0), Origin.CENTER, "Large") {
					@Override
					public void onClick() {
						worldInfo.size = WorldSize.LARGE;
						next();
					}
				});

				add(new UiTextButton(new Rectangle(0, 200, 0, 0), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						worldNew.hide();
						worldSelect.show();
					}
				});
			}

			private void next() {
				worldNew.hide();
				worldEvil.show();
			}
		};

		this.worldNew.hide();

		this.worldEvil = new UiPanel() {
			@Override
			public void addComponents() {
				add(new UiTextLabel(new Rectangle(0, -100, 0, 0), Origin.CENTER, "Select world evil:"));

				add(new UiTextButton(new Rectangle(0, 0, 0, 0), Origin.CENTER, "Corruption") {
					@Override
					public void onClick() {
						next();
					}
				});

				add(new UiTextButton(new Rectangle(0, 50, 0, 0), Origin.CENTER, "Crimson") {
					@Override
					public void onClick() {
						worldInfo.flags |= World.CRIMSON;
						next();
					}
				});

				add(new UiTextButton(new Rectangle(0, 100, 0, 0), Origin.CENTER, "Random") {
					@Override
					public void onClick() {
						if (LastTry.random.nextBoolean()) {
							worldInfo.flags |= World.CRIMSON;
						}

						next();
					}
				});

				add(new UiTextButton(new Rectangle(0, 200, 0, 0), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						worldEvil.hide();
						worldNew.show();
					}
				});
			}

			private void next() {
				worldEvil.hide();
				worldName.show();
			}
		};

		this.worldEvil.hide();

		this.worldName = new UiPanel() {
			private UiTextInput nameInput;

			@Override
			public void show() {
				super.show();
				this.nameInput.clear();
			}

			@Override
			public void addComponents() {

				add(new UiTextLabel(new Rectangle(0, -64, 100, 32), Origin.CENTER, "Enter world name:") {
					@Override
					public void onClick() {
						worldName.hide();
						worldSelect.show();
					}
				});

				this.nameInput = new UiTextInput(new Rectangle(0, 0, 100, 32), Origin.CENTER);

				add(nameInput);

				add(new UiTextButton(new Rectangle(32, 128, 100, 32), Origin.CENTER, "Back") {
					@Override
					public void onClick() {
						worldName.hide();
						worldNew.show();
					}
				});

				add(new UiTextButton(new Rectangle(-32, 128, 100, 32), Origin.CENTER, "Create") {
					@Override
					public void onClick() {
						worldInfo.name = nameInput.getText();

						WorldProvider.generate(worldInfo);
						LastTry.environment = new Environment();
						WorldProvider.save(LastTry.world);

						worldName.hide();
						worldSelect.show();
					}
				});
			}
		};

		this.worldName.hide();

		this.playerNew = new UiPanel() {
			@Override
			public void addComponents() {
				playerInfo = new PlayerInfo();

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
			public void show() {
				super.show();
				this.nameInput.clear();
			}

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
						playerInfo.name = nameInput.getText();

						PlayerProvider.create(playerInfo);

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
		LastTry.ui.add(this.worldEvil);
		LastTry.ui.add(this.worldName);
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