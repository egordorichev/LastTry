package org.egordorichev.lasttry.core.context;

@FunctionalInterface
public interface Provider<T> {
	T get();
}