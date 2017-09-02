package org.egordorichev.lasttry.core;

/** LastTry version handler */
public class Version {
	/**
	 * Minor version
	 */
	public int minor;
	/**
	 * Major version
	 */
	public double major;
	/**
	 * Version suffix (like "alpha")
	 */
	public String suffix;
	
	public Version(double major, int minor, String suffix) {
		this.major = major;
		this.minor = minor;
		this.suffix = suffix;
	}

	/**
	 * @return Version as string
	 */
	@Override
	public String toString() {
		if (this.suffix.isEmpty()) {
			return "v." + this.major + "." + this.minor;
		}
		return "v." + this.major + "." + this.minor + "-" + suffix;
	}
}