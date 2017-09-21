package org.egordorichev.lasttry.assets.textures;

import org.terasology.assets.AssetFactory;
import org.terasology.assets.AssetType;
import org.terasology.assets.ResourceUrn;

public class TextureFactory implements AssetFactory<Texture, TextureData> {
	@Override
	public Texture build(ResourceUrn urn, AssetType<Texture, TextureData> assetType, TextureData data) {
		return new Texture(urn, data, assetType);
	}
}
