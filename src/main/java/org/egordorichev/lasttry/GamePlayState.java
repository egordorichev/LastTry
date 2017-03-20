package org.egordorichev.lasttry;

import org.egordorichev.lasttry.entity.Enemy;
import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.modifier.Modifier;
import org.egordorichev.lasttry.item.tiles.Block;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.world.WorldProvider;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {
	private Enemy enemy;

	public GamePlayState() {
		String worldName = "test";
		// Must be powers of two
		int worldWidth = 512;
		int worldHeight = 512;

		if (WorldProvider.exists(worldName)) {
			LastTry.world = WorldProvider.load(worldName);
		} else {
			LastTry.world = WorldProvider.generate(worldName, worldWidth, worldHeight);
		}

		int spawnX = worldWidth / 2;
		int spawnY = LastTry.world.getHighest(spawnX);

		LastTry.player = new Player("George");
		LastTry.player.spawn(spawnX, spawnY);

		for(int i = 0; i < 10; i++) {
			LastTry.world.spawnEnemy(EnemyID.greenSlime, 40 + LastTry.random.nextInt(100), 60);
			LastTry.world.spawnEnemy(EnemyID.blueSlime, 10 + LastTry.random.nextInt(100), 60);
		}

		LastTry.modLoader = new ModLoader();
		LastTry.modLoader.load();

		LastTry.ui.add(LastTry.player.inventory);

		LastTry.player.inventory.add(new ItemHolder(Item.woodenSword, 1, Modifier.melee.legendary));
		LastTry.player.inventory.add(new ItemHolder(Item.ironPickaxe, 1, Modifier.melee.light));
		LastTry.player.inventory.add(new ItemHolder(Item.crimsandBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.crimstoneBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.redIceBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.viciousMushroom, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.stoneBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonsandBlock, 990));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonsandBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonstoneBlock, 200));
		LastTry.player.inventory.add(new ItemHolder(Item.purpleIceBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.vileMushroom, 10));

		this.enemy = LastTry.world.spawnEnemy(EnemyID.eyeOfCthulhu, LastTry.player.getGridX(), LastTry.player.getGridY() - 100);
	}

	@Override
	public int getID() {
		return 1;
	}

	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {

		if (Display.wasResized()) {
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight()); // Doesnt
																			// work,
																			// FIXME
		}

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

		Assets.font.drawString(x, 4, String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()));

		for (int i = 0; i < hp / 20; i++) {
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
			this.enemy.die();
		}
	}

}
