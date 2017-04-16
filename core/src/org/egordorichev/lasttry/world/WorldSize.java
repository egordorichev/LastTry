package org.egordorichev.lasttry.world;

public enum WorldSize {
    /** 4200x1200 blocks */
    SMALL("Small"),

    /** 6400x1800 blocks */
    MEDIUM("Medium"),

    /** 8400x2400 blocks */
    LARGE("Large");

    private String name;

    WorldSize(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}