package org.egordorichev.lasttry.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Graphics {
	public static TextureRegion[] tileCracks;

	public static void load() {
		tileCracks = new TextureRegion[24];

		for (int x = 0; x < 6; x++) {
			for (int y = 0; y < 4; y++) {
				tileCracks[x * 4 + y] = new TextureRegion(Textures.tileCracks,
					x * 18, y * 18, 18, 18);
			}
		}
	}
}