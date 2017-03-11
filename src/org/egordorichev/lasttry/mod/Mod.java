package org.egordorichev.lasstry.mod;

public class Mod {
	protected String name;
	protected boolead loaded;
	
	public Mod(String name) {
		this.name = name;
		this.loaded = false;
	}
	
	public void load() {
		if(this.loaded) {
			// TODO: throw a error
		}
		
		// TODO
		
		this.onLoad();
		this.loaded = true;
	}
	
	protected onLoad() {
	
	}
	
	protected onUnload() {
	
	}
	
	public String getName() {
		return this.name;
	}
}
