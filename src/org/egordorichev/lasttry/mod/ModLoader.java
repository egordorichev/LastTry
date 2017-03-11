package org.egordorichev.lasttry.mod;

import java.util.ArrayList;

public class ModLoader {
	private ArrayList<Mod> mods;
	
	public ModLoader() {
		mods = new ArrayList<>();
	}
	
	public Mod loadMod(String name) {
		return null;
		// throw new NotImplementedException();
	}
	
	public void unloadMod(String name) {
		try {
			this.unloadMod(this.getMod(name));
		} catch(ModException exception) {
			exception.printStackTrace();
		}
	}
	
	public void unloadMod(Mod mod) throws ModException {
		if(mod == null) {
			throw new ModException("Mod is null");
		}
		
		mod.onUnload();
		// throw new NotImplementedException();
	}
	
	public Mod getMod(String name) {
		for(Mod mod : this.mods) {
			if(mod.getName().equals(name)) {
				return mod;
			}
		}
		
		return null;
	}
}
