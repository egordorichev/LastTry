package org.egordorichev.lasttry.world.components;

import org.egordorichev.lasttry.world.WorldProvderImpl;
    
public class WorldFlagsComponent extends WorldComponent {
	public static final int CRIMSON = 1;
	public static final int HARDMODE = 2;
	public static final int EXPERT = 3;

	private int flags;

	public WorldFlagsComponent(WorldProvderImpl world, int flags) {
		super(world);
		this.flags = flags;
	}

	public boolean evilIsCorruption() {
		return !this.evilIsCrimson();
	}


	public boolean evilIsCrimson() {
		return (this.flags & CRIMSON) == CRIMSON;
	}

	public boolean isExpertMode() {
		return (this.flags & EXPERT) == EXPERT;
	}

	public boolean isHardmode() {
		return (this.flags & HARDMODE) == HARDMODE;
	}
}