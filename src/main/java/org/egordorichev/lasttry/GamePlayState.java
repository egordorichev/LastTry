package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.item.blocks.Block;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.ui.UiButton;
import org.egordorichev.lasttry.util.Assets;
import org.egordorichev.lasttry.world.World;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {
	public GamePlayState() {
		LastTry.world = new World("test");
		LastTry.player = new Player("George");
		LastTry.player.spawn(16, 117);

		//LastTry.world.spawnEnemy(EnemyID.eyeOfCthulhu, 30, 110);
		LastTry.world.spawnEnemy(EnemyID.greenSlime, 40, 110);
		LastTry.world.spawnEnemy(EnemyID.blueSlime, 10, 110);

		LastTry.modLoader = new ModLoader();
		LastTry.modLoader.load();

		LastTry.ui.add(new UiButton(new Rectangle(70, 10, 60, 32), "Hello!") {
			@Override
			public void onClick() {
				System.out.println("down!");
			}
		});
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
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
		LastTry.camera.set(graphics);
		LastTry.world.render();
		LastTry.player.render();
		LastTry.camera.unset(graphics);

		int mouseX = LastTry.input.getMouseX();
		int mouseY = LastTry.input.getMouseY();

		Assets.radialTexture.draw(mouseX - mouseX % Block.TEX_SIZE - 48 - LastTry.camera.getX() % Block.TEX_SIZE, mouseY - mouseY % Block.TEX_SIZE - 48 - LastTry.camera.getY() % Block.TEX_SIZE);

		// TODO: fix it position

		LastTry.ui.render();
		LastTry.debug.render();
	}

	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int dt) throws SlickException {
		LastTry.world.update(dt);
		LastTry.player.update(dt);

		LastTry.camera.setPosition(Math.min((LastTry.world.getWidth() - 1) * Block.TEX_SIZE - LastTry.getWindowWidth(),
			Math.max(Block.TEX_SIZE, LastTry.player.getX() + LastTry.player.getWidth() / 2 - LastTry.getWindowWidth() / 2)),
			Math.min((LastTry.world.getHeight() - 1) * Block.TEX_SIZE - LastTry.getWindowHeight(), Math.max(Block.TEX_SIZE,
			LastTry.player.getY() + LastTry.player.getHeight() / 2 - LastTry.getWindowHeight() / 2)));

		if(LastTry.input.isKeyPressed(Input.KEY_TAB)) {
			LastTry.debug.toggle();
		}
	}

}
