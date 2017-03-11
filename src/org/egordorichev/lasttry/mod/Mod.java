package org.egordorichev.lasstry.mod;

public class Mod {
	protected String name;
	
	public Mod(String name) {
		this.name = name;
		this.loaded = false;
	}
	
	protected onLoad() {
	
	}
	
	protected onUnload() {
	
	}
	
	public String getName() {
		return this.name;
	}
}
