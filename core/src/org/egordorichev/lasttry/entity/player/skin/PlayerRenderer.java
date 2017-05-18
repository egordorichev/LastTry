package org.egordorichev.lasttry.entity.player.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.graphics.Assets;

public class PlayerRenderer {
	public static final int TEXTURE_WIDTH = 40;
	public static final int TEXTURE_HEIGHT = 1120;

//	public static Pixmap playerHead = new Pixmap();
//	public static Pixmap playerBody = new Pixmap(Gdx.files.internal("enemies/PlayerBody.png"));
//	public static Pixmap playerEyes = new Pixmap(Gdx.files.internal("enemies/PlayerEyes.png"));
//	public static Pixmap playerHands = new Pixmap(Gdx.files.internal("enemies/PlayerHands.png"));
//	public static Pixmap playerFeet = new Pixmap(Gdx.files.internal("enemies/PlayerFeet.png"));

	public static TextureRegion generateTextureRegion(PlayerRenderInfo info) {
		Pixmap pixmap = new Pixmap(TEXTURE_WIDTH, TEXTURE_HEIGHT, Pixmap.Format.RGBA8888);
//
//		playerBody.setColor(info.skinColor);
//		playerHands.setColor(info.skinColor);
//		playerFeet.setColor(info.skinColor);
//		playerHead.setColor(info.skinColor);
//		pixmap.drawPixmap(playerBody, 0, 0);
//		pixmap.drawPixmap(playerHands, 0, 0);
//		pixmap.drawPixmap(playerFeet, 0, 0);
//		pixmap.drawPixmap(playerHead, 0, 0);
//		playerEyes.setColor(info.eyesColor);
//		pixmap.drawPixmap(playerEyes, 0, 0);

		// Pixmap hair = new Pixmap(Gdx.files.internal("PlayerHair" + info.hairStyle + ".png"));
		// hair.setColor(info.hairColor);
		// pixmap.drawPixmap(hair, 0, 0);

		return new TextureRegion(new Texture(pixmap));
	}
}