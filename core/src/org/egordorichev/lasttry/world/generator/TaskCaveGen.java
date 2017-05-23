package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.item.ItemID;

public class TaskCaveGen extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        boolean[][] terrain = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                terrain[x][y] = LastTry.random.nextBoolean();
            }
        }

        for (int i = 0; i < 8; i++) {
            terrain = this.nextStep(generator, terrain);
        }

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                if (generator.world.blocks.getID(x, y) != ItemID.dirtBlock) {
                    continue;
                }

                if (!terrain[x][y]) {
                    generator.world.blocks.set(ItemID.none, x, y);
                } else {
                    int neighbors = this.calculateNeighbors(generator, terrain, x, y);

                    if (neighbors != 8) {
                        generator.world.blocks.set(ItemID.grassBlock, x, y);
                    }
                }
            }
        }
    }

    private boolean[][] nextStep(WorldGenerator generator, boolean[][] terrain) {
        boolean[][] newTerrain = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                int neighbors = this.calculateNeighbors(generator, terrain, x, y);

                if (terrain[x][y]) {
                    if (neighbors < 3) {
                        newTerrain[x][y] = false;
                    } else {
                        newTerrain[x][y] = true;
                    }
                } else {
                    if (neighbors > 4) {
                        newTerrain[x][y] = true;
                    } else {
                        newTerrain[x][y] = false;
                    }
                }
            }
        }

        return newTerrain;
    }

    private int calculateNeighbors(WorldGenerator generator, boolean[][] terrain, int x, int y) {
        int neighbors = 0;

        for (int j = y - 1; j < y + 2; j++) {
            for (int i = x - 1; i < x + 2; i++) {
                if (i == x && j == y) {
                    continue;
                }

                if (i < 0 || j < 0 || i >= generator.getWorldWidth() || j >= generator.getWorldHeight()) {
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
