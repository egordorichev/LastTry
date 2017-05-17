package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.Rectangle;

public class Block extends Item {
	public static final int SIZE = 16;
	public static final byte MAX_HP = 4;

	protected boolean solid;
	protected ToolPower power;
	protected TextureRegion tiles;
	protected int width = 1;
	protected int height = 1;

	public Block(short id, String name, boolean solid, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles) {
		super(id, name, texture);
		this.power = requiredPower;
		this.tiles = tiles;
		this.solid = solid;
		this.useSpeed = 30;
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}

	public static byte calculateBinary(boolean top, boolean right, boolean bottom, boolean left) {
		byte result = 0;

		if (top)
			result += 1;
		if (right)
			result += 2;
		if (bottom)
			result += 4;
		if (left)
			result += 8;

		return result;
	}

	public void updateBlockStyle(int x, int y) {
		/* TODO: if block has animation, update it */
	}

	public void updateBlock(int x, int y) {

	}

	public void onNeighborChange(int x, int y, int nx, int ny) {

	}

	public void die(int x, int y) {
		Globals.entityManager.spawn(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
	}

	public boolean canBePlaced(int x, int y) {
		int dx = (int) Globals.player.physics.getCenterX() / Block.SIZE - x;
		int dy = (int) Globals.player.physics.getCenterY() / Block.SIZE - y;

		double length = Math.abs(Math.sqrt(dx * dx + dy * dy));

		if (length > Globals.player.getItemUseRadius()) {
			return false;
		}

		Block t = Globals.world.blocks.get(x, y + 1);
		Block b = Globals.world.blocks.get(x, y - 1);
		Block l = Globals.world.blocks.get(x + 1, y);
		Block r = Globals.world.blocks.get(x - 1, y);

		if ((t == null || !t.isSolid()) && (b == null || !b.isSolid()) &&
				(r == null || !r.isSolid()) && (l == null || !l.isSolid())) {

			Wall wall = Globals.world.walls.get(x, y);

			if (wall == null) {
				return false;
			}
		}

		return true;
	}

	public void place(int x, int y) {
		Globals.world.blocks.set(this.id, x, y);
	}

	public byte calculateBinary(int x, int y) {
		boolean t = Globals.world.blocks.getID(x, y + 1) == this.id;
		boolean r = Globals.world.blocks.getID(x + 1, y) == this.id;
		boolean b = Globals.world.blocks.getID(x, y - 1) == this.id;
		boolean l = Globals.world.blocks.getID(x - 1, y) == this.id;
		return Block.calculateBinary(t, r, b, l);
	}

	public void renderBlock(int x, int y, byte binary) {
		short variant = 1; // TODO: FIXME: replace with var

		Graphics.batch.draw(this.tiles, x * Block.SIZE,
			y * Block.SIZE, Block.SIZE, Block.SIZE,
			Block.SIZE * (binary), variant * Block.SIZE, Block.SIZE,
			Block.SIZE, false, false);

		if (this.renderCracks()) {
			byte hp = Globals.world.blocks.getHP(x, y);

			if (hp < Block.MAX_HP) {
				Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
			}
		}
	}

	protected boolean renderCracks() {
		return true;
	}

	@Override
	public boolean use() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		if (this.canBePlaced(x, y) && Globals.world.blocks.getID(x, y) == ItemID.none) {
			Rectangle rectangle = Globals.player.physics.getHitbox();

			if (rectangle.intersects(new Rectangle(x * SIZE, y * SIZE, this.width * SIZE,
					this.height * SIZE))) {

				return false;
			}

			this.place(x, y);

			return true;
		}

		return false;
	}

	public ToolPower getRequiredPower() {
		return this.power;
	}

	public boolean isSolid() {
		return this.solid;
	}
}