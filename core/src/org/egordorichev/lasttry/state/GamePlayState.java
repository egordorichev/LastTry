package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Buffs;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.language.Language;
import org.egordorichev.lasttry.ui.chat.UiChat;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.world.chunk.gc.ChunkGcManager;

public class GamePlayState implements State {
	private final TextureRegion hpTextureRegion;
	private static boolean paused = false;

	public GamePlayState() {
		this.hpTextureRegion = Assets.getTexture(Textures.hp);

		int spawnX = Globals.world.getWidth() / 2 * Block.SIZE;
		int spawnY = (Globals.world.getHeight() - 10) * Block.SIZE;

		Globals.entityManager = new EntityManager();
		Globals.entityManager.spawn(Globals.player, spawnX, spawnY);
		Globals.chunkGcManager = new ChunkGcManager();
		Globals.chat = new UiChat();

		// Globals.player.effects.applyEffect(Buffs.honey, 100);

		LastTry.ui.add(Globals.chat);
	}

	public static void play() {
		paused = false;
	}

	public static void stop() {
		paused = true;
	}

	@Override
	public void show() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
			(Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

		if (!paused) {
			Globals.environment.update((int) delta);
			Globals.entityManager.update((int) delta);

			if (InputManager.isKeyJustDown(Keys.DEBUG_MODE)) {
				LastTry.debug.toggle();
			}

			if (InputManager.isKeyJustDown(Keys.OPEN_CHAT)) {
				Globals.chat.open();
			}
		}

		Globals.environment.render();

		Camera.game.position.x = Math.max(Gdx.graphics.getWidth() / 2,
			Globals.player.physics.getCenterX());

		Camera.game.position.y = Math.max(0, Globals.player.physics.getCenterY());

		Camera.game.update();
		Graphics.batch.setProjectionMatrix(Camera.game.combined);

		Globals.world.render();
		Globals.entityManager.render();

		Graphics.batch.setProjectionMatrix(Camera.ui.combined);

		int mouseX = (int) InputManager.getMousePosition().x;
		int mouseY = (int) InputManager.getMousePosition().y;

		int hp = Globals.player.stats.getHp();
		int x = Gdx.graphics.getWidth() - 200;

		Assets.f22.draw(Graphics.batch, String.format(Language.text.get("hp") + ": %d/%d", hp, Globals.player.stats.getMaxHP()), x,
				Gdx.graphics.getHeight() - 4);

		for (int i = 0; i < hp / 20; i++) {
			Graphics.batch.draw(this.hpTextureRegion, x + i * 22 + i * 2, Gdx.graphics.getHeight() - 50);
		}

		LastTry.ui.render();
		Globals.player.effects.render();
		LastTry.debug.render();
	}

	@Override
	public void resize(int width, int height) {
		Camera.resize(width, height);
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}
}