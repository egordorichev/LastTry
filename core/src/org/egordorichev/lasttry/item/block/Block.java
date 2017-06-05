package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.util.Rectangle;

public class Block extends Item {
	public static final int SIZE = 16;
	public static final byte MAX_HP = 3;

	protected boolean solid;
	protected ToolPower power;
	protected TextureRegion[][] tiles;
	protected int width = 1;
	protected int height = 1;

	public Block(String id) {
		super(id);

		this.useDelayMax = 30;
		this.tiles = this.texture.split(SIZE, SIZE);
	}

	@Override
	protected void loadFields(JsonValue root) {
		short[] power = { 10, 0, 0 };

		if (root.has("power")) {
			power = root.asShortArray();
		}

		this.power = new ToolPower(power[0], power[1], power[3]);
		this.solid = root.getBoolean("solid", true);
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}

	public static byte calculateBinary(boolean top, boolean right, boolean bottom, boolean left) {
		byte result = 0;

		if (top) {
			result += 1;
		}

		if (right) {
			result += 2;
		}

		if (bottom) {
			result += 4;
		}

		if (left) {
			result += 8;
		}

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
		Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
		Globals.getWorld().onBlockBreak(x,y);
	}

	public boolean canBePlaced(int x, int y) {
		int dx = (int) Globals.getPlayer().physics.getCenterX() / Block.SIZE - x;
		int dy = (int) Globals.getPlayer().physics.getCenterY() / Block.SIZE - y;

		double length = Math.abs(Math.sqrt(dx * dx + dy * dy));

		if (length > Globals.getPlayer().getItemUseRadius()) {
			return false;
		}

		Block t = Globals.getWorld().blocks.get(x, y + 1);
		Block b = Globals.getWorld().blocks.get(x, y - 1);
		Block l = Globals.getWorld().blocks.get(x + 1, y);
		Block r = Globals.getWorld().blocks.get(x - 1, y);

		if ((t == null || !t.isSolid()) && (b == null || !b.isSolid()) &&
				(r == null || !r.isSolid()) && (l == null || !l.isSolid())) {

			Wall wall = Globals.getWorld().walls.get(x, y);

			if (wall == null) {
				return false;
			}
		}

		return true;
	}

	public void place(int x, int y) {
		Globals.getWorld().blocks.set(this.id, x, y);
	}

	public byte calculateBinary(int x, int y) {
		boolean t = Globals.getWorld().blocks.getID(x, y + 1).equals(this.id);
		boolean r = Globals.getWorld().blocks.getID(x + 1, y).equals(this.id);
		boolean b = Globals.getWorld().blocks.getID(x, y - 1).equals(this.id);
		boolean l = Globals.getWorld().blocks.getID(x - 1, y).equals(this.id);
		return Block.calculateBinary(t, r, b, l);
	}

	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().blocks.getHP(x, y);
		int variant = ByteHelper.getBitValue(hp, (byte) 2) + ByteHelper.getBitValue(hp, (byte) 3) * 2;

		Graphics.batch.draw(this.tiles[variant][binary], x * SIZE, y * SIZE);

		hp = (byte) (ByteHelper.getBitValue(hp, (byte) 0) + ByteHelper.getBitValue(hp, (byte) 1) * 2);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
	}

	protected boolean renderCracks() {
		return true;
	}

	@Override
	public boolean use() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		if (this.canBePlaced(x, y) && Globals.getWorld().blocks.getID(x, y) == "") {
			Rectangle rectangle = Globals.getPlayer().physics.getHitbox();

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