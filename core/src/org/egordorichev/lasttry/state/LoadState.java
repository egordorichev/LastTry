package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Engine;
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
import org.egordorichev.lasttry.injection.ContextImpl;
import org.egordorichev.lasttry.item.ItemManager;
import org.egordorichev.lasttry.item.ItemManagerImpl;
import org.egordorichev.lasttry.item.liquids.LiquidManager;
import org.egordorichev.lasttry.item.liquids.LiquidManagerImpl;
import org.egordorichev.lasttry.player.PlayerIO;
import org.egordorichev.lasttry.ui.UiManager;
import org.egordorichev.lasttry.util.Files;
import org.egordorichev.lasttry.world.WorldIO;
import org.egordorichev.lasttry.world.biome.BiomeManager;
import org.egordorichev.lasttry.world.biome.BiomeManagerImpl;

import java.io.File;
import java.security.SecureRandom;

public class LoadState implements GameState{
	/**
	 * All systems are ready
	 */
	private boolean loaded = false;
	/**
	 * Displayed label
	 */
	private String loadString = "Loading...";

	private  Context context;

	@Override
	public void load(Context rootContext) {
		this.context = new ContextImpl(rootContext);
		new Thread(new Runnable() {
			@Override
			public void run() {
				Gdx.app.postRunnable(new Runnable() {
					@Override
					public void run() {

						String world = LastTry.defaultWorldName, player = LastTry.defaultPlayerName;

						context.bindInstance(ItemManager.class, new ItemManagerImpl()).load();
						context.bindInstance(RecipeManager.class, new RecipeManagerImpl()).load();
						context.bindInstance(BiomeManager.class,new BiomeManagerImpl()).load();
						context.bindInstance(EffectManager.class,new EffectManagerImpl()).load();
						context.bindInstance(AIManager.class,new AIManagerImpl()).load();
						context.bindInstance(CreatureManager.class, new CreatureManagerImpl()).load();
						context.bindInstance(LiquidManager.class, new LiquidManagerImpl()).load();


						//TODO: figure out loading scheme
						try {
							File file = new File(Files.getRootDir());

							if (!file.exists() || !file.isDirectory()) {
								file.createNewFile();
							}
						} catch (Exception exception) {
							throw new RuntimeException("Couldn't open save directory. Aborting.");
						}

						loadString = "Loading spawn system...";
						//Globals.spawnSystem = new SpawnSystem();
						loadString = "Loading environment...";
						//Globals.environment = new Environment();

						loadString = "Loading world...";

						if (WorldIO.saveExists(world)) {
							WorldIO.load(world);
						} else {
							int seed = new SecureRandom().nextInt();
						//	Globals.setWorld(WorldIO.generate(world, World.Size.SMALL, 0, seed));
						}

						loadString = "Loading player...";

						if (PlayerIO.saveExists(player)) {
							PlayerIO.load(player);
						} else {
						//	Globals.setPlayer(PlayerIO.generate(player));
						}

						loaded = true;
					}
				});
			}
		}).start();
	}



	@Override
	public void update() {
		if (this.loaded) {
			Engine engine = context.get(Engine.class);
			engine.setContext(context);
			engine.setGameState(new GamePlayState());
			return;
		}
	}

	@Override
	public void render(float delta) {


		int height = Gdx.graphics.getHeight();

		for (int i = 0; i < Gdx.graphics.getWidth(); i++) {
			Graphics.batch.draw(Graphics.skyTexture, i, 0, 1, height, 1000, 0, 1, 1024, false, false);
		}

		Assets.f22.draw(Graphics.batch, this.loadString, 100, height / 2 - 11);
		context.get(UiManager.class).render();
	}
}