package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Color;
import org.egordorichev.lasttry.item.Item;

public class Dye extends Item {
    protected Color color;

    public Dye(String id) {
        super(id);
    }

    public Color getColor() {
        return this.color;
    }
}
