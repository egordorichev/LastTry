package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Log;

/**
 * Handles automatic removal of unused loaded chunks from game memory.
 * Rules:
 * LoadedChunks must contain at least 2 chunks -- Current chunk and last loaded chunks.
 */
public class ChunkGc {

    private ChunkGcCalc.ChunkGCLevel currentChunkGcLevel;

    ChunkGc(ChunkGcCalc.ChunkGCLevel levelToRunChunkGcAt) {
        this.currentChunkGcLevel = levelToRunChunkGcAt;
    }

    public void performChunkGC() {

        Log.debug("Received request to perform Chunk GC");

        //todo set flag in chunk gc manager to true, signalling a chunk gc is in progress

        //todo once complete set a flag in chunk gc manager, signalling a chunk gc is no longer in progress

        //todo once complete, call method in chunk gc manager to schedule next gc
    }

}
