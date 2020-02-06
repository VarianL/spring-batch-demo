package com.gxy.demo.world.writer;

import com.gxy.demo.world.model.Result;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class WorldWriter implements ItemWriter<Result> {

    @Override
    public void write(List<? extends Result> list) throws Exception {
        System.out.println("------------- this is writer -------------");
    }
}
