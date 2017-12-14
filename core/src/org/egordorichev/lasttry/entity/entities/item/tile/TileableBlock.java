package org.egordorichev.lasttry.entity.entities.item.tile;

import org.egordorichev.lasttry.entity.asset.Assets;

public class TileableBlock extends Block {
	public TileableBlock(String id) {
		super(id);
	}

	@Override
	protected boolean shouldTile(String neighbor, String self) {
		Block block = (Block) Assets.items.get(neighbor);
		return block instanceof TileableBlock;
	}
}