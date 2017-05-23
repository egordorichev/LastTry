package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.item.ItemID;

public class TaskCaveGenSimplex extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        int octaves = 4;
        float roughness = 0.5f;
        float scale = 1f/20f;
        float cut = 0.0f;
        boolean[][] solidMap = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                float f =  SimplexNoise.octavedNoise(x, y, octaves, roughness, scale);
                boolean isSolid = f <= cut;
                solidMap[x][y] = isSolid;
            }
        }


        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                if (generator.world.blocks.getID(x, y) == ItemID.none) {
                    continue;
                }
                if (!solidMap[x][y]) {
                    generator.world.blocks.set(ItemID.none, x, y);
                } else {
                    int neighbors = this.calculateNeighbors(generator, solidMap, x, y);
                    if (neighbors <= 2){
                        generator.world.blocks.set(ItemID.none, x, y);
                    } else 
                    if (neighbors != 8) {
                        generator.world.blocks.set(ItemID.grassBlock, x, y);
                    } 
                }
            }
        }
    }

    private int calculateNeighbors(WorldGenerator generator, boolean[][] terrain, int x, int y) {
        int neighbors = 0;
        int w = generator.getWorldWidth();
        int h = generator.getWorldHeight();
        for (int j = y - 1; j < y + 2; j++) {
            for (int i = x - 1; i < x + 2; i++) {
                if (i == x && j == y) {
                    continue;
                }

                if (i < 0 || j < 0 || i >= w || j >= h) {
                    neighbors++;
                    continue;
                }

                if (terrain[i][j]) {
                    neighbors++;
                }
            }
        }

        return neighbors;
    }

}
