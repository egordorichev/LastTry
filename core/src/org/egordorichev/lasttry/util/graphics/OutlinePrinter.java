package org.egordorichev.lasttry.util.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import org.egordorichev.lasttry.graphics.Graphics;

/**
 * Prints text with outline
 */
public class OutlinePrinter {
	/**
	 * TODO: document
	 */
	public static void print(BitmapFont font, String string, int x, int y) {
		font.setColor(Color.BLACK);


		for (int xx = x - 1; xx < x + 2; xx++) {
			for (int yy = y - 1; yy < y + 2; yy++) {
				font.draw(Graphics.batch, string, xx, yy);
			}
		}

		font.setColor(Color.WHITE);
		font.draw(Graphics.batch, string, x, y);
	}
}