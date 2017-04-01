package org.egordorichev.lasttry.item.block.plant;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.EffectiveToolType;

public class Plant extends Block {
	public static final byte GROW_THRESHOLD = 20;

	public Plant(short id, String name, Texture texture, Texture tiles) {
		super(id, name, true, EffectiveToolType.PICKAXE, texture, tiles);
	}

	@Override
	public void renderBlock(int x, int y) {
		int hp = LastTry.world.getBlockHp(x, y);

		int tx = 0;

		if (hp >= GROW_THRESHOLD + 1) {
			tx = 32;
		} else if (hp == GROW_THRESHOLD) {
			tx = 16;
		}

		LastTry.batch.draw(this.tiles, x * Block.TEX_SIZE, y * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE,
			tx * Block.TEX_SIZE, 0, Block.TEX_SIZE, Block.TEX_SIZE);
	}
}