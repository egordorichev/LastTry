package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.math.Rectangle;

public class UiCard extends UiPanel {
    public UiCard(Rectangle rectangle, Origin origin) {
        super(origin);

        this.rect = rectangle;
    }

    public UiCard(Rectangle rectangle) {
        this(rectangle, Origin.TOP_LEFT);
    }

    @Override
    public void render() {
        super.render();

        // TODO: render blue rect
    }
}
