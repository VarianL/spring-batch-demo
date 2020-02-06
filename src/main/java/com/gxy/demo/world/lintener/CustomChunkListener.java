package com.gxy.demo.world.lintener;

import org.springframework.context.annotation.ComponentScan;

import javax.batch.api.chunk.listener.ChunkListener;


@ComponentScan
public class CustomChunkListener implements ChunkListener {

    @Override
    public void beforeChunk() throws Exception {
        System.out.println("----------------------------------");
        System.out.println("this is before  ChunkListener");
        System.out.println("----------------------------------");
    }

    @Override
    public void onError(Exception e) throws Exception {
        System.out.println("----------------------------------");
        System.out.println("this is Error ChunkListener");
        System.out.println("----------------------------------");
    }

    @Override
    public void afterChunk() throws Exception {
        System.out.println("----------------------------------");
        System.out.println("this is after ChunkListener ");
        System.out.println("----------------------------------");
    }
}
