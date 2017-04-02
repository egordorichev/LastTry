package org.egordorichev.lasttry.item.block.plant;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.BlockGround;

public class Grass extends BlockGround {
	public Grass(short id, String name, Texture texture, Texture tiles) {
		super(id, name, texture, tiles);
	}

	@Override
	public void updateBlock(int x, int y) {
		int nx = x - 1 + LastTry.random.nextInt(2);
		int ny = y - 1 + LastTry.random.nextInt(2);

		if (nx == x && ny == y) {
			return;
		}

		Block block = (Block) Item.fromID(LastTry.world.getBlockID(nx, ny));

		if (block != null && this.canBeGrownAt(block.getId())) {
			LastTry.world.setBlock(this.id, nx, ny);
		}
	}

	public boolean canBeGrownAt(short id) {
		return false;
	}
}