package org.egordorichev.lasttry.util;

import com.badlogic.gdx.math.Vector2;

public class Size {
    private Vector2 size;

    public float width;
    public float height;

    public Size(float width, float height){
        size = new Vector2(width, height);
        width = size.x;
        height = size.y;
    }
}
