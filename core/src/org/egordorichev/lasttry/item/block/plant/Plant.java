package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;

public class Plant extends Block {
	public Plant(short id, String name, Texture texture, Texture tiles) {
		super(id, name, true, EffectiveToolType.PICKAXE, texture, tiles);
	}

	@Override
	public void renderBlock(int x, int y) {
		int hp = LastTry.world.getBlockHp(x, y);

		int tx = 0;

		if (hp == 2) {
			tx = 16;
		} else if (hp == 3) {
			tx = 32;
		}

		LastTry.batch.draw(this.tiles, x * Block.TEX_SIZE, y * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE,
			tx * Block.TEX_SIZE, 0, Block.TEX_SIZE, Block.TEX_SIZE);
	}
}