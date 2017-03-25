package org.egordorichev.lasttry;

import org.egordorichev.lasttry.effect.Buff;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.modifier.MeleeModifier;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.world.Environment;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldProvider;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GamePlayState extends BasicGameState {
	/** World name */
	private String worldName;

	public GamePlayState(String worldName) {
		this.worldName = worldName;

		int worldWidth = 512;
		int worldHeight = 512;

		if (WorldProvider.exists(this.worldName)) {
			LastTry.world = WorldProvider.load(this.worldName);
		} else {
			LastTry.world = WorldProvider.generate(this.worldName, (short) worldWidth, (short) worldHeight,
				World.CRIMSON | World.EXPERT);
		}

		int spawnX = worldWidth / 2;
		int spawnY = 3; // TODO: replace

		LastTry.environment = new Environment();
		LastTry.entityManager = new EntityManager();
		LastTry.player = new Player("George");
		LastTry.player.spawn(spawnX, spawnY);

		for (int i = 0; i < 10; i++) {
			LastTry.entityManager.spawnEnemy(EnemyID.greenSlime, 40 + LastTry.random.nextInt(100), 60);
			LastTry.entityManager.spawnEnemy(EnemyID.blueSlime, 10 + LastTry.random.nextInt(100), 60);
		}

		LastTry.modLoader = new ModLoader();
		LastTry.modLoader.load();

		LastTry.ui.add(LastTry.player.inventory);

		LastTry.player.inventory.add(new ItemHolder(Item.woodenSword, 1, MeleeModifier.legendary));
		LastTry.player.inventory.add(new ItemHolder(Item.ironPickaxe, 1, MeleeModifier.light));
		LastTry.player.inventory.add(new ItemHolder(Item.crimsandBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.crimstoneBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.redIceBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.viciousMushroom, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.stoneBlock, 999));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonsandBlock, 990));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonsandBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonstoneBlock, 200));
		LastTry.player.inventory.add(new ItemHolder(Item.purpleIceBlock, 10));
		LastTry.player.inventory.add(new ItemHolder(Item.vileMushroom, 10));

		LastTry.player.addEffect(Buff.ironskin, 240);
		LastTry.player.addEffect(Buff.regeneration, 240);
		LastTry.player.addEffect(Buff.honey, 30);
	}

	/**
	 * @return state ID, used for switching states
	 */
	@Override
	public int getID() {
		return 1;
	}

	/**
	 * Called on init
	 */
	@Override
	public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
		// Is not called by Slick
	}

	/**
	 * Render game
	 */
	@Override
	public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics)
			throws SlickException {

		if (Display.wasResized()) {
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight()); 
			// Doesn't work, why?																
			// FIXME
		}

		LastTry.environment.render();
		LastTry.camera.set();
		LastTry.world.render();
		LastTry.entityManager.render();
		LastTry.player.render();
		LastTry.camera.unset();

		int mouseX = LastTry.input.getMouseX();
		int mouseY = LastTry.input.getMouseY();

		Assets.radialTexture.draw(mouseX - mouseX % Block.TEX_SIZE - 48 - LastTry.camera.getX() % Block.TEX_SIZE,
			mouseY - mouseY % Block.TEX_SIZE - 48 - LastTry.camera.getY() % Block.TEX_SIZE); // TODO: fix its position

		int hp = LastTry.player.getHp();
		int x = LastTry.getWindowWidth() - 260;

		Assets.font.drawString(String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()), x, 4);

		for (int i = 0; i < hp / 20; i++) {
			Assets.hpTexture.draw(x + i * 22 + i * 2, 28);
		}

		LastTry.ui.render();
		LastTry.player.renderBuffs();
		LastTry.debug.render();
	}
	
	/**
	 * Updates game logic
	 */
	@Override
	public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int dt) throws SlickException {
		LastTry.environment.update(dt);
		LastTry.entityManager.update(dt);
		LastTry.player.update(dt);

		LastTry.camera.setPosition(Math.min((LastTry.world.getWidth() - 1) * Block.TEX_SIZE - LastTry.getWindowWidth(),
			Math.max(Block.TEX_SIZE, LastTry.player.getX() + LastTry.player.getWidth() / 2 - LastTry.getWindowWidth() / 2)),
			Math.min((LastTry.world.getHeight() - 1) * Block.TEX_SIZE - LastTry.getWindowHeight(), 
			Math.max(Block.TEX_SIZE, LastTry.player.getY() + LastTry.player.getHeight() / 2 - LastTry.getWindowHeight() / 2)));

		if (LastTry.input.isKeyPressed(Input.KEY_TAB)) {
			LastTry.debug.toggle();
		}
	}

	/** Saves the game */
	public void save() {
		if (LastTry.world != null) {
			WorldProvider.save(this.worldName, LastTry.world);
		}
	}
}