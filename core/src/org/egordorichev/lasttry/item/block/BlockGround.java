package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.util.ByteHelper;

public class BlockGround extends Block {
	public BlockGround(short id, String name, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles) {
		super(id, name, true, requiredPower, texture, tiles);
	}

	@Override
	public byte calculateBinary(int x, int y) {
		boolean t = Globals.getWorld().getBlock(x, y + 1) instanceof BlockGround;
		boolean r = Globals.getWorld().getBlock(x + 1, y) instanceof BlockGround;
		boolean b = Globals.getWorld().getBlock(x, y - 1) instanceof BlockGround;
		boolean l = Globals.getWorld().getBlock(x - 1, y) instanceof BlockGround;

		return calculateBinary(t, r, b, l);
	}

	@Override
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().getBlockHP(x, y);
		int variant = ByteHelper.getBitValue(hp, (byte) 2) + ByteHelper.getBitValue(hp, (byte) 3) * 2;

		Graphics.batch.draw(this.tiles[variant][binary], x * SIZE, y * SIZE);
		hp = (byte) (ByteHelper.getBitValue(hp, (byte) 0) + ByteHelper.getBitValue(hp, (byte) 1) * 2);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
	}
}