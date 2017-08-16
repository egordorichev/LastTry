package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.graphics.Graphics;

public class UiImage extends UiComponent {
    private TextureRegion texture;

    public UiImage(Rectangle rectangle, Origin origin, TextureRegion texture) {
        super(rectangle, origin);

        this.texture = texture;
    }

    public UiImage(Rectangle rectangle, TextureRegion texture) {
        super(rectangle);

        this.texture = texture;
    }

    public void setTextureRegion(TextureRegion texture) {
        this.texture = texture;
    }

    @Override
    public void render() {
        super.render();

        Graphics.batch.draw(this.texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }
}