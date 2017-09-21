package org.egordorichev.lasttry.assets.textures;

import com.badlogic.gdx.graphics.Texture;
import org.terasology.assets.AssetData;

public class TextureData implements AssetData {
	private Texture texture;

	public TextureData() {
		// Be careful :P
	}

	public TextureData(Texture texture) {
		this.texture = texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return this.texture;
	}
}