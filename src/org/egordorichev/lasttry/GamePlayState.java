package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.item.Block;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.ui.UiButton;
import org.egordorichev.lasttry.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {
	public GamePlayState() {
		LastTry.world = new World("test");
		LastTry.player = new Player("George");
		LastTry.player.spawn(14, 48);

		LastTry.world.spawnEnemy("Green Slime", 20, 49);

		LastTry.modLoader = new ModLoader();
		LastTry.modLoader.load();
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		//LastTry.graphics.setFont(Assets.font);

		LastTry.ui.add(new UiButton(new Rectangle(10, 10, 60, 32), "Hello!"));
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		LastTry.camera.set(graphics);
		LastTry.world.render();
		LastTry.player.render();
		LastTry.camera.unset(graphics);
		LastTry.ui.render();

		graphics.drawString(String.valueOf(gameContainer.getFPS()), 10, LastTry.getWindowHeight() - 30);
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int dt) throws SlickException {
		LastTry.world.update(dt);
		LastTry.player.update(dt);

		LastTry.camera.setPosition(Math.min((LastTry.world.getWidth() - 1) * Block.TEX_SIZE - LastTry.getWindowWidth(), Math.max(Block.TEX_SIZE, LastTry.player.getX() + LastTry.player.getWidth() / 2 - LastTry.getWindowWidth() / 2)),
			Math.min((LastTry.world.getHeight() - 1) * Block.TEX_SIZE - LastTry.getWindowHeight(), Math.max(Block.TEX_SIZE, LastTry.player.getY() + LastTry.player.getHeight() / 2 - LastTry.getWindowHeight() / 2)));
	}
}
