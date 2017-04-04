package org.egordorichev.lasttry.world;

public class WorldInfo {
    /**
     * World name
     */
    public String name;

    /**
     * World size
     */
    public WorldSize size;

    /**
     * World version
     */
    public int version;

    /**
     * World stats
     */
    public int flags;

    public WorldInfo(String name, WorldSize size, int version, int flags) {
        this.name = name;
        this.size = size;
        this.version = version;
        this.flags = flags;
    }

    public WorldInfo() {

    }
}