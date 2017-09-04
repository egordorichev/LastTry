package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;

public class LightTest extends Block {
	public LightTest(String id) {
		super(id);
	}

	@Override
	public void renderBlock(int x, int y, byte binary) {
		// This enture class is temporary, this forces the torch texture.
		byte hp = Globals.getWorld().blocks.getHP(x, y);

		float light = Globals.getWorld().light.get(x, y);
		Graphics.batch.setColor(light, light, light, 1f);
		Graphics.batch.draw(this.tiles[2][0], x * SIZE, y * SIZE);

		hp = BlockHelper.plain.getHP(hp);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
		Graphics.batch.setColor(1f, 1f, 1f, 1f);
	}
	
	@Override
	public int getBrightness() {
		return 16;
	}
}