package org.egordorichev.lasttry.state;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.player.PlayerProvider;
import org.egordorichev.lasttry.graphics.Fonts;
import org.egordorichev.lasttry.graphics.Textures;
import org.egordorichev.lasttry.item.ItemProvider;
import org.egordorichev.lasttry.world.WorldProvider;
import org.egordorichev.lasttry.world.environment.Environment;

public class LoadState implements State {
    private boolean loaded = false;
    private String loadString = "";

    public LoadState() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Gdx.app.postRunnable(new Runnable() {
                    @Override
                    public void run() {
                    	ItemProvider.load();
                        loadString = "Loading environment...";
                        LastTry.environment = new Environment();
                        loadString = "Loading world...";
                        LastTry.world = WorldProvider.load();
                        loadString = "Loading player...";
                        LastTry.player = PlayerProvider.load();

                        loaded = true;
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

        for (int i = 0; i < Gdx.graphics.getWidth() / 48 + 1; i++) {
            LastTry.batch.draw(Textures.sky, i * 48, 0);
        }

        Fonts.f22.draw(LastTry.batch, this.loadString, 0, 0);
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