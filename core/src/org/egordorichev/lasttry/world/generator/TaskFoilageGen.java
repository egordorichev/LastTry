package org.egordorichev.lasttry.world.generator;

public class TaskFoilageGen extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        // TODO: Replace blocks with trees and plants
        // Small plants
        for (int x = 0; x < generator.getWorldWidth(); x += generator.world.random.nextInt(5)) {
            int y = generator.getHighest(x);
            generator.world.setBlock(ItemID.ashBlock, x, y);
        }

        // Larger plants
        for (int x = 0; x < generator.getWorldWidth(); x += generator.world.random.nextInt(10)) {
            int y = generator.getHighest(x);
            generator.world.setBlock(ItemID.crimstoneBlock, x, y);
        }
    }

}
