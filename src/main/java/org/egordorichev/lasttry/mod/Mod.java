package org.egordorichev.lasttry.mod;

public class Mod {
	protected String name;
	
	public Mod(String name) {
		this.name = name;
	}
	
	protected void onLoad() {
	
	}
	
	protected void onUnload() {
	
	}
	
	public String getName() {
		return this.name;
	}
}
