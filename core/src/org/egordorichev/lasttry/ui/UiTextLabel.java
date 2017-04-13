package org.egordorichev.lasttry.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.graphics.Assets;

public class UiTextLabel extends UiComponent {

    public enum FontStyles
    {
        ERRORMESSAGE;
    }

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

    /**
     * When a specific font style has been requested, we use this private font variable.
     * So the specific font style will be applied to only this instance.
     */
    private BitmapFont privateFontInstance;

    /**
     * A private variable used when a specific is requested for a private instance.
     */
    private FontStyles fontStyle;

    public UiTextLabel(Rectangle rectangle, UiComponent.Origin origin, String label) {
        super(rectangle, origin);

        this.label = label;

        this.setFont(Assets.f22);

    }

    public UiTextLabel(Rectangle rectangle, String label) {
        this(rectangle, UiComponent.Origin.TOP_LEFT, label);
    }

    /**
     * Renders the element.  Checks if a specific font color has been set for the element.  If a font colour does exist
     * we create a new font object and change the font color for this specific object.
     *
     */
    @Override
    public void render() {
        if (this.hidden) {
            return;
        }

        super.render();

        /**
         * I used the if statement to ensure, only the font style for this specific object instance will change.
         */
        if(fontStyle==null) {
            //Draw the font using the global 'font' variable.
            this.font.draw(LastTry.batch, this.label, this.getX(),
                    this.getY() + (this.getHeight() - this.font.getLineHeight()) / 2);
        }
        else {
            //Check if private font object instance has been created, to prevent creating repeatedly.
            if(privateFontInstance==null)
            {
                privateFontInstance = Assets.f22Red;
            }

            //Draw the new font style.
            privateFontInstance.draw(LastTry.batch, this.label, this.getX(),
                    this.getY() + (this.getHeight() - this.font.getLineHeight()) / 2);

        }
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

    /**
     * Sets the fontStyle to be applied to the font, for this specific instance.
     *
     * @param fontStyle Enum representing requested fontStyle.
     */
    public void setFontStyle(final FontStyles fontStyle)
    {
        this.fontStyle = fontStyle;
    }
}