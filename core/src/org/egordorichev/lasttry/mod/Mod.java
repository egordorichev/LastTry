package org.egordorichev.lasttry.mod;

public abstract class Mod {
	/** Mod name, used in Mod UI */
	protected String name;
	
	public Mod(String name) {
		this.name = name;
	}
	
	/** Called on mod load. Write your code in this method. */
	protected abstract void onLoad();
	
	/** Called, when mod is unloading. Remove all key bindings and code, you enabled in onLoad() method */
	protected abstract void onUnload();
	
	/**
	 * Returns mod name
	 * @return mod name
	 */
	public String getName() {
		return this.name;
	}
}
