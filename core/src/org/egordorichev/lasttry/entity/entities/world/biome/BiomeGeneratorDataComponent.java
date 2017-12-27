package org.egordorichev.lasttry.entity.entities.world.biome;

import org.egordorichev.lasttry.entity.component.Component;

/**
 * Stores data about biome, needed for the component
 */
public class BiomeGeneratorDataComponent extends Component {
	/**
	 * The soil type
	 */
	public String soil;
	/**
	 * The soil wall
	 */
	public String soil_wall;
	/**
	 * The grass
	 */
	public String grass;
	/**
	 * The grass wall
	 */
	public String grass_wall;
}