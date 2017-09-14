package org.egordorichev.lasttry.injection;

public interface Context {
    <T> T get(Class<? extends T> requestedType);
    <T> T bindInstance(Class<? extends T> classz,T object);
    <T> void bindProvider(Class<? extends T> classz,Provider<T> provider);

}
