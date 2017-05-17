package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.Item;

public class Coin extends Item {
    public Coin(short id, String name, TextureRegion texture) {
        super(id, name, texture);
    }

    @Override
    public boolean canBeUsed() {
        return false;
    }

    @Override
    public int getMaxInStack() {
        return 100;
    }
}