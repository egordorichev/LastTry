package org.egordorichev.lasttry.assets.textures;

import org.terasology.assets.Asset;
import org.terasology.assets.AssetType;
import org.terasology.assets.ResourceUrn;

import java.util.Optional;

public class Texture extends Asset<TextureData> {
	private com.badlogic.gdx.graphics.Texture texture;

	public Texture(ResourceUrn urn, TextureData data, AssetType<?, TextureData> type) {
		super(urn, type);
		reload(data);
	}

	@Override
	protected Optional<? extends Asset<TextureData>> doCreateCopy(ResourceUrn copyUrn, AssetType<?, TextureData> parentAssetType) {
		return Optional.of(new Texture(copyUrn, new TextureData(this.texture), parentAssetType));
	}

	@Override
	protected void doReload(TextureData data) {
		this.texture = data.getTexture();
	}

	public com.badlogic.gdx.graphics.Texture getTexture() {
		return this.texture;
	}
}