package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.items.ToolPower;

public class EvilBlock extends BlockGround {
    public EvilBlock(short id, String name, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles) {
        super(id, name, requiredPower, texture, tiles);
    }

    public static boolean canBeInfected(int id) {
        if (id == ItemID.stoneBlock || id == ItemID.sandBlock || id == ItemID.grassBlock) {
            return true;
        }

        return false;
    }

    public static short getInfectIDFor(short fromID, short id) {
        if (EvilBlock.isCrimsonBlock(fromID)) {
            switch (id) {
                case ItemID.stoneBlock:
                    return ItemID.crimstoneBlock;
                case ItemID.sandBlock:
                    return ItemID.crimsandBlock;
                case ItemID.iceBlock:
                    return ItemID.redIceBlock;
            }
        } else {
            switch (id) {
                case ItemID.stoneBlock:
                    return ItemID.ebonstoneBlock;
                case ItemID.sandBlock:
                    return ItemID.ebonsandBlock;
                case ItemID.iceBlock:
                    return ItemID.purpleIceBlock;
            }
        }

        return id;
    }

    public static boolean isCrimsonBlock(short id) {
        switch (id) {
            case ItemID.crimstoneBlock:
            case ItemID.crimsandBlock:
            case ItemID.viciousMushroom:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void updateBlock(int x, int y) {
        int nx = x - 3 + LastTry.random.nextInt(7);
        int ny = y - 3 + LastTry.random.nextInt(7);

        if (nx == x && ny == y) {
            return;
        }

        Block block = (Block) Item.fromID(Globals.getWorld().getBlockID(nx, ny));

        if (block != null && EvilBlock.canBeInfected(block.getID())) {
            Globals.getWorld().setBlock(EvilBlock.getInfectIDFor(this.id, block.getID()), nx, ny);
        }
    }
}