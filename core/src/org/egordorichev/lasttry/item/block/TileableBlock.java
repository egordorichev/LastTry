package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.world.components.WorldLightingComponent;

public class TileableBlock extends Block {
	public TileableBlock(String id) {
		super(id);
	}

	public TileableBlock(String id, boolean loadIcon) {
		super(id, loadIcon);
	}

	/**
	 * Renders this block at given position
	 *
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 * @param binary
	 *            Byte, representing block neighbors
	 */
	@Override
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().blocks.getHP(x, y);
		byte variant = BlockHelper.plain.getVariant(hp);

		float light  = 1f;
		// Update light leven
		if (!LastTry.noLight){
			light = (0f + Globals.getWorld().blocks.getLight(x, y)) / ( WorldLightingComponent.MAX_LIGHT );
		}
		Graphics.batch.setColor(light, light, light, 1f);
		Graphics.batch.draw(this.tiles[variant][binary], x * SIZE, y * SIZE);

		hp = BlockHelper.plain.getHP(hp);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
		Graphics.batch.setColor(1f,1f,1f,1f);
	}
}
