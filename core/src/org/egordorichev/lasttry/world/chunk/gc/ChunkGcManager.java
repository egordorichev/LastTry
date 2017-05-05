package org.egordorichev.lasttry.world.chunk.gc;
import org.egordorichev.lasttry.Globals;

/**
 * Responsible for determining intervals between Chunk GC should be run and amount of chunks to be freed.
 *
 * Created by Admin on 05/05/2017.
 */
public class ChunkGcManager {

    private int currentlyLoadedChunks;

    public void requestGC(){

        currentlyLoadedChunks = Globals.world.chunks.getAmountOfLoadedChunks();


    }





}
