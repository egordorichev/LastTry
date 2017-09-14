package org.egordorichev.lasttry.entity.ai;

public interface AIManager {
    void load();
    AI get(short id);
    void set(short id,AI ai);
}
