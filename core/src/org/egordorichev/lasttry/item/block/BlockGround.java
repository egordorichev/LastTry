package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.item.Item;

public class BlockGround extends TileableBlock {
	public BlockGround(String id) {
		super(id);
	}

	@Override
	protected boolean canConnect(Item other) {
		return (other instanceof BlockGround);
	}
}