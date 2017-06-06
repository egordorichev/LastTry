package org.egordorichev.lasttry.item.wall;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.util.ByteHelper;

public class Wall extends Item {
	protected TextureRegion[][] tiles;
	protected ToolPower power;

	public Wall(String id) {
		super(id);

		this.useDelayMax = 30;
		this.tiles = this.texture.split(Block.SIZE, Block.SIZE);
		this.texture = Assets.getTexture(this.id + "_icon");
	}

	@Override
	protected void loadFields(JsonValue root) {
		super.loadFields(root);

		short power[] = { 10, 0, 0 };

		if (root.has("power")) {
			power = root.get("power").asShortArray();
		}

		this.power = new ToolPower(power[0], power[1], power[2]);
	}

	public void die(int x, int y) {
		Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
		Globals.getWorld().onWallBreak(x, y);
	}

	public void renderWall(int x, int y) {
		boolean t = Globals.getWorld().walls.getID(x, y + 1).equals(this.id);
		boolean r = Globals.getWorld().walls.getID(x + 1, y).equals(this.id);
		boolean b = Globals.getWorld().walls.getID(x, y - 1).equals(this.id);
		boolean l = Globals.getWorld().walls.getID(x - 1, y).equals(this.id);

		byte hp = Globals.getWorld().walls.getHP(x, y);
		int variant = ByteHelper.getBitValue(hp, (byte) 2) + ByteHelper.getBitValue(hp, (byte) 3) * 2;
		int binary = Block.calculateBinary(t, r, b, l);

		Graphics.batch.draw(this.tiles[variant][binary], x * Block.SIZE, y * Block.SIZE);

		hp = (byte) (ByteHelper.getBitValue(hp, (byte) 0) + ByteHelper.getBitValue(hp, (byte) 1) * 2);

		if (this.renderCracks() && hp < Block.MAX_HP) {
			Graphics.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		}
	}

	@Override
	public boolean use() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		if (this.canBePlaced(x, y) && Globals.getWorld().walls.getID(x, y).isEmpty()) {
			this.place(x, y);

			return true;
		}

		return false;
	}

	protected boolean renderCracks() {
		return true;
	}

	@Override
	public boolean isAutoUse() {
		return true;
	}

	public void place(int x, int y) {
		Globals.getWorld().walls.set(this.id, x, y);
	}

	public boolean canBePlaced(int x, int y) {
		int dx = (int) Globals.getPlayer().physics.getCenterX() / Block.SIZE - x;
		int dy = (int) Globals.getPlayer().physics.getCenterY() / Block.SIZE - y;

		double length = Math.abs(Math.sqrt(dx * dx + dy * dy));

		if (length > Globals.getPlayer().getItemUseRadius()) {
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

	public ToolPower getRequiredPower() {
		return this.power;
	}
}