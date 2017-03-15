package org.egordorichev.lasttry.mod;

public class ModException extends Exception {
	public ModException() {
		this("");
	}

	public ModException(String message) {
		super(message);
	}
}
