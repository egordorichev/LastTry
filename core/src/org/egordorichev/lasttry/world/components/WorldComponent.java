package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.component.Component;
import org.egordorichev.lasttry.world.World;

public class WorldComponent implements Component {
	protected World world;

	public WorldComponent(World world) {
		this.world = world;
	}
}