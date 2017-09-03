package org.egordorichev.lasttry.item.block;

public class LightTest extends Block {
	public LightTest(String id) {
		super(id);
	}

	@Override
	public int getBrightness() {
		return 16;
	}
}