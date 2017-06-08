package org.egordorichev.lasttry.item.block.plant;

public class JungleGrass extends Grass {
	public JungleGrass(String id) {
		super(id);
	}

	@Override
	public boolean canBeGrownAt(String id) {
		return id.equals("lt:mud");
	}
}