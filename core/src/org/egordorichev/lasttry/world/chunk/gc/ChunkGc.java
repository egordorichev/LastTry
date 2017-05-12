package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.util.Log;
import org.egordorichev.lasttry.world.chunk.Chunk;

import java.util.*;

//Logic responsible for carrying out chunk gc
public class ChunkGc {
    private ChunkGcCalc.ChunkGCLevel currentChunkGcLevel;

    ChunkGc(ChunkGcCalc.ChunkGCLevel levelToRunChunkGcAt) {
        this.currentChunkGcLevel = levelToRunChunkGcAt;
    }

    public void onWakeUp(){
        if (currentChunkGcLevel== ChunkGcCalc.ChunkGCLevel.SLEEP){
            Globals.chunkGcManager.scheduleChunkGc(currentChunkGcLevel);
        } else {
            this.beginChunkGC();
        }
    }

    public void beginChunkGC() {
        Log.debug("Received request to perform Chunk GC");

        this.startUp();
        this.performChunkGc();
        this.finish();
    }

    private void performChunkGc() {
        List<Chunk> mutableLoadedChunks = this.retrieveMutableLoadedChunks();
        ArrayList<UUID> uniqueIdsOfChunksToBeFreed = this.getUniqueIdsOfChunksToBeFreed(mutableLoadedChunks);

        this.freeChunks(uniqueIdsOfChunksToBeFreed);
    }

    private List<Chunk> retrieveMutableLoadedChunks() {
        List<Chunk> loadedChunks = Globals.world.chunks.getImmutableLoadedChunks();
        List<Chunk> mutableLoadedChunks = new ArrayList<Chunk>(loadedChunks);
        Log.debug("Amount of loaded chunks is: "+mutableLoadedChunks.size());

        return mutableLoadedChunks;
    }

    private void setChunkGcInProgressFlag(boolean flag) {
        Globals.chunkGcManager.setChunkGcInProgress(flag);
    }

    private ArrayList<UUID> getUniqueIdsOfChunksToBeFreed(List<Chunk> loadedChunks) {
        this.sortBasedOnDate(loadedChunks);

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

    private void startUp() {
        assert Globals.world.chunks.getImmutableLoadedChunks().size()<=ChunkGcCalc.MINIMUMLOADEDCHUNKS : "Chunks currently loaded is less than or equal to minimum loaded chunks";

        //Set flag in gc manager, signalling a chunk gc is in progress
        setChunkGcInProgressFlag(true);
    }

    private void finish() {
        setChunkGcInProgressFlag(false);

        //Schedule next chunk gc
        Globals.chunkGcManager.requestFutureChunkGc();
    }

    private void sortBasedOnDate(List<Chunk> loadedChunks) {
        // Sort by local date, most recent date is last and oldest date is first.
        Collections.sort(loadedChunks, new Comparator<Chunk>() {
            public int compare(Chunk one, Chunk other) {
                return one.getLastAccessedTime().compareTo(other.getLastAccessedTime());
            }
        });
    }

}
