package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.items.ToolPower;

public class SlippyBlock extends BlockGround { // TODO
    public SlippyBlock(short id, String name, ToolPower requiredPower, Texture texture, Texture tiles) {
        super(id, name, requiredPower, texture, tiles);
    }
}
