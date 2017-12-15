package org.egordorichev.lasttry.util.struct;

/**
 * Simple pair class.
 * 
 * @param <T>
 */
public class Pair<T> {
	public final T left, right;

	public Pair(T left, T right) {
		this.left = left;
		this.right = right;
	}

	/**
	 * @return Two items are the same.
	 */
	public boolean same() {
		return left.equals(right);
	}
}