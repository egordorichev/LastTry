package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.mod.ModLoader;

public class GamePlayState implements State {
	private final Texture hpTexture;

    public GamePlayState() {
    	this.hpTexture = Assets.getTexture(Textures.hp);

        int spawnX = LastTry.world.getWidth() / 2 * Block.TEX_SIZE;
        int spawnY = 50 * Block.TEX_SIZE;

        LastTry.player.spawn(spawnX, spawnY);
        LastTry.player.inventory.add(new ItemHolder(Items.wood, 1000));
        LastTry.player.inventory.add(new ItemHolder(Items.workBench, 10));
        LastTry.entityManager = new EntityManager();

        for (int i = 0; i < 2; i++) {
            LastTry.entityManager.spawnEnemy(EnemyID.greenSlime, spawnX, spawnY);
            LastTry.entityManager.spawnEnemy(EnemyID.blueSlime, spawnX, spawnY);
        }

	    LastTry.modLoader = new ModLoader();
        LastTry.modLoader.load();
    }

    /**
     * Never used
     */
    @Override
    public void show() {

    }

    /**
     * Renders and updates the splash
     *
     * @param delta delta from last update
     */
    @Override
    public void render(float delta) {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
			 (Gdx.graphics.getBufferFormat().coverageSampling? GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        LastTry.environment.update((int) delta);
        LastTry.entityManager.update((int) delta);
        LastTry.player.update((int) delta);

        if (InputManager.isKeyJustDown(Keys.DEBUG_MODE)) {
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

        int mouseX = (int) InputManager.getMousePosition().x;
        int mouseY = (int) InputManager.getMousePosition().y;

        int hp = LastTry.player.getHp();
        int x = Gdx.graphics.getWidth() - 260;

	    Assets.f22.draw(LastTry.batch, String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()), x,
                Gdx.graphics.getHeight() - 4);

        for (int i = 0; i < hp / 20; i++) {
            LastTry.batch.draw(this.hpTexture, x + i * 22 + i * 2, Gdx.graphics.getHeight() - 50);
        }

        LastTry.ui.render();
        LastTry.player.renderBuffs();
        LastTry.debug.render();
    }

    /**
     * Updates the view
     */
    @Override
    public void resize(int width, int height) {
        LastTry.viewport.update(width, height);
        LastTry.camera.update();
    }

    /**
     * Never used
     */
    @Override
    public void pause() {

    }

    /**
     * Never used
     */
    @Override
    public void resume() {

    }

    /**
     * Never used
     */
    @Override
    public void hide() {

    }

    /**
     * Never used
     */
    @Override
    public void dispose() {

    }
}
