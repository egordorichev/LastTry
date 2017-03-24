package org.egordorichev.lasttry.graphics;

import java.awt.Color;
import java.awt.Font;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.util.ResourceLoader;

/** Wrapper around slick2d font mess */
public class FontWrapper {
	/** The actual font */
	private UnicodeFont font;

	public FontWrapper(String path, int style, int size, Color color) {
		try {
			this.font = new UnicodeFont(Font.createFont(Font.TRUETYPE_FONT,
				ResourceLoader.getResourceAsStream(path)).deriveFont(style, size));

			this.setColor(Color.white);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Changes the font color
	 * @param color color to be set
	 */
	public void setColor(Color color) {
		this.font.getEffects().clear();
		this.font.getEffects().add(new ColorEffect(color));
		this.font.clearGlyphs();
		this.font.addNeheGlyphs();

		try {
			this.font.loadGlyphs();
		} catch(SlickException exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * Draws given string at given position
	 * @param string string to draw
	 * @param x position X
	 * @param y position Y
	 */
	public void drawString(String string, int x, int y) {
		this.font.drawString(x, y, string);
	}

	/**
	 *  Returns font
	 * @return font
	 */
	public UnicodeFont getFont() {
		return this.font;
	}
}