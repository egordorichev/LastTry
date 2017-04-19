package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.entity.drop.DroppedItem;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.ItemHolder;
import org.egordorichev.lasttry.item.ItemID;
import org.egordorichev.lasttry.item.items.ToolPower;
import org.egordorichev.lasttry.util.Rectangle;

public class Block extends Item {
    public static final int SIZE = 16;
    public static final byte MAX_HP = 4;

    /** Is the block solid */
    protected boolean solid;

    /** The tool type to use for the block */
    protected ToolPower power;

    /** The block spite-sheet */
    protected Texture tiles;

	/** Block width in tiles */
	protected int width = 1;

    /** Block height in tiles */
    protected int height = 1;

    public Block(short id, String name, boolean solid, ToolPower requiredPower, Texture texture, Texture tiles) {
        super(id, name, texture);
        this.power = requiredPower;
        this.tiles = tiles;
        this.solid = solid;
        this.useSpeed = 30;
    }

    @Override
    public boolean isAutoUse() {
        return true;
    }

	/**
     * Calculates a number based on the edges that have blocks of the same type.
     *
     * @param top    Top edge matches current type.
     * @param right  Right edge matches current type.
     * @param bottom Bottom edge matches current type.
     * @param left   Left edge matches current type.
     * @return
     */
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

    /**
     * Updates the block at given coordinates
     *
     * @param x X-position in the world.
     * @param y Y-position in the world.
     */
    public void updateBlockStyle(int x, int y) {
        /* TODO: if block has animation, update it */
    }

    public void updateBlock(int x, int y) {

    }

    public void onNeighborChange(int x, int y, int nx, int ny) {

    }

    public void die(int x, int y) {
	LastTry.entityManager.spawn(new DroppedItem(new ItemHolder(this, 1)), Block.SIZE * x, Block.SIZE * y);
    }

    public boolean canBePlaced(int x, int y) {
    	return true; // TODO: placement radius
    }

    public void place(int x, int y) {
    	LastTry.world.blocks.set(this.id, x, y);
    }

    /**
     * Renders the block at the given coordinates.
     *
     * @param x X-position in the world.
     * @param y Y-position in the world.
     */
    public void renderBlock(int x, int y) {
        boolean t = LastTry.world.blocks.getID(x, y - 1) == this.id;
        boolean r = LastTry.world.blocks.getID(x + 1, y) == this.id;
        boolean b = LastTry.world.blocks.getID(x, y + 1) == this.id;
        boolean l = LastTry.world.blocks.getID(x - 1, y) == this.id;

        // TODO: FIXME: replace with var
        short variant = 1;
        byte binary = Block.calculateBinary(t, r, b, l);

        if (binary == 15) {
            LastTry.batch.draw(this.tiles, x * Block.SIZE,
                (LastTry.world.getHeight() - y - 1) * Block.SIZE, Block.SIZE, Block.SIZE,
                Block.SIZE * (binary), 48 + variant * Block.SIZE, Block.SIZE,
                Block.SIZE, false, false);
        } else {
            LastTry.batch.draw(this.tiles, x * Block.SIZE,
                (LastTry.world.getHeight() - y - 1) * Block.SIZE, Block.SIZE, Block.SIZE,
                Block.SIZE * (binary), variant * Block.SIZE, Block.SIZE,
                Block.SIZE, false, false);
        }

        if (this.renderCracks()) {
	        byte hp = LastTry.world.blocks.getHP(x, y);

	        if (hp < Block.MAX_HP) {
				LastTry.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, (LastTry.world.getHeight() - y - 1) * Block.SIZE);
	        }
        }
    }

    /** Returns true, if we allowed to draw cracks here */
    protected boolean renderCracks() {
    	return true;
    }

    /**
     * Attempts to place the block in the world at the player's cursor.
     */
    @Override
    public boolean use() {
        int x = LastTry.getMouseXInWorld() / Block.SIZE;
        int y = LastTry.getMouseYInWorld() / Block.SIZE;

        if (this.canBePlaced(x, y) && LastTry.world.blocks.getID(x, y) == ItemID.none) {
            Rectangle rectangle = LastTry.player.physics.getHitbox();

            if (rectangle.intersects(new Rectangle(x * SIZE, y * SIZE, this.width * SIZE,
		            this.height * SIZE))) {

                return false;
            }

            this.place(x, y);

            return true;
        }

        return false;
    }

    /**
     * Returns required power to break this block
     * @return required power to break this block
     */
    public ToolPower getRequiredPower() {
    	return this.power;
    }

    /**
     * Returns the solidity of the block.
     * @return true if the block is solid.
     */
    public boolean isSolid() {
        return this.solid;
    }

    @Override
    public int getMaxInStack() {
        return 999;
    }
}
