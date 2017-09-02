package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Tile;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.ByteHelper;

public class Block extends Tile {
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
			this.texture = Assets.getTexture(this.id.replace(':', '_') + "_icon");
		}
	}

	/**
	 * Loads block info from root
	 *
	 * @param root
	 *            Block root node
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
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 */
	public void updateBlockStyle(int x, int y) {
		/* TODO: if block has animation, update it */
	}

	/**
	 * Updates block (one in World.UPDATE_TIME seconds)
	 *
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 */
	public void updateBlock(int x, int y) {

	}

	/**
	 * Callback, called on neighbor change
	 *
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 * @param nx
	 *            Neighbor X
	 * @param ny
	 *            Neighbor Y
	 */
	public void onNeighborChange(short x, short y, short nx, short ny) {

	}

	/**
	 * Callback, called on block destroy
	 *
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 */
	public void die(short x, short y) {
		Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
		Globals.getWorld().onBlockBreak(x, y);
	}

	/**
	 * Returns, if this block can be placed at given position
	 *
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 * @return If this block can be placed at given position
	 */
	public boolean canBeUsed(short x, short y) {
		if (!super.canBeUsed(x, y)) {
			return false;
		}

		if (Globals.getWorld().blocks.getID(x, y) != null) {
			return false;
		}

		Block t = Globals.getWorld().blocks.get(x, y + 1);
		Block b = Globals.getWorld().blocks.get(x, y - 1);
		Block l = Globals.getWorld().blocks.get(x + 1, y);
		Block r = Globals.getWorld().blocks.get(x - 1, y);

		if ((t == null || !t.isSolid()) && (b == null || !b.isSolid()) && (r == null || !r.isSolid())
				&& (l == null || !l.isSolid())) {

			Wall wall = Globals.getWorld().walls.get(x, y);

			if (wall == null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates byte, representing block neighbors
	 *
	 * @param x
	 *            Block X
	 * @param y
	 *            Block Y
	 * @return Byte, representing block neighbors
	 */
	public byte calculateBinary(int x, int y) {
		boolean t = canConnect(Globals.getWorld().blocks.getID(x, y + 1));
		boolean r = canConnect(Globals.getWorld().blocks.getID(x + 1, y));
		boolean b = canConnect(Globals.getWorld().blocks.getID(x, y - 1));
		boolean l = canConnect(Globals.getWorld().blocks.getID(x - 1, y));

		return ByteHelper.create(t, r, b, l, false, false, false, false);
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
	public void renderBlock(int x, int y, byte binary) {
		byte hp = Globals.getWorld().blocks.getHP(x, y);
		byte variant = BlockHelper.plain.getVariant(hp);

		float light = Globals.getWorld().light.get(x, y);
		Graphics.batch.setColor(light, light, light, 1f);
		Graphics.batch.draw(this.tiles[variant][binary], x * SIZE, y * SIZE);

		hp = BlockHelper.plain.getHP(hp);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
		Graphics.batch.setColor(1f, 1f, 1f, 1f);
	}

	/**
	 * @return Render tile cracks
	 */
	protected boolean renderCracks() {
		return true;
	}

	@Override
	public boolean use(short x, short y) {
		Globals.getWorld().blocks.set(this.id, x, y);
		Globals.getWorld().onBlockPlace(x, y);
		return true;
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

	/**
	 * @return How much light the block emits.
	 */
	public int getBrightness() {
		return 0;
	}
}