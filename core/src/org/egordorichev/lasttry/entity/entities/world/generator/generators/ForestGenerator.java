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
		
		SizeComponent size = World.instance.getComponent(SizeComponent.class);
		int width = (int) size.width;
		int height = (int) size.height;
		
		int groundLevel = height * Chunk.SIZE / 4 * 3;
		
		for(int chunkX = 0; chunkX < width; chunkX++){
			for(int chunkY = 0; chunkY < height; chunkY++){
				Chunk chunk = World.instance.getChunk(chunkX, chunkY);
				for(int x = 0; x < Chunk.SIZE; x++){
					int gy = groundLevel + (int)(Math.cos((chunkX * Chunk.SIZE + x) / 4.0) * 4) - chunkY * Chunk.SIZE;
					for(int y = 0; y < Chunk.SIZE; y++){
						if (y < gy) {
							chunk.setBlock("lt:dirt", x, y);
							chunk.setWall("lt:dirt_wall", x, y);
						} else if (y == gy) {
							chunk.setBlock("lt:grass", x, y);
							chunk.setWall("lt:grass_wall", x, y);
						}

						this.world.setData(TileHelper.main.create(), x, y);
					}
				}
			}
		}

		return this.world;
	}
}