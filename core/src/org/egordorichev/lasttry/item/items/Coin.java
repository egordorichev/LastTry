package org.egordorichev.lasttry.item.items;

import org.egordorichev.lasttry.item.Item;

public class Coin extends Item {
    public Coin(String id) {
        super(id);
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