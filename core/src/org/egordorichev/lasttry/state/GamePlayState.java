package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Buff;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.graphics.Fonts;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.input.InputManager;
import org.egordorichev.lasttry.input.Keys;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.modifier.MeleeModifier;
import org.egordorichev.lasttry.mod.ModLoader;

public class GamePlayState implements State {
    public GamePlayState() {
        int spawnX = LastTry.world.getWidth() / 2;
        int spawnY = 50;

        LastTry.player.spawn(spawnX, spawnY);
        LastTry.player.inventory.add(new ItemHolder(Items.woodenSword, 1, MeleeModifier.legendary));
        LastTry.player.inventory.add(new ItemHolder(Items.ironPickaxe, 1, MeleeModifier.light));
        LastTry.player.inventory.add(new ItemHolder(Items.crimstoneBlock, 100));
        LastTry.player.inventory.add(new ItemHolder(Items.stoneBlock, 999));
        LastTry.player.inventory.add(new ItemHolder(Items.ebonstoneBlock, 200));
        LastTry.player.inventory.add(new ItemHolder(Items.dayBloom, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.dayBloomSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.blinkRoot, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.blinkRootSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.dirtBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.mudBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.jungleGrassBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.moonGlowSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.jungleGrassSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.deathWeed, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.deathWeedSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.silverThorn, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.silverThornSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.fireBlossom, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.fireBlossomSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.waterLeaf, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.waterLeafSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.ashBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.snowBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Items.sandBlock, 10));

        LastTry.player.addEffect(Buff.ironskin, 240);
        LastTry.player.addEffect(Buff.regeneration, 240);
        LastTry.player.addEffect(Buff.honey, 30);

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
        LastTry.environment.update((int) delta);
        LastTry.entityManager.update((int) delta);
        LastTry.player.update((int) delta);

        if (InputManager.isKeyJustDown(Keys.DEBUG_MODE)) {
            LastTry.debug.toggle();
        }

        LastTry.environment.render();

        LastTry.camera.position.x = Math.max(Gdx.graphics.getWidth() / 2,
                LastTry.player.getCenter().x);

        LastTry.camera.position.y = Math.max(Gdx.graphics.getHeight() / 2,
                LastTry.world.getHeight() * Block.TEX_SIZE - LastTry.player.getCenter().y);

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

        Fonts.f22.draw(LastTry.batch, String.format("Life: %d/%d", hp, LastTry.player.getMaxHp()), x,
                Gdx.graphics.getHeight() - 4);

        for (int i = 0; i < hp / 20; i++) {
            LastTry.batch.draw(Textures.hp, x + i * 22 + i * 2, Gdx.graphics.getHeight() - 50);
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
