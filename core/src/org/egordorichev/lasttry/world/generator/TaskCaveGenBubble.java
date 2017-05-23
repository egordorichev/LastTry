package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.item.ItemID;

public class TaskCaveGenBubble extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        boolean[][] solidMap = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                solidMap[x][y] = generator.world.random.nextBoolean();
            }
        }

        for (int i = 0; i < 8; i++) {
            solidMap = this.nextStep(generator, solidMap);
        }

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                if (generator.world.blocks.getID(x, y) != ItemID.dirtBlock) {
                    continue;
                }

                if (!solidMap[x][y]) {
                    generator.world.blocks.set(ItemID.none, x, y);
                } else {
                    int neighbors = this.calculateNeighbors(generator, solidMap, x, y);

                    if (neighbors != 8) {
                        generator.world.blocks.set(ItemID.grassBlock, x, y);
                    }
                }
            }
        }
    }

    private boolean[][] nextStep(WorldGenerator generator, boolean[][] terrain) {
        boolean[][] solidMap = new boolean[generator.getWorldWidth()][generator.getWorldHeight()];

        for (int y = 0; y < generator.getWorldHeight(); y++) {
            for (int x = 0; x < generator.getWorldWidth(); x++) {
                int neighbors = this.calculateNeighbors(generator, terrain, x, y);

                if (terrain[x][y]) {
                    if (neighbors < 3) {
                        solidMap[x][y] = false;
                    } else {
                        solidMap[x][y] = true;
                    }
                } else {
                    if (neighbors > 4) {
                        solidMap[x][y] = true;
                    } else {
                        solidMap[x][y] = false;
                    }
                }
            }
        }

        return solidMap;
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
