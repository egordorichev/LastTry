package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.effect.Buff;
import org.egordorichev.lasttry.entity.EnemyID;
import org.egordorichev.lasttry.entity.EntityManager;
import org.egordorichev.lasttry.graphics.Fonts;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.modifier.MeleeModifier;
import org.egordorichev.lasttry.mod.ModLoader;

public class GamePlayState implements State {
    public GamePlayState() {
        int spawnX = LastTry.world.getWidth() / 2;
        int spawnY = 50;

        LastTry.player.spawn(spawnX, spawnY);
        LastTry.player.inventory.add(new ItemHolder(Item.woodenSword, 1, MeleeModifier.legendary));
        LastTry.player.inventory.add(new ItemHolder(Item.ironPickaxe, 1, MeleeModifier.light));
        LastTry.player.inventory.add(new ItemHolder(Item.crimstoneBlock, 100));
        LastTry.player.inventory.add(new ItemHolder(Item.stoneBlock, 999));
        LastTry.player.inventory.add(new ItemHolder(Item.ebonstoneBlock, 200));
        LastTry.player.inventory.add(new ItemHolder(Item.dayBloom, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.dayBloomSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.blinkRoot, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.blinkRootSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.dirtBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.mudBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.jungleGrassBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.moonGlowSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.jungleGrassSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.deathWeed, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.deathWeedSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.silverThorn, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.silverThornSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.fireBlossom, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.fireBlossomSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.waterLeaf, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.waterLeafSeeds, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.ashBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.snowBlock, 10));
        LastTry.player.inventory.add(new ItemHolder(Item.sandBlock, 10));

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
