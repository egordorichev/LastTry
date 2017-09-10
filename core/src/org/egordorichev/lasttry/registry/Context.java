package org.egordorichev.lasttry.registry;

public interface Context {
    Provider  get(Class parent);

     <T> void bindProvider(Class<? extends T> classz,Provider<T> provider);
     <T> T bindInstance(Class<? extends T> classz,T object);
     <T> void bindeInterface(Class<T> interfaceType, Class<? extends T> implementationType) throws IllegalArgumentException;
}
