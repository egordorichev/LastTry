package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Buff;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.entity.Player;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.modifier.MeleeModifier;
import org.egordorichev.lasttry.mod.ModLoader;
import org.egordorichev.lasttry.world.Environment;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldProvider;

public class GamePlayState implements State {
	/** World name */
	private String worldName = "test";

	public GamePlayState() {
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
		LastTry.camera.position.x = Math.min((LastTry.world.getWidth() - 1) * Block.TEX_SIZE - Gdx.graphics.getWidth(),
			Math.max(Block.TEX_SIZE, LastTry.player.getX() + LastTry.player.getWidth() / 2 - Gdx.graphics.getWidth() / 2));

		LastTry.camera.position.y = Math.min((LastTry.world.getHeight() - 1) * Block.TEX_SIZE - Gdx.graphics.getHeight(),
			Math.max(Block.TEX_SIZE, LastTry.player.getY() + LastTry.player.getHeight() / 2 - Gdx.graphics.getHeight() / 2));

		LastTry.world.render();
		LastTry.entityManager.render();
		LastTry.player.render();

		LastTry.camera.position.x = 0;
		LastTry.camera.position.y = 0;

		int mouseX = Gdx.input.getX();
		int mouseY = Gdx.input.getY();

		LastTry.batch.draw(Textures.radial, mouseX - mouseX % Block.TEX_SIZE - 48 - LastTry.camera.position.x % Block.TEX_SIZE,
				mouseY - mouseY % Block.TEX_SIZE - 48 - LastTry.camera.position.y % Block.TEX_SIZE); // TODO: fix its position

		int hp = LastTry.player.getHp();
		int x = Gdx.graphics.getWidth() - 260;

		Assets.font.draw(LastTry.batch, String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()), x, 4);

		for (int i = 0; i < hp / 20; i++) {
			LastTry.batch.draw(Textures.hp, x + i * 22 + i * 2, 28);
		}

		LastTry.ui.render();
		LastTry.player.renderBuffs();
		LastTry.debug.render();
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