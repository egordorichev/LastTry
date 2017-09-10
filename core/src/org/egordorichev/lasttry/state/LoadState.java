package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.crafting.RecipeManager;
import org.egordorichev.lasttry.crafting.RecipeManagerImpl;
import org.egordorichev.lasttry.effect.EffectManager;
import org.egordorichev.lasttry.effect.EffectManagerImpl;
import org.egordorichev.lasttry.entity.CreatureManager;
import org.egordorichev.lasttry.entity.CreatureManagerImpl;
import org.egordorichev.lasttry.entity.ai.AIManager;
import org.egordorichev.lasttry.entity.ai.AIManagerImpl;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.injection.Context;
import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.item.ItemManager;
import org.egordorichev.lasttry.item.ItemManagerImpl;
import org.egordorichev.lasttry.item.liquids.LiquidManager;
import org.egordorichev.lasttry.item.liquids.LiquidManagerImpl;
import org.egordorichev.lasttry.player.PlayerIO;
import org.egordorichev.lasttry.util.Files;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldIO;
import org.egordorichev.lasttry.world.biome.BiomeManager;
import org.egordorichev.lasttry.world.biome.BiomeManagerImpl;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.world.spawn.SpawnSystem;

import java.io.File;
import java.security.SecureRandom;

public class LoadState implements State {
	/**
	 * All systems are ready
	 */
	private boolean loaded = false;
	/**
	 * Displayed label
	 */
	private String loadString = "Loading...";

    public LoadState() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {

                    	String world = LastTry.defaultWorldName, player = LastTry.defaultPlayerName;


						//TODO: figure out loading scheme
						try {
							File file = new File(Files.getRootDir());

							if (!file.exists() || !file.isDirectory()) {
								file.createNewFile();
							}
						} catch (Exception exception) {
							throw new RuntimeException("Couldn't open save directory. Aborting.");
						}
						Context context = new Context();
						CoreRegistry.setContext(context);
						context.bindInstance(ItemManager.class, new ItemManagerImpl()).load();
						context.bindInstance(RecipeManager.class, new RecipeManagerImpl()).load();
						context.bindInstance(BiomeManager.class,new BiomeManagerImpl()).load();
						context.bindInstance(EffectManager.class,new EffectManagerImpl()).load();
						context.bindInstance(AIManager.class,new AIManagerImpl()).load();
						context.bindInstance(CreatureManager.class, new CreatureManagerImpl()).load();
						context.bindInstance(LiquidManager.class, new LiquidManagerImpl()).load();


                        loadString = "Loading spawn system...";
                        Globals.spawnSystem = new SpawnSystem();
                        loadString = "Loading environment...";
                        Globals.environment = new Environment();

	                    loadString = "Loading world...";

	                    if (WorldIO.saveExists(world)) {
		                    WorldIO.load(world);
	                    } else {
		                    int seed = new SecureRandom().nextInt();
		                    Globals.setWorld(WorldIO.generate(world, World.Size.SMALL, 0, seed));
	                    }

	                    loadString = "Loading player...";

	                    if (PlayerIO.saveExists(player)) {
		                    PlayerIO.load(player);
	                    } else {
		                    Globals.setPlayer(PlayerIO.generate(player));
	                    }

	                    loaded = true;
					}
				});
			}
		}).start();
	}

	@Override
	public void render(float delta) {
		if (this.loaded) {
			LastTry.instance.setScreen(new GamePlayState());
			return;
		}

		int height = Gdx.graphics.getHeight();

		for (int i = 0; i < Gdx.graphics.getWidth(); i++) {
			Graphics.batch.draw(Graphics.skyTexture, i, 0, 1, height, 1000, 0, 1, 1024, false, false);
		}

		Assets.f22.draw(Graphics.batch, this.loadString, 100, height / 2 - 11);
		LastTry.ui.render();
	}
}