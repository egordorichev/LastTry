package org.egordorichev.lasttry.world.generator;

import org.egordorichev.lasttry.item.ItemID;

public class TaskFoilageGen extends GeneratorTask {

    @Override
    public void run(WorldGenerator generator) {
        // Small plants
        for (int x = 0; x < generator.getWorldWidth(); x += generator.world.random.nextInt(5)) {
            int y = generator.getHighest(x);
            generator.world.blocks.set(ItemID.ashBlock, x, y);
        }

        // Larger plants
        for (int x = 0; x < generator.getWorldWidth(); x += generator.world.random.nextInt(10)) {
            int y = generator.getHighest(x);
            generator.world.blocks.set(ItemID.crimstoneBlock, x, y);
        }
    }

}
