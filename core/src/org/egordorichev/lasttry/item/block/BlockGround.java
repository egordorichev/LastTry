package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Graphics;
import org.egordorichev.lasttry.item.items.ToolPower;

public class BlockGround extends Block {
    public BlockGround(short id, String name, ToolPower requiredPower,
            Texture texture, Texture tiles) {

        super(id, name, true, requiredPower, texture, tiles);
    }

    /**
     * Overridden so blocks of the same type merge.
     */
    @Override
    public void renderBlock(int x, int y) {
        boolean t = LastTry.world.blocks.get(x, y - 1) instanceof BlockGround;
        boolean r = LastTry.world.blocks.get(x + 1, y) instanceof BlockGround;
        boolean b = LastTry.world.blocks.get(x, y + 1) instanceof BlockGround;
        boolean l =LastTry.world.blocks.get(x - 1, y) instanceof BlockGround;

        short variant = 1; // TODO: FIXME: replace  with var
	    byte binary = Block.calculateBinary(t, r, b, l);

        if (binary == 15) {
            // TODO: Replace (binary) with (corner)
            // It's not getting the right texture for some reason.

            LastTry.batch.draw(this.tiles, x * Block.SIZE,
                y * Block.SIZE, Block.SIZE, Block.SIZE,
                Block.SIZE * (binary), 48 + variant * Block.SIZE, Block.SIZE,
                Block.SIZE, false, false);
        } else {
            LastTry.batch.draw(this.tiles, x * Block.SIZE,
                y * Block.SIZE, Block.SIZE, Block.SIZE,
                Block.SIZE * (binary), variant * Block.SIZE, Block.SIZE,
                Block.SIZE, false, false);
        }

	    if (this.renderCracks()) {
		    byte hp = LastTry.world.blocks.getHP(x, y);

		    if (hp < Block.MAX_HP) {
			    LastTry.batch.draw(Graphics.tileCracks[Block.MAX_HP - hp], x * Block.SIZE, y * Block.SIZE);
		    }
	    }
    }
}