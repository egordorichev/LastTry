package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.items.ToolPower;

public class BlockGround extends Block {
	public BlockGround(short id, String name, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles) {
		super(id, name, true, requiredPower, texture, tiles);
	}

	@Override
	public byte calculateBinary(int x, int y) {
		boolean t = Globals.world.blocks.get(x, y + 1) instanceof BlockGround;
		boolean r = Globals.world.blocks.get(x + 1, y) instanceof BlockGround;
		boolean b = Globals.world.blocks.get(x, y - 1) instanceof BlockGround;
		boolean l = Globals.world.blocks.get(x - 1, y) instanceof BlockGround;

		return calculateBinary(t, r, b, l);
	}

	/**
	 * Overridden so blocks of the same type merge.
	 */
	@Override
	public void renderBlock(int x, int y, byte binary) {
		short variant = 1; // TODO: FIXME: replace  with var

		Graphics.batch.draw(this.tiles[binary][variant], x * SIZE, y * SIZE);

		if (this.renderCracks()) {
			byte hp = Globals.world.blocks.getHP(x, y);

			if (hp < Block.MAX_HP) {
				Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
			}
		}
	}
}