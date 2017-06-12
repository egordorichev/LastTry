package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.language.Language;
import org.egordorichev.lasttry.ui.chat.UiChat;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.world.chunk.gc.ChunkGcManager;

public class GamePlayState implements State {
	private static boolean paused = false;
	private final TextureRegion hpTextureRegion;

	public GamePlayState() {
		this.hpTextureRegion = Assets.getTexture(Textures.hp);

		Globals.entityManager = new EntityManager();
		Globals.entityManager.spawn(Globals.getPlayer(), (int) (Globals.getPlayer().getSpawnPoint().x * Block.SIZE), (int) Globals.getPlayer().getSpawnPoint().y * Block.SIZE);
		Globals.chunkGcManager = new ChunkGcManager();
		Globals.chat = new UiChat();

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
				(Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV : 0));

		if (!paused) {
			// TODO: This is a shitty fix. Delta is usually 0.013f per tick (on average)
			// casting it to int always gives 0, which breaks a lot of shit.
			int dt = (int) (delta * 100f);
			Globals.environment.update(dt);
			Globals.entityManager.update(dt);
			Globals.getWorld().updateLight(dt);

			if (InputManager.isKeyJustDown(Keys.OPEN_CHAT)) {
				Globals.chat.toggle();
			}
		}

		if (InputManager.isKeyJustDown(Keys.DEBUG_MODE)) {
			LastTry.debug.toggle();
		}

		Globals.environment.render();

		Camera.game.position.x = Math.max(Gdx.graphics.getWidth() / 2,
				Globals.getPlayer().physics.getCenterX());

		Camera.game.position.y = Math.max(0, Globals.getPlayer().physics.getCenterY());

		Camera.game.update();
		Graphics.batch.setProjectionMatrix(Camera.game.combined);

		Globals.getWorld().render();
		Globals.entityManager.render();
		Globals.getWorld().renderLights();

		Graphics.batch.setProjectionMatrix(Camera.ui.combined);

		int mouseX = (int) InputManager.getMousePosition().x;
		int mouseY = (int) InputManager.getMousePosition().y;

		int hp = Globals.getPlayer().stats.getHP();
		int x = Gdx.graphics.getWidth() - 200;

		Assets.f22.draw(Graphics.batch, String.format(Language.text.get("hp") + ": %d/%d", hp, Globals.getPlayer().stats.getMaxHP()), x,
				Gdx.graphics.getHeight() - 4);

		for (int i = 0; i < hp / 20; i++) {
			Graphics.batch.draw(this.hpTextureRegion, x + i * 22 + i * 2, Gdx.graphics.getHeight() - 50);
		}

		LastTry.ui.render();
		Globals.getPlayer().effects.render();
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