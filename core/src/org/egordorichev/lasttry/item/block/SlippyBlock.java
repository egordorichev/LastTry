package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.items.ToolPower;

public class SlippyBlock extends BlockGround { // TODO
    public SlippyBlock(short id, String name, ToolPower requiredPower, TextureRegion texture, TextureRegion tiles) {
        super(id, name, requiredPower, texture, tiles);
    }
}
