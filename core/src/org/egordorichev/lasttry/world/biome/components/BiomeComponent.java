package org.egordorichev.lasttry.world.biome.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeComponent extends Component {
    protected Biome biome;

    public BiomeComponent(Biome biome) {this.biome = biome;}

    public void fadeIn() {
        this.biome.fadeIn();
    }

    public void fadeOut() {
        this.biome.fadeOut();
    }

    public boolean fadeInIsDone() { return this.biome.fadeInIsDone(); }

    public boolean fadeOutIsDone() {
        return this.biome.fadeOutIsDone();
    }

    public String getName() {
        return this.biome.getName();
    }

    public void renderBackground() { this.biome.renderBackground();}
}
