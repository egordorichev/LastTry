package org.egordorichev.lasttry.item.wall;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.Items;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.Rectangle;

public class Wall extends org.egordorichev.lasttry.item.Item {
	protected Texture tiles;

	public Wall(short id, String name, Texture texture, Texture tiles) {
		super(id, name, texture);
		this.tiles = tiles;
	}

	public static Wall getForBlockID(int id) {
		switch (id) {
			case ItemID.none: default: return null;
			case ItemID.dirtBlock: return (Wall) Items.dirtWall;
		}
	}

	public void die(int x, int y) {
		Globals.entityManager.spawn(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
	}

	/**
	 * Renders the wall at the given coordinates.
	 * @param x X-position in the world.
	 * @param y Y-position in the world.
	 */
	public void renderWall(int x, int y) {
		boolean t = Globals.world.walls.getID(x, y + 1) == this.id;
		boolean r = Globals.world.walls.getID(x + 1, y) == this.id;
		boolean b = Globals.world.walls.getID(x, y - 1) == this.id;
		boolean l = Globals.world.walls.getID(x - 1, y) == this.id;

		// TODO: FIXME: replace with variable
		int variant = 1;
		int binary = Block.calculateBinary(t, r, b, l);

		Graphics.batch.draw(this.tiles, x * Block.SIZE,
			y * Block.SIZE, Block.SIZE, Block.SIZE,
			Block.SIZE * (binary), variant * Block.SIZE, Block.SIZE,
			Block.SIZE, false, false);
	}

	@Override
	public boolean use() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		System.out.println("use");

		if (this.canBePlaced(x, y) && Globals.world.walls.getID(x, y) == ItemID.none) {
			this.place(x, y);

			return true;
		}

		return false;
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}

	public void place(int x, int y) {
		Globals.world.walls.set(this.id, x, y);
	}

	public boolean canBePlaced(int x, int y) {
		int dx = (int) Globals.player.physics.getCenterX() / Block.SIZE - x;
		int dy = (int) Globals.player.physics.getCenterY() / Block.SIZE - y;

		double length = Math.abs(Math.sqrt(dx * dx + dy * dy));

		if (length > Globals.player.getItemUseRadius()) {
			return false;
		}

		Wall t = Globals.world.walls.get(x, y + 1);
		Wall b = Globals.world.walls.get(x, y - 1);
		Wall l = Globals.world.walls.get(x + 1, y);
		Wall r = Globals.world.walls.get(x - 1, y);

		if (t == null && b == null && r == null && l == null) {
			Block block = Globals.world.blocks.get(x, y);

			if (block == null) {
				return false;
			}
		}

		return true;
	}
}