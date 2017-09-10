package org.egordorichev.lasttry.injection;

@FunctionalInterface
public interface Provider<T>  {
    T get();
}
