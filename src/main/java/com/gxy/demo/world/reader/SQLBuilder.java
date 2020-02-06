package com.gxy.demo.world.reader;

import org.springframework.batch.core.StepExecution;

public class SQLBuilder {

    public String buildSQL(StepExecution stepExecution) {
        String sql = "select name code, name, surfaceArea, population from country where continent = '";
        String continent = stepExecution.getJobParameters().getString("continent");
        return sql + continent + "'";
    }
}
