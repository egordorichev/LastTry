package org.egordorichev.lasttry.world;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.FileReader;
import org.egordorichev.lasttry.util.FileWriter;
import org.egordorichev.lasttry.world.biome.Biome;
import org.egordorichev.lasttry.world.generator.WorldGenerator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorldProvider {
    /**
     * Current supported world version
     */
    public static short CURRENT_VERSION = 5;

    /**
     * Returns all worlds in the "worlds/" directory
     *
     * @return all worlds in the "worlds/" directory
     */
    public static WorldInfo[] getWorlds() {
        File worldsDirectory = new File("worlds/");

        if (!worldsDirectory.mkdir()) {
            try {
                worldsDirectory.createNewFile();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } else {
            LastTry.warning("There's no worlds directory so one will be created!");
        }

        File[] files = worldsDirectory.listFiles();

        List<WorldInfo> worlds = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                worlds.add(getWorldInfo("worlds/" + file.getName()));
            }
        }

        return worlds.toArray(new WorldInfo[0]);
    }

    /**
     * Returns world info from given file name
     *
     * @param fileName world file
     * @return world info
     */
    public static WorldInfo getWorldInfo(String fileName) {
        String worldName = fileName.replace("worlds/", "").replace(".wld", "");
        WorldSize size;
        int version;
        int flags = 0;

        try {
            FileReader stream = new FileReader(fileName);

            version = stream.readInt32();

            if (stream.readBoolean()) {
                flags |= World.HARDMODE;
            }

            if (stream.readBoolean()) {
                flags |= World.EXPERT;
            }

            if (stream.readBoolean()) {
                flags |= World.CRIMSON;
            }

            stream.readByte();
            stream.readByte();

            short width = stream.readInt16();
            short height = stream.readInt16();

            if (width == 500 && height == 500) {
                size = WorldSize.DEV;
            } else if (width == 4200 && height == 1200) {
                size = WorldSize.SMALL;
            } else if (width == 6400 && height == 1800) {
                size = WorldSize.MEDIUM;
            } else if (width == 8400 && height == 2400) {
                size = WorldSize.LARGE;
            } else {
                size = WorldSize.CUSTOM;
            }

            stream.close();
        } catch (Exception exception) {
            LastTry.handleException(exception);
            return null;
        }

        return new WorldInfo(worldName, size, version, flags);
    }

    /**
     * Generates new world with the given name, width, and height.
     *
     * @return new world.
     */
    public static World generate(WorldInfo info) {
        LastTry.log.info("Generating world...");

        Biome.preload();

        short width;
        short height;

        switch (info.size) {
            case DEV:
                width = height = 500;
                break;
            case SMALL:
            default:
                width = 4200;
                height = 1200;
                break;
            case LARGE:
                width = 8400;
                height = 2400;
                break;
            case MEDIUM:
                width = 6400;
                height = 1800;
                break;
        }

        WorldGenerator generator = new WorldGenerator();
        World world = new World(width, height, info.flags, generator.generate(width, height));

        LastTry.worldInfo = info;
        LastTry.world = world;

        LastTry.log("Finished generating!");

        return world;
    }

    /**
     * Saves the given world.
     *
     * @param world world to save.
     */
    public static void save(World world) {
        LastTry.log("Saving world...");

        try {
            LastTry.log(LastTry.worldInfo.name);

            FileWriter stream = new FileWriter(getFilePath(LastTry.worldInfo.name));

            stream.writeInt32(CURRENT_VERSION);
            stream.writeBoolean(world.isHardmode());
            stream.writeBoolean(world.isExpertMode());
            stream.writeBoolean(world.evilIsCrimson());

            stream.writeByte(LastTry.environment.time.getHour());
            stream.writeByte(LastTry.environment.time.getMinute());

            short width = world.getWidth();
            short height = world.getHeight();

            stream.writeInt16(width);
            stream.writeInt16(height);
            
            stream.writeInt32(LastTry.seed);
            
            // Write the number of blocks etc that have changed
            stream.writeInt32(world.changedData.size());

            // Iterate over all the positions that have changed and save
            // the data to file
            for(int position: world.changedData) {
                stream.writeInt32(position);
                stream.writeInt16(world.getWorldData().blocks[position]);
                stream.writeByte(world.getWorldData().blocksHealth[position]);
                stream.writeInt16(world.getWorldData().walls[position]);
                stream.writeByte(world.getWorldData().wallsHealth[position]);
            }

            stream.writeBoolean(true);
            stream.close();

            LastTry.log("Done saving!");
        } catch (IOException exception) {
            LastTry.handleException(exception);
        }
    }

    /**
     * Load a world by the given name. Returns null if the world cannot be
     * found.
     * <p>
     * World to load.
     *
     * @return World by name.
     */
    public static World load() {
        LastTry.log("Loading world...");

        try (FileReader stream = new FileReader(getFilePath(LastTry.worldInfo.name))) {
            int version = stream.readInt32();

            if (version > CURRENT_VERSION) {
                throw new RuntimeException("Unsupported world version: " + version);
            } else if (version < CURRENT_VERSION) {
                LastTry.log.warn("Trying to load old version world");
            }

            int flags = 0;

            if (stream.readBoolean()) {
                flags |= World.HARDMODE;
            }

            if (stream.readBoolean()) {
                flags |= World.EXPERT;
            }

            if (stream.readBoolean()) {
                flags |= World.CRIMSON;
            }

            LastTry.environment.time.setHour(stream.readByte());
            LastTry.environment.time.setMinute(stream.readByte());

            short width = stream.readInt16();
            short height = stream.readInt16();
            
            LastTry.seed = stream.readInt32();
            LastTry.random.setSeed(LastTry.seed);
            
            /*
                Instead of this it should use the world generator to generate the right world and then load the changes from the file
                And slot them in
            */
            
            // Generate the original world using the seed
            WorldGenerator generator = new WorldGenerator();
            World world = new World(width, height, flags, generator.generate(width, height));
            
            WorldData data = world.getWorldData();
            
            int numberOfIterations  = stream.readInt32();
            
            // Read in all the blocks that have changed from the file
            for (int i = 0; i < numberOfIterations; i++) {
                int position = stream.readInt32();
                world.changedData.add(position);
                data.blocks[position] = stream.readInt16();
                data.blocksHealth[position] = stream.readByte();
                data.walls[position] = stream.readInt16();
                data.wallsHealth[position] = stream.readByte();
            }
            
            world.setWorldData(data);

            if (!stream.readBoolean()) {
                throw new RuntimeException("Verification failed");
            }
            
            stream.close();
            Biome.preload();

            LastTry.log("Done loading!");

            return world;
        } catch (IOException exception) {
            // world.save();

            LastTry.handleException(exception);
            System.exit(0);
        }

        return null;
    }

    /**
     * Get the file path of a world by the given name.
     *
     * @param worldName World name.
     * @return Path to world file.
     */
    public static String getFilePath(String worldName) {
        return "worlds/" + worldName + ".wld";
    }
}