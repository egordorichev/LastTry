package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.chunk.Chunk;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.UUID;

//Logic responsible for carrying out chunk gc
public class ChunkGc {

    private ChunkGcCalc.ChunkGCLevel currentChunkGcLevel;

    ChunkGc(ChunkGcCalc.ChunkGCLevel levelToRunChunkGcAt) {
        this.currentChunkGcLevel = levelToRunChunkGcAt;
    }

    //todo split into smaller methods
    public void performChunkGC() {

        Log.debug("Received request to perform Chunk GC");

        assert Globals.world.chunks.getAmountOfLoadedChunks()<=ChunkGcCalc.MINIMUMLOADEDCHUNKS : "Chunks currently loaded is less than or equal to minimum loaded chunks";

        //Set flag in gc manager, signalling a chunk gc is in progress
        Globals.chunkGcManager.setChunkGcInProgress(true);

        ArrayList<Chunk> loadedChunks = Globals.world.chunks.getLoadedChunks();
        Log.debug("Amount of loaded chunks is: "+loadedChunks.size());

        // Sort by local date, most recent date is last and oldest date is first.
        Collections.sort(loadedChunks, new Comparator<Chunk>() {
            public int compare(Chunk one, Chunk other) {
                return one.getLastAccessedTime().compareTo(other.getLastAccessedTime());
            }
        });

        int amountOfChunksToFree = currentChunkGcLevel.getChunksToFree();
        Log.debug("Amount of loaded chunks to free is: "+amountOfChunksToFree);

        ArrayList<UUID> uniqueIdsOfChunksToBeFreed = new ArrayList<>();

        //We remove the oldest chunk, which will be the first chunks.
        for(int i=0; i<amountOfChunksToFree; i++){
            Chunk chunkToBeFreed = loadedChunks.get(i);
            uniqueIdsOfChunksToBeFreed.add(chunkToBeFreed.getUniqueChunkId());
        }

        //free chunks
        uniqueIdsOfChunksToBeFreed.stream().forEach(uniqueIdOfChunkToBeFreed -> {
            Globals.world.chunks.removeChunk(uniqueIdOfChunkToBeFreed);
        });

        Globals.chunkGcManager.setChunkGcInProgress(false);

        //Schedule next chunk gc
        Globals.chunkGcManager.requestFutureChunkGc();
    }

}
