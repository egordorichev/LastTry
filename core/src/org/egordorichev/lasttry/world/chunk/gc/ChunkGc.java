package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.chunk.Chunk;

import java.lang.reflect.Array;
import java.util.*;

//Logic responsible for carrying out chunk gc
public class ChunkGc {

    private ChunkGcCalc.ChunkGCLevel currentChunkGcLevel;

    ChunkGc(ChunkGcCalc.ChunkGCLevel levelToRunChunkGcAt) {
        this.currentChunkGcLevel = levelToRunChunkGcAt;
    }

    public void performChunkGC() {

        Log.debug("Received request to perform Chunk GC");

        assert Globals.world.chunks.getImmutableLoadedChunks().size()<=ChunkGcCalc.MINIMUMLOADEDCHUNKS : "Chunks currently loaded is less than or equal to minimum loaded chunks";

        //Set flag in gc manager, signalling a chunk gc is in progress
        setChunkGcInProgressFlag(true);

        List<Chunk> loadedChunks = Globals.world.chunks.getImmutableLoadedChunks();
        Log.debug("Amount of loaded chunks is: "+loadedChunks.size());

        ArrayList<UUID> uniqueIdsOfChunksToBeFreed = this.getUniqueIdsOfChunksToBeFreed(loadedChunks);

        freeChunks(uniqueIdsOfChunksToBeFreed);

        finish();

    }

    private void setChunkGcInProgressFlag(boolean Flag) {
        Globals.chunkGcManager.setChunkGcInProgress(true);
    }

    private ArrayList<UUID> getUniqueIdsOfChunksToBeFreed(List<Chunk> loadedChunks) {
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

        return uniqueIdsOfChunksToBeFreed;
    }

    private void freeChunks(List<UUID> idsOfChunksToBeFreed) {
        //free chunks
        idsOfChunksToBeFreed.stream().forEach(uniqueIdOfChunkToBeFreed -> {
            Globals.world.chunks.removeChunk(uniqueIdOfChunkToBeFreed);
        });
    }

    private void finish() {
        setChunkGcInProgressFlag(true);

        //Schedule next chunk gc
        Globals.chunkGcManager.requestFutureChunkGc();
    }

}
