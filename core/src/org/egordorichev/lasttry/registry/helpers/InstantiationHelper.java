package org.egordorichev.lasttry.registry.helpers;

import org.egordorichev.lasttry.registry.Context;

@FunctionalInterface
public interface InstantiationHelper{
    Object lookup(Context context, Class<?> lookup);
}
