package org.egordorichev.lasttry;

public class Version {
	public int minor;
	public int major;
	
	public Version(int major, int minor) {
		this.major = major;
		this.minor = minor;
	}
	
        @Override
	public String toString() {
		return "v." + this.major + "." + this.minor;	
	}
}
