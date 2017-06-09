package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;

public class MultiTileBlock extends Block {
	public MultiTileBlock(String id, boolean loadIcon) {
		super(id, loadIcon);
	}

	@Override
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().blocks.getHP(x, y);
		Graphics.batch.draw(this.tiles[BlockHelper.mtb.getY(hp)][BlockHelper.mtb.getX(hp)], x * Block.SIZE, y * Block.SIZE);
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		if (root.has("size")) {
			JsonValue size = root.get("size");

			this.width = size.get(0).asByte();
			this.height = size.get(1).asByte();
		} else {
			this.width = 2;
			this.height = 2;
		}
	}

	public int getGridWidth() {
		return this.width;
	}

	public int getGridHeight() {
		return this.height;
	}

	@Override
	public boolean canBePlaced(int x, int y) {
		int dx = (int) Globals.getPlayer().physics.getCenterX() / Block.SIZE - x;
		int dy = (int) Globals.getPlayer().physics.getCenterY() / Block.SIZE - y;

		double length = Math.abs(Math.sqrt(dx * dx + dy * dy));

		if (length > Globals.getPlayer().getItemUseRadius()) {
			return false;
		}

		for (int j = y; j < y + this.height; j++) {
			for (int i = x; i < x + this.width; i++) {
				if (Globals.getWorld().blocks.get(i, j) != null) {
					return false;
				}
			}
		}

		// todo: no placing in air

		return true;
	}

	@Override
	public void place(int x, int y) {
		for (int j = y; j < y + this.height; j++) {
			for (int i = x; i < x + this.width; i++) {
				Globals.getWorld().blocks.set(this.id, i, j);

				byte hp = 0;

				hp = BlockHelper.mtb.setX(hp, (byte) (i - x));
				hp = BlockHelper.mtb.setY(hp, (byte) (this.height - (j - y) - 1));

				Globals.getWorld().blocks.setHP(hp, i, j);
			}
		}
	}

	@Override
	public void die(int x, int y) {
		byte data = Globals.getWorld().blocks.getHP(x, y);
		byte tx = BlockHelper.mtb.getX(data);
		byte ty = BlockHelper.mtb.getY(data);
		short sx = (short) (x);
		short sy = (short) (y);

		for (int j = sy - 1; j < sy + this.height - 1; j++) {
			for (int i = sx + 1; i < sx + this.width + 1; i++) {
				Globals.getWorld().blocks.set("lt:sand", i, j);
			}
		}

		Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
	}
}