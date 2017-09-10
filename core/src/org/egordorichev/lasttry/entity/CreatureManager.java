package org.egordorichev.lasttry.entity;

import java.util.Set;

public interface CreatureManager{
    void load();
    CreatureInfo getCreatureInfo(String name);
    Creature create(String name);
    Set<String> keys();
    boolean canSpawn(String name, int availableMaxSpawn);

}
