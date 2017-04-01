package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.Input;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Buff;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.player.*;
import org.egordorichev.lasttry.graphics.Fonts;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.modifier.MeleeModifier;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.Environment;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldProvider;

public class GamePlayState implements State {
	/** World name */
	private String worldName = "test";

	public GamePlayState() {
		int worldWidth = 500;
		int worldHeight = 500;

		if (Util.fileExists(WorldProvider.getFilePath(this.worldName))) {
			LastTry.world = WorldProvider.load(this.worldName);
		} else {
			LastTry.world = WorldProvider.generate(this.worldName, (short) worldWidth, (short) worldHeight,
				World.CRIMSON | World.EXPERT);
		}

		int spawnX = LastTry.world.getWidth() / 2;
		int spawnY = 50;

		LastTry.environment = new Environment();
		LastTry.entityManager = new EntityManager();

		LastTry.player = new Player(new PlayerInfo("George", 100, 20, PlayerType.SOFTCORE, PlayerProvider
			.CURRENT_VERSION, new PlayerRenderInfo(1, Color.GREEN, Color.GRAY, Color.CORAL, 1, true)));

		LastTry.player.spawn(spawnX, spawnY);
		LastTry.ui.add(LastTry.player.inventory);

		LastTry.player.inventory.add(new ItemHolder(Item.woodenSword, 1, MeleeModifier.legendary));
		LastTry.player.inventory.add(new ItemHolder(Item.ironPickaxe, 1, MeleeModifier.light));
		LastTry.player.inventory.add(new ItemHolder(Item.crimsandBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.crimstoneBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.redIceBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.viciousMushroom, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.stoneBlock, 999));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonsandBlock, 990));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonsandBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.ebonstoneBlock, 200));
		LastTry.player.inventory.add(new ItemHolder(Item.purpleIceBlock, 100));
		LastTry.player.inventory.add(new ItemHolder(Item.dayBloom, 10));

		LastTry.player.addEffect(Buff.ironskin, 240);
		LastTry.player.addEffect(Buff.regeneration, 240);
		LastTry.player.addEffect(Buff.honey, 30);


		for (int i = 0; i < 10; i++) {
			LastTry.entityManager.spawnEnemy(EnemyID.greenSlime, 40 + LastTry.random.nextInt(100), 60);
			LastTry.entityManager.spawnEnemy(EnemyID.blueSlime, 10 + LastTry.random.nextInt(100), 60);
		}

		LastTry.modLoader = new ModLoader();
		LastTry.modLoader.load();
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
		LastTry.environment.update((int) delta);
		LastTry.entityManager.update((int) delta);
		LastTry.player.update((int) delta);

		if (Gdx.input.isKeyJustPressed(Input.Keys.TAB)) {
			LastTry.debug.toggle();
		}

		LastTry.environment.render();

		LastTry.camera.position.x = Math.max(Gdx.graphics.getWidth() / 2,
			LastTry.player.getCenterX());

		LastTry.camera.position.y = Math.max(Gdx.graphics.getHeight() / 2,
			LastTry.world.getHeight() * Block.TEX_SIZE - LastTry.player.getCenterY());

		LastTry.camera.update();
		LastTry.batch.setProjectionMatrix(LastTry.camera.combined);

		LastTry.world.render();
		LastTry.entityManager.render();
		LastTry.player.render();

		LastTry.batch.setProjectionMatrix(LastTry.uiCamera.combined);

		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		int hp = LastTry.player.getHp();
		int x = Gdx.graphics.getWidth() - 260;

		Fonts.f22.draw(LastTry.batch, String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()), x,
			Gdx.graphics.getHeight() - 4);

		for (int i = 0; i < hp / 20; i++) {
			LastTry.batch.draw(Textures.hp, x + i * 22 + i * 2, Gdx.graphics.getHeight() - 50);
		}

		LastTry.ui.render();
		LastTry.player.renderBuffs();
		LastTry.debug.render();
	}

	/** Updates the view */
	@Override
	public void resize(int width, int height) {
		LastTry.viewport.update(width, height);
		LastTry.camera.update();
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
