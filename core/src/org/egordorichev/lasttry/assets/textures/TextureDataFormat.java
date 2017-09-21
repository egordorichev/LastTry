package org.egordorichev.lasttry.assets.textures;

import org.terasology.assets.ResourceUrn;
import org.terasology.assets.format.AbstractAssetFileFormat;
import org.terasology.assets.format.AssetDataFile;

import java.io.IOException;
import java.util.List;

public class TextureDataFormat extends AbstractAssetFileFormat<TextureData> {
	public TextureDataFormat() {
		super("png");
	}

	@Override
	public TextureData load(ResourceUrn urn, List<AssetDataFile> inputs) throws IOException {
		TextureData data = new TextureData();

		if (!inputs.isEmpty()) {
			try {
				com.badlogic.gdx.graphics.Texture texture = new
					com.badlogic.gdx.graphics.Texture(inputs.get(0).getFilename());
				data.setTexture(texture);
			} catch (Exception exception) {
				// TODO: log error
			}
		}

		return data;
	}
}