package org.egordorichev.lasttry.registry;

@FunctionalInterface
public interface Provider<T>  {
    T get();
}
