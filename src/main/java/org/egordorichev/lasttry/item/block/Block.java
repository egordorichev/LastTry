package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.util.Rectangle;
import org.newdawn.slick.Image;

public class Block extends Item {
	public static final int TEX_SIZE = 16;
	protected boolean solid;
	protected EffectiveToolType type;
	protected Image tiles;

	public Block(short id, String name, boolean solid, EffectiveToolType type, Image texture, Image tiles) {
		super(id, name, texture);
		this.type = type;
		this.tiles = tiles;
		this.solid = solid;
	}

	public void renderBlock(int x, int y) {
		boolean t = LastTry.world.getBlockID(x, y - 1) == this.id;
		boolean r = LastTry.world.getBlockID(x + 1, y) == this.id;
		boolean b = LastTry.world.getBlockID(x, y + 1) == this.id;
		boolean l = LastTry.world.getBlockID(x - 1, y) == this.id;

		short variant = 1; // TODO: FIXME: replace with var
		byte binary = this.calculateBinary(t, r, b, l);

		if (binary == 15) {
			this.tiles.getSubImage(15 * Block.TEX_SIZE, 48 + variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE)
					.draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		} else {
			this.tiles.getSubImage(binary * Block.TEX_SIZE, variant * Block.TEX_SIZE, Block.TEX_SIZE, Block.TEX_SIZE)
					.draw(x * Block.TEX_SIZE, y * Block.TEX_SIZE);
		}
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

	@Override
	public boolean use() {
		int x = LastTry.getMouseXInWorld() / Block.TEX_SIZE;
		int y = LastTry.getMouseYInWorld() / Block.TEX_SIZE;

		if (LastTry.world.getBlockID(x, y) == ItemID.none) {
			Rectangle rectangle = LastTry.player.getHitbox();

			if (!rectangle.intersects(new Rectangle(x * TEX_SIZE, y * TEX_SIZE, TEX_SIZE, TEX_SIZE))) {
				LastTry.world.setBlock(this.id, x, y);

				return true;
			}
		}

		return false;
	}

	public EffectiveToolType getEffectiveToolType() {
		return this.type;
	}

	public boolean isSolid() {
		return this.solid;
	}

	@Override
	public int getMaxInStack() {
		return 999;
	}
}
