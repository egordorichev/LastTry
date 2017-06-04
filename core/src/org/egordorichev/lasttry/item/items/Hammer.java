package org.egordorichev.lasttry.item.items;


import com.badlogic.gdx.graphics.g2d.TextureRegion;
import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.Rarity;
import org.egordorichev.lasttry.item.block.Block;
import org.egordorichev.lasttry.item.wall.Wall;

public class Hammer extends DigTool {
	public Hammer(short id, String name, Rarity rarity, float baseDamage, int power, int useSpeed, TextureRegion texture) {
		super(id, name, rarity, baseDamage, ToolPower.hammer(power), useSpeed, texture);
	}

	public Hammer(short id, String name, float baseDamage, int power, int useSpeed, TextureRegion texture) {
		this(id, name, Rarity.WHITE, baseDamage, power, useSpeed, texture);
	}

	@Override
	protected boolean onUse() {
		int x = LastTry.getMouseXInWorld() / Block.SIZE;
		int y = LastTry.getMouseYInWorld() / Block.SIZE;

		Wall wall = Globals.getWorld().getWall(x, y);

		if (wall == null) {
			return false;
		}

		ToolPower power = wall.getRequiredPower();

		if (this.power.isEnoughFor(power)) {
			byte hp = Globals.getWorld().getWallHP(x, y);

			if (hp > 0) {
				Globals.getWorld().setWallHP((byte) (hp - 1), x, y);
			}
		}

		return false;
	}
}