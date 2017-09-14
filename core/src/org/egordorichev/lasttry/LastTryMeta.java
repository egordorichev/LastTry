package org.egordorichev.lasttry;

import org.egordorichev.lasttry.core.Version;

public class LastTryMeta {
    private final Version version = new Version(0.0, 18, "alpha");

    public  Version getVersion(){
        return version;
    }
}
