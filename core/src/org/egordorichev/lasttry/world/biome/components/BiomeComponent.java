package org.egordorichev.lasttry.world.biome.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.biome.Biome;

public class BiomeComponent extends Component {
    protected Biome biome;

    public BiomeComponent(Biome biome) {this.biome = biome;}
}
