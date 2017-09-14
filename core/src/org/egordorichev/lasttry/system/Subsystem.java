package org.egordorichev.lasttry.system;

import org.egordorichev.lasttry.injection.Context;

public interface Subsystem {
    String name();

    void load(Context context);
}
