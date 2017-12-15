package org.egordorichev.lasttry.util.struct;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Pairs {
	/**
	 * Create a pair of two items of the same type.
	 * 
	 * @param left
	 * @param right
	 * @return
	 */
	public static <T> Pair<T> of(T left, T right) {
		return new Pair<>(left, right);
	}

	/**
	 * Create a set of all possible pairs of the items in the given collection.
	 * 
	 * @param collection
	 * @return
	 */
	public static <T> Set<Pair<T>> setOf(Collection<T> collection) {
		Set<Pair<T>> set = new HashSet<>();
		for (T t1 : collection) {
			for (T t2 : collection) {
				set.add(of(t1, t2));
			}
		}
		return set;
	}

}
