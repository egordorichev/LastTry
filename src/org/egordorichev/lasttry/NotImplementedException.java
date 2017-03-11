package org.egordorichev.lasstry;

public class NotImplementedException extends Exception {
	public NotImplementedException() {
		this("");
	}

	public NotImplementedException(String message) {
		super(message);
	}
}
