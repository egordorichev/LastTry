package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.graphics.Graphics;

public class UiTextureRegion extends UiComponent {
    private TextureRegion region;

    public UiTextureRegion(Rectangle rectangle, Origin origin, TextureRegion region) {
        super(rectangle, origin);
        this.region = region;
    }

    public UiTextureRegion(Rectangle rectangle, TextureRegion region) {
        this(rectangle, Origin.TOP_LEFT, region);
    }

    @Override
    public void render() {
        super.render();

        Graphics.batch.draw(this.region, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}