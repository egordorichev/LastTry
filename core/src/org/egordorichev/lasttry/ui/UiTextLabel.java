package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;

public class UiTextLabel extends UiComponent {
    /**
     * Text, to be drawn on the button
     */
    protected String label;

    /**
     * Label width in pixels
     */
    protected int labelWidth;

    /**
     * Current font
     */
    protected BitmapFont font;

    public UiTextLabel(Rectangle rectangle, UiComponent.Origin origin, String label) {
        super(rectangle, origin);

        this.label = label;

        this.setFont(Assets.f22);
    }

    public UiTextLabel(Rectangle rectangle, String label) {
        this(rectangle, UiComponent.Origin.TOP_LEFT, label);
    }

    /**
     * Renders the element
     */
    @Override
    public void render() {
        if (this.hidden) {
            return;
        }

        super.render();

        this.font.draw(LastTry.batch, this.label, this.getX(),
                this.getY() + (this.getHeight() - this.font.getLineHeight()) / 2);
    }

    /**
     * Sets font and calculates new size
     */
    protected void setFont(BitmapFont font) {
        this.font = font;
        this.setLabel(this.label);
    }

    /**
     * Calculates new size
     */
    public void setLabel(String label) {
        this.label = label;

        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(this.font, this.label);

        this.labelWidth = (int) glyphLayout.width;
        this.rect.width = this.labelWidth;
        this.rect.height = this.font.getLineHeight() * 1.f;
    }
}