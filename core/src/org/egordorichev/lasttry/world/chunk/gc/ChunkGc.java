package org.egordorichev.lasttry.world.chunk.gc;

import org.egordorichev.lasttry.Globals;
import org.egordorichev.lasttry.LastTry;
import org.egordorichev.lasttry.util.Callable;
import org.egordorichev.lasttry.util.Util;

/**
 * Handles automatic removal of unused loaded chunks from game memory.
 * Rules:
 * LoadedChunks must contain at least 2 chunks -- Current chunk and last loaded chunks.
 */
//todo ChunkGc to be triggered using an adjustable timer or when loaded chunks is reaching max chunks
public class ChunkGc {

    private boolean isChunkGCInProcess = false;

    //todo Chunk gc will be either triggered by: timer or when loaded chunks is approaching max chunks
    public void requestChunkGC(){
        int amountOfLoadedChunks = Globals.world.chunks.getAmountOfLoadedChunks();

        if(amountOfLoadedChunks>2){
            this.triggerChunkGC();
        }else{
            //todo increase the amount of time before next chunk gc request is sent.
        }
    }

    private synchronized void setChunkGCInProcess(boolean flag) { this.isChunkGCInProcess = flag; }

    private void triggerChunkGC() {

        this.setChunkGCInProcess(true);

        Util.oneTimeRunInThread(new Callable() {
            @Override
            public void call() {
                performChunkGC();
            }
        });

    }

    private void performChunkGC() {

    }



}
