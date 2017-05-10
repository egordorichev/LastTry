package org.egordorichev.lasttry.item.items.seeds;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.block.plant.Plant;
import org.egordorichev.lasttry.util.Rectangle;

public class Seeds extends Item {
    private Plant plant;

    public Seeds(short id, String name, Texture texture, Plant plant) {
        super(id, name, texture);

        this.plant = plant;
    }

    @Override
    public boolean use() {
        // Get world position to place block at
        int x = LastTry.getMouseXInWorld() / Block.SIZE;
        int y = LastTry.getMouseYInWorld() / Block.SIZE;
        // TODO: Distance checks from cursor coordinates to player coordinates

        // Check if the plant can be placed.
        if (this.plant.canBeGrownAt(x, y)) {
            // Check if the plant intersects the player's hitbox
            // TODO: Check other entities in the world
            Rectangle rectangle = Globals.player.physics.getHitbox();

            if (rectangle.intersects(new Rectangle(x * Block.SIZE, y * Block.SIZE, Block.SIZE, Block.SIZE))) {
                return false;
            }

            Globals.world.blocks.set(this.plant.getID(), x, y);
            Globals.world.blocks.setHP((byte) 1, x, y);

            return true;
        }

        return false;
    }

	@Override
	public int getMaxInStack() {
		return 99;
	}
}