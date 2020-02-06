package com.gxy.demo.world.processor;

import com.gxy.demo.world.model.Country;
import com.gxy.demo.world.model.Result;
import org.springframework.batch.item.ItemProcessor;

public class WorldProcessor implements ItemProcessor<Country, Result> {

    @Override
    public Result process(Country country) throws Exception {
        System.out.println(country.getName());
        return null;
    }
}
