package org.egordorichev.lasttry.world.biome.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeComponent extends Component {
    protected Biome biome;

    public BiomeComponent(Biome biome) {this.biome = biome;}

    public void fadeIn() {
        this.biome.biomeAnimation.fadeIn();
    }

    public void fadeOut() {
        this.biome.biomeAnimation.fadeOut();
    }

    public boolean fadeInIsDone() { return this.biome.biomeAnimation.fadeInIsDone(); }

    public boolean fadeOutIsDone() {
        return this.biome.biomeAnimation.fadeOutIsDone();
    }

    public String getName() {
        return this.biome.getName();
    }

    public void renderBackground() { this.biome.biomeAnimation.renderBackground();}
}
