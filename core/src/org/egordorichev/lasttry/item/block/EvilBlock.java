package org.egordorichev.lasttry.item.block;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Item;

public class EvilBlock extends BlockGround {
	public EvilBlock(String id) {
		super(id);
	}

	public static boolean canBeInfected(String id) {
		return id.equals("lt:stone") || id.equals("lt:sand") || id.equals("lt:grass");
	}

	public static String getInfectIDFor(String fromID, String id) {
		if (EvilBlock.isCrimsonBlock(fromID)) {
			switch (id) {
				case "lt:stone": return "lt:crimstone";
				case "lt:sand": return "lt:crimsand";
				case "lt:ice": return "lt:red_ice";
			}
		} else {
			switch (id) {
				case "lt:stone": return "lt:ebonstone";
				case "lt:sand": return "lt:ebonsand";
				case "lt:ice": return "lt:purple_ice";
			}
		}

		return id;
	}

	public static boolean isCrimsonBlock(String id) {
		switch (id) {
			case "lt:crimstone":
			case "lt:crimsand":
			case "lt:red_ice":
				return true;
			default:
				return false;
		}
	}

	@Override
	public void updateBlock(int x, int y) {
		int nx = x - 3 + LastTry.random.nextInt(7);
		int ny = y - 3 + LastTry.random.nextInt(7);

		if (nx == x && ny == y) {
			return;
		}

		Block block = (Block) Item.fromID(Globals.getWorld().blocks.getID(nx, ny));

		if (block != null && EvilBlock.canBeInfected(block.getID())) {
			Globals.getWorld().blocks.set(EvilBlock.getInfectIDFor(this.id, block.getID()), nx, ny);
		}
	}
}