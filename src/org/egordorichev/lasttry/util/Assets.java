package org.egordorichev.lasttry.util;

import org.jetbrains.annotations.Nullable;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Assets {
	public static Image dirtTileTexture = loadImage("assets/images/DirtTile.png");
	public static Image dirtWallTexture = loadImage("assets/images/DirtWall.png");
	public static Image greenSlimeTexture = loadImage("assets/images/GreenSlime.png");

	@Nullable
	public static Image loadImage(String path) {
		try {
			Image image = new Image(path);
			image.setFilter(Image.FILTER_NEAREST);

			return image;
		} catch(SlickException exception) {
			System.out.println(path + " not found.");
			System.exit(-1);
		}

		return null;
	}
}