package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.item.block.helpers.PlainBlockHelper;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.util.Rectangle;

public class Block extends Item {
	public static final int SIZE = 16;
	public static final byte MAX_HP = 3;

	/**
	 * Block is collidable
	 */
	protected boolean solid;
	/**
	 * Tool requiredPower required to break this block
	 */
	protected ToolPower requiredPower;
	/**
	 * Block textures (not the icon)
	 */
	protected TextureRegion[][] tiles;
	/**
	 * Block width in tiles
	 */
	protected int width = 1;
	/**
	 * Block height in tiles
	 */
	protected int height = 1;

	public Block(String id) {
		this(id, true);
	}

	public Block(String id, boolean loadIcon) {
		super(id);

		this.useDelayMax = 30;
		this.tiles = this.texture.split(SIZE, SIZE);

		if (loadIcon) {
			this.texture = Assets.getTexture(this.id + "_icon");
		}
	}

	/**
	 * Loads block info from root
	 * @param root Block root node
	 */
	@Override
	protected void loadFields(JsonValue root) {
		short[] power = { 10, 0, 0 };

		if (root.has("requiredPower")) {
			power = root.asShortArray();
		}

		this.requiredPower = new ToolPower(power[0], power[1], power[2]);
		this.solid = root.getBoolean("solid", true);
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}

	/**
	 * Updates block animation
	 *
	 * @param x Block X
	 * @param y Block Y
	 */
	public void updateBlockStyle(int x, int y) {
		/* TODO: if block has animation, update it */
	}

	/**
	 * Updates block (one in World.UPDATE_TIME seconds)
	 *
	 * @param x Block X
	 * @param y Block Y
	 */
	public void updateBlock(int x, int y) {

	}

	/**
	 * Callback, called on neighbor change
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @param nx Neighbor X
	 * @param ny Neighbor Y
	 */
	public void onNeighborChange(int x, int y, int nx, int ny) {

	}

	/**
	 * Callback, called on block destroy
	 *
	 * @param x Block X
	 * @param y Block Y
	 */
	public void die(int x, int y) {
		Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
		Globals.getWorld().onBlockBreak(x, y);
	}

	/**
	 * Returns, if this block can be placed at given position
	 * @param x Block X
	 * @param y Block Y
	 * @return If this block can be placed at given position
	 */
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

	/**
	 * Places block of self type at given position
	 *
	 * @param x New block X
	 * @param y New block Y
	 */
	public void place(int x, int y) {
		Globals.getWorld().blocks.set(this.id, x, y);
	}

	/**
	 * Creates byte, representing block neighbors
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @return Byte, representing block neighbors
	 */
	public byte calculateBinary(int x, int y) {
		boolean t = Globals.getWorld().blocks.getID(x, y + 1).equals(this.id);
		boolean r = Globals.getWorld().blocks.getID(x + 1, y).equals(this.id);
		boolean b = Globals.getWorld().blocks.getID(x, y - 1).equals(this.id);
		boolean l = Globals.getWorld().blocks.getID(x - 1, y).equals(this.id);

		return ByteHelper.create(t, r, b, l, false, false, false , false);
	}

	/**
	 * Renders this block at given position
	 *
	 * @param x Block X
	 * @param y Block Y
	 * @param binary Byte, representing block neighbors
	 */
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().blocks.getHP(x, y);
		byte variant = PlainBlockHelper.getVariant(hp);

		Graphics.batch.draw(this.tiles[variant][binary], x * SIZE, y * SIZE);

		hp = BlockHelper.plain.getHP(hp);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
	}

	/**
	 * @return Render tile cracks
	 */
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

	/**
	 * @return Required tool power to break this block
	 */
	public ToolPower getRequiredPower() {
		return this.requiredPower;
	}

	/**
	 * @return Block is solid
	 */
	public boolean isSolid() {
		return this.solid;
	}
}