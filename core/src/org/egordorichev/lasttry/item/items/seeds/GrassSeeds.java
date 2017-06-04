package org.egordorichev.lasttry.item.items.seeds;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.plant.Grass;
import org.egordorichev.lasttry.util.Rectangle;

public class GrassSeeds extends Item {
    private Grass grass;

    public GrassSeeds(short id, String name, TextureRegion texture, Grass grass) {
        super(id, name, texture);

        this.grass = grass;
    }

    @Override
    public boolean use() {
        // Get world position to place block at
        int x = LastTry.getMouseXInWorld() / Block.SIZE;
        int y = LastTry.getMouseYInWorld() / Block.SIZE;
        // TODO: Distance checks from cursor coordinates to player coordinates

        short id = Globals.getWorld().getBlockID(x, y);

        // Check if the plant can be placed.
        if (this.grass.canBeGrownAt(id)) {
            // Check if the plant intersects the player's hitbox
            // TODO: Check other entities in the world
            Rectangle rectangle = Globals.player.physics.getHitbox();

            if (rectangle.intersects(new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE))) {
                return false;
            }

            Globals.getWorld().setBlock(this.grass.getID(), x, y);
            Globals.getWorld().setBlock((byte) 1, x, y);

            return true;
        }

        return false;
    }
}