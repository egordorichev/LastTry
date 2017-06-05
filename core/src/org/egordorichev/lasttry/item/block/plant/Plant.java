package org.egordorichev.lasttry.item.block.plant;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.inventory.ItemHolder;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.helpers.BlockHelper;
import org.egordorichev.lasttry.item.block.helpers.PlantHelper;
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
		if (ny == y - 1 && x == nx) {
			this.die(x, y);
		}
	}

	@Override
	public void die(int x, int y) {
    	byte hp = Globals.getWorld().getBlockHP(x, y);

		if (BlockHelper.plant.hasGrown(hp)) {
			Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(this, Util.random(1, 3))), x * Block.SIZE - Block.SIZE / 2,
				y * Block.SIZE - Block.SIZE / 2);
		}

		if (BlockHelper.plant.isBlooming(hp)) {
			short seeds = getSeedsFor(this.id);

			if (seeds != 0) {
				Globals.entityManager.spawnBlockDrop(new DroppedItem(new ItemHolder(Item.fromID(seeds), Util.random(1, 3))),
					x * Block.SIZE - Block.SIZE / 2, y * Block.SIZE - Block.SIZE / 2);
			}
		}

		Globals.getWorld().setBlock(ItemID.none, x, y);
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
        byte hp = Globals.getWorld().getBlockHP(x, y);

		int tx = 0;

        if (BlockHelper.plant.isBlooming(hp)) {
            tx = 1;
        } else if (BlockHelper.plant.hasGrown(hp)) {
            tx = 2;
        }

        Graphics.batch.draw(this.tiles[0][tx], x * Block.SIZE, y * Block.SIZE);
	}

    @Override
    protected boolean renderCracks() {
	    return false;
    }

	@Override
	public void updateBlock(int x, int y) {
		byte hp = Globals.getWorld().getBlockHP(x, y);

		if (BlockHelper.plant.hasGrown(Plant.GROW_THRESHOLD)) {
			if (this.canBloom()) {
				Globals.getWorld().setBlockHP(BlockHelper.plant.setGrowLevel(hp, (byte) (GROW_THRESHOLD + 1)), x, y);
			} else {
				Globals.getWorld().setBlockHP(BlockHelper.plant.setGrowLevel(hp, (byte) GROW_THRESHOLD), x, y);
			}
		} else {
			Globals.getWorld().setBlockHP(BlockHelper.plant.setGrowLevel(hp, (byte) (BlockHelper.plant.getGrowLevel(hp) + 1)), x, y);
		}
	}

    protected boolean canBloom() {
    	return true;
    }

    public boolean canBeGrownAt(int x, int y) {
        short id = Globals.getWorld().getBlockID(x, y);

        if (id != ItemID.none) {
            return false;
        }

        return true;
    }
}