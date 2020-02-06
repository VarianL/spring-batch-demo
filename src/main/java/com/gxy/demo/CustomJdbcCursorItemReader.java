package com.gxy.demo;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Value;

public class CustomJdbcCursorItemReader<T> extends JdbcCursorItemReader<T> {

    @Value(value = "#{stepExecution}")
    private StepExecution stepExecution;

    @Override
    public void afterPropertiesSet() throws Exception {
        String sql = "select name code, name, surfaceArea, population from country where continent = '";
        String continent = stepExecution.getJobParameters().getString("continent");
        super.setSql(sql + continent + "'");
        super.afterPropertiesSet();
    }
}
