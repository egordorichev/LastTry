package org.egordorichev.lasttry.util.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.egordorichev.lasttry.graphics.Graphics;

/**
 * Prints text with outline
 */
public class OutlinePrinter {
	/**
	 * Draws a string with the given font.
	 * 
	 * @param font
	 *            Font to draw with.
	 * @param string
	 *            Text to draw.
	 * @param x
	 *            X-position to draw at.
	 * @param y
	 *            Y-position to draw at.
	 */
	public static void print(BitmapFont font, String string, int x, int y) {
		// Draw border
		font.setColor(Color.BLACK);
		for (int xx = x - 1; xx < x + 2; xx++) {
			for (int yy = y - 1; yy < y + 2; yy++) {
				font.draw(Graphics.batch, string, xx, yy);
			}
		}
		// Draw inner-color
		font.setColor(Color.WHITE);
		font.draw(Graphics.batch, string, x, y);
	}
}