package org.egordorichev.lasttry.item.block.plant;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.util.Util;

public class Plant extends Block {
    public static final byte GROW_THRESHOLD = 20;

    public Plant(short id, String name, TextureRegion texture, TextureRegion tiles) {
        super(id, name, false, ToolPower.pickaxe(10), texture, tiles);
    }

	@Override
	public void onNeighborChange(int x, int y, int nx, int ny) {
		if (ny == y + 1 && x == nx) {
			this.die(x, y);
		}
	}

	@Override
	public void die(int x, int y) {
		if (hasGrown(x, y)) {
			Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, Util.random(1, 3))), x * Block.SIZE - Block.SIZE / 2,
				y * Block.SIZE - Block.SIZE / 2);
		}

		if (isBlooming(x, y)) {
			short seeds = getSeedsFor(this.id);

			if (seeds != 0) {
				Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(Item.fromID(seeds), Util.random(1, 3))),
					x * Block.SIZE - Block.SIZE / 2, y * Block.SIZE - Block.SIZE / 2);
			}
		}

		Globals.world.blocks.set(ItemID.none, x, y);
	}

	public static boolean isBlooming(int x, int y) {
    	return getGrowLevel(x, y) > GROW_THRESHOLD;
	}


	public static boolean hasGrown(int x, int y) {
		return getGrowLevel(x, y) >= GROW_THRESHOLD;
	}

	protected static byte getGrowLevel(int x, int y) {
		byte hp = Globals.world.blocks.getHP(x, y);
		return (byte) (ByteHelper.getBitValue(hp, (byte) 2) + ByteHelper.getBitValue(hp, (byte) 3) * 2);
	}

	protected static void setGrowLevel(byte level, int x, int y) {
		byte hp = Globals.world.blocks.getHP(x, y);

		hp = ByteHelper.setBit(hp, (byte) 2, ByteHelper.bitIsSet(level, (byte) 0));
		hp = ByteHelper.setBit(hp, (byte) 3, ByteHelper.bitIsSet(level, (byte) 1));

		Globals.world.blocks.setHP(hp, x, y);
    }

	public static short getSeedsFor(short id) {
    	switch (id) {
		    case ItemID.dayBloom: return ItemID.dayBloomSeeds;
		    case ItemID.blinkRoot: return ItemID.blinkRootSeeds;
		    case ItemID.moonGlow: return ItemID.moonGlowSeeds;
		    case ItemID.deathWeed: return ItemID.deathWeedSeeds;
		    case ItemID.fireBlossom: return ItemID.fireBlossom;
		    case ItemID.waterLeaf: return ItemID.waterLeafSeeds;
		    case ItemID.silverThorn: return ItemID.silverThornSeeds;
	    }

	    return 0;
	}

	@Override
    public void renderBlock(int x, int y, byte binary) {
        int hp = getGrowLevel(x, y);

        int tx = 0;

        if (hp >= GROW_THRESHOLD + 1) {
            tx = 1;
        } else if (hp == GROW_THRESHOLD) {
            tx = 2;
        }

        Graphics.batch.draw(this.tiles[tx][0], x * Block.SIZE, y * Block.SIZE);
	}

    @Override
    protected boolean renderCracks() {
	    return false;
    }

    public boolean canBeGrownAt(int x, int y) {
        short id = Globals.world.blocks.getID(x, y);

        if (id != ItemID.none) {
            return false;
        }

        return true;
    }
}