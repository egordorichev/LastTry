package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.Items;

public class Wall extends org.egordorichev.lasttry.item.Item {
    protected Texture tiles;

    public Wall(short id, String name, Texture texture, Texture tiles) {
        super(id, name, texture);

        this.tiles = tiles;
    }

    public static Wall getForBlockID(int id) {
        switch (id) {
            case ItemID.none:
            default:
                return null;
            case ItemID.dirtBlock:
                return (Wall) Items.dirtWall;
        }
    }

    public void die(int x, int y) {
	    Globals.entityManager.spawn(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
    }

    /**
     * Renders the wall at the given coordinates.
     * @param x X-position in the world.
     * @param y Y-position in the world.
     */
    public void renderWall(int x, int y) {
        boolean t = Globals.world.walls.getID(x, y - 1) == this.id;
        boolean r = Globals.world.walls.getID(x + 1, y) == this.id;
        boolean b = Globals.world.walls.getID(x, y + 1) == this.id;
        boolean l = Globals.world.walls.getID(x - 1, y) == this.id;

        // TODO: FIXME: replace with variable
        int variant = 1;
        int binary = Block.calculateBinary(t, r, b, l);

        Graphics.batch.draw(this.tiles, x * Block.SIZE,
            (Globals.world.getHeight() - y - 1) * Block.SIZE, Block.SIZE, Block.SIZE,
            Block.SIZE * (binary), variant * Block.SIZE, Block.SIZE,
            Block.SIZE, false, false);
    }
}