package org.egordorichev.lasttry.world.biome.components;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeAnimationComponent extends BiomeComponent {

    private Texture backgroundTexture;

    public BiomeAnimationComponent(Biome biome, Texture backgroundTexture) {
        super(biome);
        this.backgroundTexture = backgroundTexture;
    }

    public float alpha = 0;

    public void fadeIn() { this.alpha = (Math.min(1, this.alpha + 0.01f));}

    public void fadeOut() { this.alpha = (Math.min(1, this.alpha - 0.01f));}

    public boolean fadeInIsDone() { return this.alpha >= 0.99f; }

    public boolean fadeOutIsDone() { return this.alpha < 0.01f; }

    public void renderBackground() {
        LastTry.batch.setColor(1, 1, 1, this.alpha);
        LastTry.batch.draw(this.backgroundTexture, 0, 0);
        LastTry.batch.setColor(1, 1, 1, 1);
    }


}
