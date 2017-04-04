package org.egordorichev.lasttry.item;

public enum DamageType {
    MELEE("Melee"),
    RANGED("Randged"),
    MAGIC("Magic"),
    SUMMON("Summon"),
    THROWING("Throwing");

    private String name;

    DamageType(String name) {
        this.name = name;
    }

    public String toString() {
        return this.name;
    }
}