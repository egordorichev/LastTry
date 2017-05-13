package org.egordorichev.lasttry.world.biome.components;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeAnimationComponent extends Component {
	private Biome biome;
    private Texture backgroundTexture;
	private float alpha = 0;

    public BiomeAnimationComponent(Biome biome, Texture backgroundTexture) {
        this.biome = biome;
        this.backgroundTexture = backgroundTexture;
    }

    public void fadeIn() {
    	this.alpha = (Math.min(1, this.alpha + 0.01f));
    }

    public void fadeInFast() {
    	this.alpha = 1f;
    }

    public void fadeOut() {
    	this.alpha = (Math.min(1, this.alpha - 0.01f));
    }

    public boolean fadeInIsDone() {
    	return this.alpha >= 0.99f;
    }

    public boolean fadeOutIsDone() {
    	return this.alpha < 0.01f;
    }

    public void renderBackground() {
        Graphics.batch.setColor(1, 1, 1, this.alpha);
        Graphics.batch.draw(this.backgroundTexture, 0, 0);
        Graphics.batch.setColor(1, 1, 1, 1);
    }
}