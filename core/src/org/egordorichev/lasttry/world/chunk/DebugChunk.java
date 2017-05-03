package org.egordorichev.lasttry.world.chunk;

import org.egordorichev.lasttry.item.Item;
import org.egordorichev.lasttry.item.block.Block;

import java.util.HashMap;
import java.util.UUID;

/**
 * Created by Admin on 03/05/2017.
 */
public class DebugChunk {

    UUID currentUUID;

    static HashMap<UUID, Block> blockAndUUID = new HashMap<>();

    static int counter = 0;

    public static Block getBlockToGenerate(UUID randomUUID){


        if(!blockAndUUID.containsKey(randomUUID)){

            if(counter>2){
                counter = 0;
            }

            Block block = (Block) Item.fromID(1);
            Block block2 = (Block) Item.fromID(12);
            Block block3 = (Block) Item.fromID(13);

            switch (counter){
                case 0:
                    blockAndUUID.put(randomUUID, block);
                    counter++;
                    return block;

                case 1:
                    blockAndUUID.put(randomUUID, block2);
                    counter++;
                    return block2;

                case 2:
                    blockAndUUID.put(randomUUID, block3);
                    counter++;
                    return block3;

            }
        }

        return blockAndUUID.get(randomUUID);
    }


}
