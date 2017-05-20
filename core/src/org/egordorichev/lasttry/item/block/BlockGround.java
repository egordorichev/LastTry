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
		boolean t = Globals.world.blocks.get(x, y + 1) instanceof BlockGround;
		boolean r = Globals.world.blocks.get(x + 1, y) instanceof BlockGround;
		boolean b = Globals.world.blocks.get(x, y - 1) instanceof BlockGround;
		boolean l = Globals.world.blocks.get(x - 1, y) instanceof BlockGround;

		return calculateBinary(t, r, b, l);
	}

	@Override
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.world.blocks.getHP(x, y);
		int variant = ByteHelper.getBitValue(hp, (byte) 3) + ByteHelper.getBitValue(hp, (byte) 4) * 2;

		System.out.println(variant);

		Graphics.batch.draw(this.tiles[variant][binary], x * SIZE, y * SIZE);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
	}
}