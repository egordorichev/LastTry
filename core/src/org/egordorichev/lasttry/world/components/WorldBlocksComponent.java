package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.util.ByteHelper;
import org.egordorichev.lasttry.world.World;
import org.egordorichev.lasttry.world.chunk.Chunk;

public class WorldBlocksComponent extends WorldComponent {
    public WorldBlocksComponent(World world) {
        super(world);
    }

    // grid points

    public Block get(int x, int y) {
        return (Block) Item.fromID(this.getID(x, y));
    }

    public byte getLight(int x, int y) {
        Chunk chunk = this.getChunk(x, y);

        if (chunk == null) {
            return 0;
        }

        return chunk.getLight(x, y);
    }

    public void setLight(int x, int y, byte light) {
        Chunk chunk = this.getChunk(x, y);

        if (chunk == null) {
            return;
        }

        chunk.setLight(light, x, y);
    }

    public short getID(int x, int y) {
        Chunk chunk = this.getChunk(x, y);

        if (chunk == null) {
            return ItemID.none;
        }

        return chunk.getBlock(x, y);
    }

    public void set(short id, int x, int y) {
        Chunk chunk = this.getChunk(x, y);

        if (chunk == null) {
            return;
        }

        chunk.setBlock(id, x, y);
        this.updateNeighbors(x, y);
    }

    public byte getHP(int x, int y) {
        Chunk chunk = this.getChunk(x, y);

        if (chunk == null) {
            return Block.MAX_HP;
        }

        return chunk.getBlockHP(x, y);
    }

    public void setHP(byte hp, int x, int y) {
        Chunk chunk = this.getChunk(x, y);

        if (chunk == null) {
            return;
        }

        chunk.setBlockHP(hp, x, y);

        if (ByteHelper.getBitValue(hp, (byte) 0) + ByteHelper.getBitValue(hp, (byte) 1) * 2 == 0) {
            this.updateNeighbors(x, y);
        }
    }

    private Chunk getChunk(int x, int y) {
        if (!this.world.isInside(x, y)) {
            return null;
        }

        Chunk chunk = this.world.getChunks().getFor(x, y);

        if (chunk == null) {
            Globals.getWorld().getChunks().load(x / Chunk.SIZE, y / Chunk.SIZE);
            return null;
        }

        return chunk;
    }

    private void updateNeighbors(int x, int y) {
        for (int by = y - 1; by < y + 2; by++) {
            for (int bx = x - 1; bx < x + 2; bx++) {
                Block block = this.get(bx, by);

                if (block != null) {
                    block.onNeighborChange(bx, by, x, y);
                }
            }
        }
    }
}