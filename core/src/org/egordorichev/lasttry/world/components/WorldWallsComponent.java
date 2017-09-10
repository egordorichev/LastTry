package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.injection.CoreRegistry;
import org.egordorichev.lasttry.injection.InjectionHelper;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemManager;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;

public class WorldWallsComponent extends WorldComponent {
	public WorldWallsComponent(World world) {
		super(world);
	}

	public Wall get(int x, int y) {
		return (Wall) CoreRegistry.get(ItemManager.class).getItem(this.getID(x, y));
	}

	public String getID(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return null;
		}

		return chunk.getWall(x, y);
	}

	public void set(String id, int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return ;
		}

		chunk.setWall(id, x, y);
	}

	public byte getHP(int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return Block.MAX_HP;
		}

		return chunk.getWallHP(x, y);
	}

	public void setHP(byte hp, int x, int y) {
		Chunk chunk = this.getChunk(x, y);

		if (chunk == null) {
			return;
		}


		chunk.setWallHP(hp, x, y);
	}
}