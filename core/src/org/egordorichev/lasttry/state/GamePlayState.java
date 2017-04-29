package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Camera;

public class GamePlayState implements State {
	private final Texture hpTexture;

    public GamePlayState() {
    	this.hpTexture = Assets.getTexture(Textures.hp);

	    int spawnX = LastTry.world.getWidth() / 2 * Block.SIZE;
        int spawnY = LastTry.world.getHeight() / Block.SIZE - 100;

        LastTry.player.spawn(spawnX, spawnY);
        LastTry.player.inventory.add(new ItemHolder(Items.wood, 100));
        LastTry.player.inventory.add(new ItemHolder(Items.workBench, 10));
        LastTry.entityManager = new EntityManager();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
	    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT |
			 (Gdx.graphics.getBufferFormat().coverageSampling ? GL20.GL_COVERAGE_BUFFER_BIT_NV:0));

        LastTry.environment.update((int) delta);
        LastTry.entityManager.update((int) delta);
        LastTry.player.update((int) delta);

        if (InputManager.isKeyJustDown(Keys.DEBUG_MODE)) {
            LastTry.debug.toggle();
        }

        LastTry.environment.render();

	    Camera.game.position.x = Math.max(Gdx.graphics.getWidth() / 2,
            LastTry.player.physics.getCenterX());

	    Camera.game.position.y = Math.max(0, LastTry.player.physics.getCenterY());

	    Camera.game.update();
        LastTry.batch.setProjectionMatrix(Camera.game.combined);

        LastTry.world.render();
        LastTry.entityManager.render();
        LastTry.player.render();

        LastTry.batch.setProjectionMatrix(Camera.ui.combined);

        int mouseX = (int) InputManager.getMousePosition().x;
        int mouseY = (int) InputManager.getMousePosition().y;

        int hp = LastTry.player.stats.getHp();
        int x = Gdx.graphics.getWidth() - 260;

	    Assets.f22.draw(LastTry.batch, String.format("Life: %d/%d", hp, LastTry.player.stats.getMaxHP()), x,
                Gdx.graphics.getHeight() - 4);

        for (int i = 0; i < hp / 20; i++) {
            LastTry.batch.draw(this.hpTexture, x + i * 22 + i * 2, Gdx.graphics.getHeight() - 50);
        }

        LastTry.ui.render();
        // LastTry.player.renderBuffs(); TODO
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
