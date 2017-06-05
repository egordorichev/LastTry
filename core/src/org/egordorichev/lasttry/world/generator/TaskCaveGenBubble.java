package org.egordorichev.lasttry.world.generator;

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
                if (generator.world.getBlockID(x, y) != ItemID.dirtBlock) {
                    continue;
                }

                if (!solidMap[x][y]) {
                    generator.world.setBlock(ItemID.none, x, y);
                } else {
                    int neighbors = this.calculateNeighbors(generator, solidMap, x, y);

                    if (neighbors != 8) {
                        generator.world.setBlock(ItemID.grassBlock, x, y);
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
   

}
