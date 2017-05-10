package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.graphics.Assets;
import org.egordorichev.lasttry.graphics.Graphics;

public class UiButton extends UiComponent {
    protected String label;
    protected int textWidth;

    public UiButton(Rectangle rectangle, String label, Origin origin) {
        super(rectangle, origin);

        this.setLabel(label);
    }

    public UiButton(Rectangle rectangle, String label) {
        this(rectangle, label, Origin.TOP_LEFT);
    }

    public UiButton(Rectangle rectangle) {
        this(rectangle, "");
    }

    @Override
    public void render() {
        if (this.hidden) {
            return;
        }


        super.render();

        // Gdx.graphics.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight()); TODO: draw texture

        Assets.f22.draw(Graphics.batch, this.label, this.getX() + (this.getWidth() - this.textWidth) / 2, this.getY()
                + (this.getHeight() - Assets.f22.getLineHeight()) / 2);
    }

    public void setLabel(String label) {
        this.label = label;
        this.textWidth = (int) new GlyphLayout(Assets.f22, this.label).width;
    }
}
