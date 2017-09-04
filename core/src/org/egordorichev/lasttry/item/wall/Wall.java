package org.egordorichev.lasttry.item.wall;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Tile;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.item.wall.helpers.WallHelper;
import org.egordorichev.lasttry.util.ByteHelper;

public class Wall extends Tile {
	/**
	 * Wall tiles (not icons!)
	 */
	protected TextureRegion[][] tiles;
	/**
	 * Required tool power to break this wall
	 */
	protected ToolPower power;

	public Wall(String id) {
		super(id);

		this.useDelayMax = 30;
		this.tiles = this.texture.split(Block.SIZE, Block.SIZE);
		this.texture = Assets.getTexture(this.id.replace(":", "_") + "_icon");
	}

	/**
	 * Loads fields from given wall root
	 * 
	 * @param root
	 *            Wall root
	 */
	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		short power[] = { 10, 0, 0 };

		if (root.has("requiredPower")) {
			power = root.get("requiredPower").asShortArray();
		}

		this.power = new ToolPower(power[0], power[1], power[2]);
	}

	/**
	 * Callback, called on wall death
	 *
	 * @param x
	 *            Wall X
	 * @param y
	 *            Wall Y
	 */
	public void die(int x, int y) {
		Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
		Globals.getWorld().onWallBreak(x, y);
	}

	/**
	 * Creates byte, representing wall neighbors
	 *
	 * @param x
	 *            Wall X
	 * @param y
	 *            Wall Y
	 * @return Byte, representing wall neighbors
	 */

	public byte calculateBinary(int x, int y) {
		boolean t = this.canConnect(Globals.getWorld().walls.getID(x, y + 1));
		boolean r = this.canConnect(Globals.getWorld().walls.getID(x + 1, y));
		boolean b = this.canConnect(Globals.getWorld().walls.getID(x, y - 1));
		boolean l = this.canConnect(Globals.getWorld().walls.getID(x - 1, y));

		return ByteHelper.create(t, r, b, l, false, false, false, false);
	}

	/**
	 * Renders wall at given position
	 *
	 * @param x
	 *            Wall X
	 * @param y
	 *            Wall Y
	 */
	public void renderWall(int x, int y) {
		byte hp = Globals.getWorld().walls.getHP(x, y);
		byte variant = WallHelper.getVariant(hp);
		byte binary = calculateBinary(x, y);

		float light = Globals.getWorld().light.get(x, y);
		Graphics.batch.setColor(light, light, light, 1f);
		Graphics.batch.draw(this.tiles[variant][binary], x * Block.SIZE, y * Block.SIZE);

		hp = WallHelper.getHP(hp);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}

		Graphics.batch.setColor(1f, 1f, 1f, 1f);
	}

	@Override
	public boolean use(short x, short y) {
		if (Globals.getWorld().walls.getID(x, y) == null) {
			this.place(x, y);
			return true;
		}

		return false;
	}

	/**
	 * @return Render wall cracks
	 */
	protected boolean renderCracks() {
		return true;
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}

	/**
	 * Places the wall of self type in given position
	 *
	 * @param x
	 *            Wall X
	 * @param y
	 *            Wall Y
	 */
	public void place(int x, int y) {
		Globals.getWorld().walls.set(this.id, x, y);
	}

	/**
	 * Returns, if wall can be used
	 *
	 * @param x
	 *            Wall X
	 * @param y
	 *            Wall Y
	 * @return If wall can be used
	 */
	@Override
	public boolean canBeUsed(short x, short y) {
		if (!super.canBeUsed(x, y)) {
			return false;
		}

		Wall t = Globals.getWorld().walls.get(x, y + 1);
		Wall b = Globals.getWorld().walls.get(x, y - 1);
		Wall l = Globals.getWorld().walls.get(x + 1, y);
		Wall r = Globals.getWorld().walls.get(x - 1, y);

		if (t == null && b == null && r == null && l == null) {
			Block block = Globals.getWorld().blocks.get(x, y);

			if (block == null) {
				return false;
			}
		}

		return true;
	}

	/**
	 * @return Required tool power to break this wall
	 */
	public ToolPower getRequiredPower() {
		return this.power;
	}
}