package org.egordorichev.lasttry.entity.entities.world;

import com.badlogic.gdx.Gdx;
import org.egordorichev.lasttry.entity.asset.Assets;
import org.egordorichev.lasttry.entity.component.IdComponent;
import org.egordorichev.lasttry.entity.component.SizeComponent;
import org.egordorichev.lasttry.entity.engine.system.systems.CameraSystem;
import org.egordorichev.lasttry.entity.entities.camera.CameraComponent;
import org.egordorichev.lasttry.entity.entities.item.tile.Block;
import org.egordorichev.lasttry.entity.entities.item.tile.Wall;
import org.egordorichev.lasttry.entity.entities.world.chunk.Chunk;
import org.egordorichev.lasttry.util.log.Log;

/**
 * Handles chunk rendering
 */
public class World extends ChunkManager {
	/**
	 * Static instance
	 */
	public static World instance;

	public World(String name, String type) {
		instance = this;

		this.addComponent(SizeComponent.class);

		SizeComponent size = this.getComponent(SizeComponent.class);

		size.width = 4;
		size.height = 4;

		this.addComponent(WorldChunksComponent.class);

		IdComponent id = (IdComponent) this.addComponent(IdComponent.class);
		id.id = name + ":" + type;
	}

	/**
	 * Renders the world
	 */
	@Override
	public void render() {
		short xStart = (short) Math.floor((CameraSystem.instance.get("main").getComponent(CameraComponent.class).camera.position.x - Gdx.graphics.getWidth() / 2) / Block.SIZE);
		short yStart = (short) Math.floor((CameraSystem.instance.get("main").getComponent(CameraComponent.class).camera.position.y - Gdx.graphics.getHeight() / 2) / Block.SIZE);
		short width = (short) (Math.floor(Gdx.graphics.getWidth() / Block.SIZE) + 2);
		short height = (short) (Math.floor(Gdx.graphics.getHeight() / Block.SIZE) + 2);

		for (short x = xStart; x < xStart + width; x++) {
			for (short y = yStart; y < yStart + height; y++) {
				String blockId = this.getBlock(x, y);
				Block block = null;
				int neighbors = 15;
				float light = ((float) this.getLight(x, y) + 128) / 255;

				if (blockId != null) {
					block = (Block) Assets.items.get(blockId);
					neighbors = block.getNeighbors(x, y);
				}

				if (blockId == null || neighbors != 15) {
					String wallId = this.getWall(x, y);

					if (wallId != null) {
						Wall wall = (Wall) Assets.items.get(wallId);
						wall.render(x, y, light);
					}
				}

				if (block != null) {
					block.render(x, y, neighbors, light);
				}
			}
		}
	}

	/**
	 * Returns highest block Y at given X
	 *
	 * @param x Block X
	 * @return Returns highest block Y at given X
	 */
	public short getHighest(short x) {
		SizeComponent size = this.getComponent(SizeComponent.class);

		if (x >= size.width * Chunk.SIZE || x < 0) {
			return 0;
		}

		for (short y = (short) (size.height * Chunk.SIZE); y >= 0; y--) {
			if (this.getBlock(x, y) != null) {
				return y;
			}
		}

		return 0;
	}
}