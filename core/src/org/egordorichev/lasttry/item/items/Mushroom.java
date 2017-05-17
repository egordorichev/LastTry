package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.Item;

public class Mushroom extends Item { // TODO
    public Mushroom(short id, String name, TextureRegion texture) {
        super(id, name, texture);
    }

    @Override
    public int getMaxInStack() {
        return 999;
    }
}
