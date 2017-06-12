package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.Args;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.core.Bootstrap;
import org.egordorichev.lasttry.player.PlayerIO;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.WorldIO;
import org.egordorichev.lasttry.world.environment.Environment;
import org.egordorichev.lasttry.world.spawn.SpawnSystem;

public class LoadState implements State {
	private boolean loaded = false;
	private String loadString = "Loading...";

    public LoadState() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
	                    Bootstrap.load();
                        loadString = "Loading spawn system...";
                        Globals.spawnSystem = new SpawnSystem();
                        loadString = "Loading environment...";
                        Globals.environment = new Environment();

	                    loadString = "Loading world...";

	                    if (WorldIO.saveExists(Args.world)) {
		                    WorldIO.load(Args.world);
	                    } else {
		                    int seed = (int) System.currentTimeMillis();
		                    Globals.setWorld(WorldIO.generate(Args.world, World.Size.SMALL, 0, seed));
	                    }

	                    loadString = "Loading player...";

	                    if (PlayerIO.saveExists(Args.player)) {
		                    PlayerIO.load(Args.player);
	                    } else {
		                    Globals.setPlayer(PlayerIO.generate(Args.player));
	                    }

	                    loaded = true;

	                    Globals.getWorld().initLights();
					}
				});
			}
		}).start();
	}

	@Override
	public void show() {

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

	@Override
	public void resize(int width, int height) {

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