package org.egordorichev.lasttry.item.items;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.item.Item;

public class Dye extends Item {
    protected Color color;

    public Dye(short id, String name, Color color, TextureRegion texture) {
        super(id, name, texture);

        this.color = color;
    }

    public Color getColor() {
        return this.color;
    }
}
