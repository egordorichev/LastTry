package org.egordorichev.lasttry.world.biome.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeComponent extends Component {
    protected Biome biome;

    public BiomeComponent(Biome biome) {
    	this.biome = biome;
    }

    public void fadeIn() {
        this.biome.animation.fadeIn();
    }

    public void fadeOut() {
        this.biome.animation.fadeOut();
    }

    public boolean fadeInIsDone() {
    	return this.biome.animation.fadeInIsDone();
    }

    public boolean fadeOutIsDone() {
        return this.biome.animation.fadeOutIsDone();
    }

    public String getName() {
        return this.biome.getName();
    }

    public void renderBackground() {
    	this.biome.animation.renderBackground();
    }

	public Biome get() {
		return this.biome;
	}
}
