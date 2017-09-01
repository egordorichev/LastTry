package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.item.Item;

public class TileableBlock extends Block {
	public TileableBlock(String id) {
		super(id);
	}

	public TileableBlock(String id, boolean loadIcon) {
		super(id, loadIcon);
	}

	@Override
	protected boolean canConnect(Item other) {
		return other == this;
	}
}