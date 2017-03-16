package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.item.blocks.Block;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.ui.UiButton;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.world.gen.WorldProvider;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {
	public GamePlayState() {
		String worldName = "test";
		if (WorldProvider.exists(worldName)) {
			LastTry.world = WorldProvider.load(worldName);
		} else {
			LastTry.world = WorldProvider.generate(worldName, 512, 512); // Must be power of two
		}
		LastTry.player = new Player("George");
		LastTry.player.spawn(16, 117);

		// LastTry.world.spawnEnemy(EnemyID.eyeOfCthulhu, 30, 110);
		LastTry.world.spawnEnemy(EnemyID.greenSlime, 40, 110);
		LastTry.world.spawnEnemy(EnemyID.blueSlime, 10, 110);

		LastTry.modLoader = new ModLoader();
		LastTry.modLoader.load();

		LastTry.ui.add(LastTry.player.inventory);
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		LastTry.graphics.setFont(Assets.font);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {
		LastTry.camera.set(graphics);
		LastTry.world.render();
		LastTry.player.render();
		LastTry.camera.unset(graphics);

		int mouseX = LastTry.input.getMouseX();
		int mouseY = LastTry.input.getMouseY();

		Assets.radialTexture.draw(mouseX - mouseX % Block.TEX_SIZE - 48 - LastTry.camera.getX() % Block.TEX_SIZE,
				mouseY - mouseY % Block.TEX_SIZE - 48 - LastTry.camera.getY() % Block.TEX_SIZE);

		// TODO: fix it position

		int hp = LastTry.player.getHp();
		int x = LastTry.getWindowWidth() - 260;

		LastTry.graphics.getFont().drawString(x, 4, String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()));

		for(int i = 0; i < hp / 20; i++) {
			Assets.hpTexture.draw(x + i * 22 + i * 2, 28);
		}

		LastTry.ui.render();
		LastTry.debug.render();
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int dt) throws SlickException {
		LastTry.world.update(dt);
		LastTry.player.update(dt);

		LastTry.camera
				.setPosition(
						Math.min((LastTry.world.getWidth() - 1) * Block.TEX_SIZE - LastTry.getWindowWidth(),
								Math.max(Block.TEX_SIZE,
										LastTry.player.getX() + LastTry.player.getWidth() / 2
												- LastTry.getWindowWidth() / 2)),
				Math.min((LastTry.world.getHeight() - 1) * Block.TEX_SIZE - LastTry.getWindowHeight(), Math.max(
						Block.TEX_SIZE,
						LastTry.player.getY() + LastTry.player.getHeight() / 2 - LastTry.getWindowHeight() / 2)));

		if (LastTry.input.isKeyPressed(Input.KEY_TAB)) {
			LastTry.debug.toggle();
		}
	}

}
