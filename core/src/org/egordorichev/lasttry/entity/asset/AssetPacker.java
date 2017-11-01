package org.egordorichev.lasttry.entity.asset;

import com.badlogic.gdx.tools.texturepacker.TexturePacker;

/**
 * Packs textures into the atlas
 */
public class AssetPacker {
	public static void pack() {
		TexturePacker.process("textures", "atlas", "textures.atlas");
	}
}