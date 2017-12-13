package org.egordorichev.lasttry.entity.entities.world.generator.generators;

import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.helper.TileHelper;
import org.egordorichev.lasttry.entity.entities.world.World;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.entity.entities.world.generator.WorldGenerator;

public class ForestGenerator extends WorldGenerator {
	public ForestGenerator(String name) {
		super(name);
	}

	/**
	 * Generates a forest world
	 *
	 * @return New world
	 */
	@Override
	public World generate() {
		this.world = new World(this.name, "forest");
		SizeComponent size = this.world.getComponent(SizeComponent.class);

		int width = (int) (size.width * Chunk.SIZE);
		int height = (int) (size.height * Chunk.SIZE);

		int groundLevel = height / 4 * 3;

		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				if (y < groundLevel + Math.cos(x / 4) * 4) {
					this.world.setBlock("lt:dirt", (short) x, (short) y);
					this.world.setWall("lt:dirt_wall", (short) x, (short) y);
					this.world.setData(TileHelper.create(), (short) x, (short) y);
				}
			}
		}

		return this.world;
	}
}