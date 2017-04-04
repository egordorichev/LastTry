package org.egordorichev.lasttry.item.block;

import com.badlogic.gdx.graphics.Texture;
import org.egordorichev.lasttry.item.items.ToolPower;

public class ThornBlock extends Block {
    public ThornBlock(short id, String name, Texture texture) {
        super(id, name, false, ToolPower.pickaxe(0), null, texture);
    }
}