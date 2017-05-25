package org.egordorichev.lasttry.world;

import java.util.Random;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Camera;
import org.egordorichev.lasttry.util.Rectangle;
import org.egordorichev.lasttry.util.Util;
import org.egordorichev.lasttry.world.chunk.Chunk;
import org.egordorichev.lasttry.world.components.*;

import com.badlogic.gdx.math.Vector2;

public class World {
    public static final int UPDATE_DELAY_SECONDS = 1;

    private final WorldFlagsComponent flags;
    private final WorldChunksComponent chunks;
    private final WorldBlocksComponent blocks;
    private final WorldWallsComponent walls;
    private final WorldLightingComponent light;
    /**
     * Random instance, used for terrain generation. Since it's associated with
     * the world seed, it will provide the same results every time if the seed
     * is the same.
     */
    public final Random random;
    /**
     * World size.
     */
    private final Size size;
    /**
     * World name.
     */
    private final String name;
    /**
     * World seed, used for terrain generation.
     */
    private final int seed;
    /**
     * Boolean flag for if light needs to be recalculated near the player.
     */
    private boolean lightDirty;

    public World(String name, Size size, int flags, int seed) {
        this.size = size;
        this.name = name;
        this.seed = seed;
        this.random = new Random(seed);
        this.chunks = new WorldChunksComponent(this);
        this.flags = new WorldFlagsComponent(this, flags);
        this.blocks = new WorldBlocksComponent(this);
        this.walls = new WorldWallsComponent(this);
        this.light = new WorldLightingComponent(this);

        Util.runDelayedThreadSeconds(new Callable() {
            @Override
            public void call() {
                update();
            }
        }, UPDATE_DELAY_SECONDS);
    }

    public void renderLights() {
       if (!LastTry.noLight){
           this.light.render();
       }
    }

    public void render() {
        this.getChunks().render();
    }

    public void updateLight(int dt) {
        if (!LastTry.noLight && lightDirty) {
            this.light.update(dt);
            lightDirty = false;
        }
    }

    /**
     * Create the light map for the entire world.
     */
    public void initLights() {
        if (!LastTry.noLight){
            this.light.init();
        }
    }

    /**
     * Called when a block is broken.
     * 
     * @param x
     * @param y
     */
    public void onBlockBreak(int x, int y) {
        // update lighting
        if (closeToPlayer(x, y)) {
            lightDirty = true;
        }
    }

    /**
     * Called when a wall is broken.
     * 
     * @param x
     * @param y
     */
    public void onWallBreak(int x, int y) {
        // update lighting
        if (closeToPlayer(x, y)) {
            lightDirty = true;
        }
    }

    /**
     * Boolean checking of the given coordinates are near the player. Used
     * primarily if checking if lighting should be re-calculated from a block
     * modification event.
     * 
     * @param x
     * @param y
     * @return
     */
    private boolean closeToPlayer(int x, int y) {
        if (LastTry.noLight){
            return false;
        }
        return Globals.player.physics.getGridPosition().dst(new Vector2(x, y)) < (Camera.getXInBlocks());
    }

    public void update() {
        this.getChunks().update();
    }
    
    public WorldChunksComponent getChunks() {
        return chunks;
    }

    public WorldFlagsComponent getFlags() {
        return flags;
    }

    public short getWidth() {
        return this.size.getWidth();
    }

    public short getHeight() {
        return this.size.getHeight();
    }

    public Size getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }

    public int getSeed() {
        return seed;
    }

    public Block getBlock(int x, int y) {
        return blocks.get(x, y);
    }

    public short getBlockID(int x, int y) {
        return blocks.getID(x, y);
    }

    public void setBlock(short id, int x, int y) {
        blocks.set(id, x, y);
        // update lighting
        if (closeToPlayer(x, y)) {
            lightDirty = true;
        }
    }

    public byte getBlockHP(int x, int y) {
        return blocks.getHP(x, y);
    }

    public void setBlockHP(byte hp, int x, int y) {
        blocks.setHP(hp, x, y);
    }

    public byte getLight(int x, int y) {
        return blocks.getLight(x, y);
    }

    public void setLight(int x, int y, byte light) {
        blocks.setLight(x, y, light);
    }

    public Wall getWall(int x, int y) {
        return walls.get(x, y);
    }

    public short getWallID(int x, int y) {
        return walls.getID(x, y);
    }

    public byte getWallHP(int x, int y) {
        return walls.getHP(x, y);
    }

    public void setWallHP(byte hp, int x, int y) {
        walls.setHP(hp, x, y);
    }

    public void setWall(short id, int x, int y) {
        walls.set(id, x, y);
        // update lighting
        if (closeToPlayer(x, y)) {
            lightDirty = true;
        }
    }

    // GridPoints
    public boolean isInside(int x, int y) {
        return (x >= 0 && x < this.getWidth() && y >= 0 && y < this.getHeight());
    }
    
    public Vector2 updateVelocity(Vector2 pos, Vector2 vel){
        return vel;
    }

    public boolean isColliding(Rectangle bounds) {
        Rectangle gridBounds = new Rectangle(bounds.x, bounds.y, bounds.width, bounds.height);
        gridBounds.x /= Block.SIZE;
        gridBounds.y /= Block.SIZE;
        gridBounds.width /= Block.SIZE;
        gridBounds.height /= Block.SIZE;

        for (int y = (int) gridBounds.y - 1; y < gridBounds.y + gridBounds.height + 1; y++) {
            for (int x = (int) gridBounds.x - 1; x < gridBounds.x + gridBounds.width + 1; x++) {
                if (!this.isInside(x, y)) {
                    return true;
                }

                Block block = (Block) Item.fromID(this.blocks.getID(x, y));

                if (block == null || !block.isSolid()) {
                    continue;
                }

                Rectangle blockRect = new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE);

                if (blockRect.intersects(bounds)) {
                    return true;
                }
            }
        }

        return false;
    }

  

    public enum Size {
        SMALL("Small", 4096, 1024), // Small contains 64 chunks
        MEDIUM("Medium", 6144, 2048), // Medium contains 192 chunks
        LARGE("Large", 8192, 2304), // Large contains 288 chunks
        DEVEXTRALARGE("Dev: Extra Large", 16384, 4608); // Dev Extra Large
                                                        // contains 1152 chunks

        private String name;
        private short width;
        private short height;
        private short maxChunks;

        Size(String name, int width, int height) {
            this.name = name;
            this.width = (short) width;
            this.height = (short) height;
            this.maxChunks = (short) maxChunks;
        }

        @Override
        public String toString() {
            return this.name;
        }

        public short getWidth() {
            return this.width;
        }

        public short getHeight() {
            return this.height;
        }

        public short getMaxChunks() {

            int maxChunks = (width / Chunk.SIZE) * (short) (height / Chunk.SIZE);

            return (short) maxChunks;
        }
    }

}