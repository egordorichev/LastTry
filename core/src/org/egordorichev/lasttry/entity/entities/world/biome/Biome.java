package org.egordorichev.lasttry.entity.entities.world.biome;

import org.egordorichev.lasttry.entity.Entity;
import org.egordorichev.lasttry.entity.component.IdComponent;

/**
 * Represents a world biome
 */
public class Biome extends Entity {
	public Biome() {
		super(IdComponent.class, BiomeGeneratorDataComponent.class, BiomeSpawnComponent.class, BiomeIndexComponent.class);
	}
}