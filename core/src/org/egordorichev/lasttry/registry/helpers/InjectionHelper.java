package org.egordorichev.lasttry.registry.helpers;

import org.egordorichev.lasttry.registry.Context;

@FunctionalInterface
public interface InjectionHelper<T>{
    void lookup(Context context,T obj);
}
