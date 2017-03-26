package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;

public class BlockGround extends Block {
	public BlockGround(short id, String name, Texture texture, Texture tiles) {
		super(id, name, true, EffectiveToolType.PICKAXE, texture, tiles);
	}

	/**
	 * Overridden so blocks of the same type merge.
	 */
	@Override
	public void renderBlock(int x, int y) {
		boolean t = EffectiveToolType.lookup(LastTry.world.getBlockID(x, y - 1)) == this.type;
		boolean r = EffectiveToolType.lookup(LastTry.world.getBlockID(x + 1, y)) == this.type;
		boolean b = EffectiveToolType.lookup(LastTry.world.getBlockID(x, y + 1)) == this.type;
		boolean l = EffectiveToolType.lookup(LastTry.world.getBlockID(x - 1, y)) == this.type;
		// TODO: FIXME: replace  with var
		short variant =  1; 
		byte binary = Block.calculateBinary(t, r, b, l);

			if (binary == 15) {
			/*
			boolean tr = EffectiveToolType.lookup(LastTry.world.getBlockID(x + 1, y - 1)) == this.type;
			boolean br = EffectiveToolType.lookup(LastTry.world.getBlockID(x + 1, y + 1)) == this.type;
			boolean bl = EffectiveToolType.lookup(LastTry.world.getBlockID(x - 1, y + 1)) == this.type;
			boolean tl = EffectiveToolType.lookup(LastTry.world.getBlockID(x - 1, y - 1)) == this.type;
			int corner = Block.calculateBinary(tr, br, bl, tl);
			*/
				// TODO: Replace (binary) with (corner)
				// It's not getting the right texture for some reason.

				LastTry.batch.draw(this.tiles, x * Block.TEX_SIZE,
						(LastTry.world.getHeight() - y) * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE,
						Block.TEX_SIZE * (binary), 48 + variant * Block.TEX_SIZE, Block.TEX_SIZE,
						Block.TEX_SIZE, false, false);
			} else {
				LastTry.batch.draw(this.tiles, x * Block.TEX_SIZE,
						(LastTry.world.getHeight() - y) * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE,
						Block.TEX_SIZE * (binary), variant * Block.TEX_SIZE, Block.TEX_SIZE,
						Block.TEX_SIZE, false, false);
			}
	}
}