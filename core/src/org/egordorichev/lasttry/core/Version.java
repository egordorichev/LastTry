package org.egordorichev.lasttry.core;

public class Version {
	public int minor;
	public double major;
	public String suff;
	
	public Version(double major, int minor, String suff) {
		this.major = major;
		this.minor = minor;
		this.suff = suff;
	}

	@Override
	public String toString() {
		if (this.suff.isEmpty()) {
			return "v." + this.major + "." + this.minor;
		} else {
			return "v." + this.major + "." + this.minor + "-" + suff;
		}
	}
}
