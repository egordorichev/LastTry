package org.egordorichev.lasttry.core;

public class Version {
	public int minor;
	public double major;
	
	public Version(double major, int minor) {
		this.major = major;
		this.minor = minor;
	}

	@Override
	public String toString() {
		return "v." + this.major + "." + this.minor;	
	}
}
