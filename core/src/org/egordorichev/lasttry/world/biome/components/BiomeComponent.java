package org.egordorichev.lasttry.world.biome.components;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeComponent extends Component {
    protected Biome biome;

    public BiomeComponent(Biome biome) {this.biome = biome;}

    /**
     * Animates background
     */
    public void fadeIn() {
        this.biome.fadeIn();
    }

    /**
     * Animates background
     */
    public void fadeOut() {
        this.biome.fadeOut();
    }

    /**
     * Shows, if animation is done
     *
     * @return animation is done
     */
    public boolean fadeInIsDone() { return this.biome.fadeInIsDone(); }

    /**
     * Shows, if animation is done
     *
     * @return animation is done
     */
    public boolean fadeOutIsDone() {
        return this.biome.fadeOutIsDone();
    }

    /**
     * Returns biome name
     *
     * @return biome name
     */
    public String getName() {
        return this.biome.getName();
    }

    /**
     * Renders backgrounds
     */
    public void renderBackground() { this.biome.renderBackground();}
}
