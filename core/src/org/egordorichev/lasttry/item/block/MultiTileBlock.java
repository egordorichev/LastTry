package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.item.items.ToolPower;

public class MultiTileBlock extends Block {
	public MultiTileBlock(short id, String name, boolean solid, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles, int gridWidth, int gridHeight) {
		super(id, name, solid, requiredPower, texture, tiles);

		if (width > 16 || height > 8) {
			throw new RuntimeException("To big MTB");
		}

		this.width = gridWidth;
		this.height = gridHeight;
	}

	@Override
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().getBlockHP(x, y);
		Graphics.batch.draw(this.tiles[BlockHelper.mtb.getY(hp)][BlockHelper.mtb.getX(hp)], x * Block.SIZE, y * Block.SIZE);
	}

	public int getGridWidth() {
		return this.width;
	}

	public int getGridHeight() {
		return this.height;
	}

	@Override
	public boolean canBePlaced(int x, int y) {
		int dx = (int) Globals.player.physics.getCenterX() / Block.SIZE - x;
		int dy = (int) Globals.player.physics.getCenterY() / Block.SIZE - y;

		double length = Math.abs(Math.sqrt(dx * dx + dy * dy));

		if (length > Globals.player.getItemUseRadius()) {
			return false;
		}

		for (int j = y; j < y + this.height; j++) {
			for (int i = x; i < x + this.width; i++) {
				if (Globals.getWorld().getBlock(i, j) != null) {
					return false;
				}
			}
		}

		// todo: no placing in air

		return false;
	}

	@Override
	public void place(int x, int y) {
		for (int j = y; j < y + this.height; j++) {
			for (int i = x; i < x + this.width; i++) {
				Globals.getWorld().setBlock(this.id, i, j);

				byte hp = 0;

				hp = BlockHelper.mtb.setX(hp, (byte) (i - x));
				hp = BlockHelper.mtb.setY(hp, (byte) (j - y));

				Globals.getWorld().setBlockHP(hp, i, j);
			}
		}
	}
}