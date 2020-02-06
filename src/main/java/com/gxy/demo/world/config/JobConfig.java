package com.gxy.demo.world.config;


import com.gxy.demo.CustomJdbcCursorItemReader;
import com.gxy.demo.world.lintener.CustomChunkListener;
import com.gxy.demo.world.lintener.CustomJobListener;
import com.gxy.demo.world.lintener.CustomSkipListener;
import com.gxy.demo.world.lintener.CustomStepLinter;
import com.gxy.demo.world.model.Country;
import com.gxy.demo.world.model.Result;
import com.gxy.demo.world.processor.WorldProcessor;
import com.gxy.demo.world.tasklet.DoNothingTasklet;
import com.gxy.demo.world.tasklet.WorldTasklet;
import com.gxy.demo.world.writer.WorldWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.step.tasklet.MethodInvokingTaskletAdapter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

/**
 * JobConfig
 */
@Configuration("world")
@EnableBatchProcessing
public class JobConfig {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataSource dataSource;

    @Bean("worldJob")
    public Job worldJob() {
        return jobBuilderFactory.get("worldJob")
                .listener(this.customJobListener())
                .start(this.worldStep1()).on("COMPLETED").to(this.taskLet())
                .from(this.worldStep1()).on("*").to(this.taskLet1())
                .end()
                .build();
    }


    @Bean("worldStep1")
    @SuppressWarnings(value = "unchecked")
    public Step worldStep1() {
        return stepBuilderFactory.get("worldStep1")
                .listener(this.customStepLinter())
                .<Country, Result>chunk(10)
                .reader(this.countryReader())
                .processor(this.worldProcessor())
                .listener(this.customChunkListener())
                .writer(this.worldWriter())
                .faultTolerant()
                .skip(NullPointerException.class)
                .noSkip(IndexOutOfBoundsException.class)
                .skipLimit(10)
                .listener(this.customSkipListener())
                .build();
    }

    @Bean
    public Step worldStep2() {
        return stepBuilderFactory.get("worldStep2")
                .<Country, Result>chunk(10)
                .reader(this.countryReader())
                .processor(this.worldProcessor())
                .writer(this.worldWriter())
                .faultTolerant()
                .retryLimit(3)
                .retry(DeadlockLoserDataAccessException.class)
                .build();
    }

    @Bean
    public Step taskLet() {
        return stepBuilderFactory.get("taskLet")
                .tasklet(this.worldTaskLet())
                .build();
    }

    @Bean
    public Step taskLet1() {
        return stepBuilderFactory.get("taskLet1")
                .tasklet(this.doNothingTaskLet())
                .build();
    }

    @Bean
    @StepScope
    public WorldTasklet worldTaskLet() {
        return new WorldTasklet();
    }

    @Bean
    @StepScope
    public DoNothingTasklet doNothingTaskLet() {
        return new DoNothingTasklet();
    }

    @Bean("countryReader")
    @StepScope
    public JdbcCursorItemReader<Country> countryReader() {
        CustomJdbcCursorItemReader<Country> reader = new CustomJdbcCursorItemReader<>();
        String sql = "select name code, name, surfaceArea, population from country where continent = 'Asia' ";
        reader.setRowMapper(new BeanPropertyRowMapper<>(Country.class));
        reader.setDataSource(dataSource);
        return reader;
    }

    @Bean("worldProcessor")
    @StepScope
    public WorldProcessor worldProcessor() {
        return new WorldProcessor();
    }

    @Bean("worldWriter")
    @StepScope
    public WorldWriter worldWriter() {
        MethodInvokingTaskletAdapter adapter = new MethodInvokingTaskletAdapter();
        return new WorldWriter();
    }


    private CustomChunkListener customChunkListener() {
        return new CustomChunkListener();
    }

    private CustomJobListener customJobListener() {
        return new CustomJobListener();
    }

    private CustomStepLinter customStepLinter() {
        return new CustomStepLinter();
    }

    private CustomSkipListener customSkipListener() {
        return new CustomSkipListener();
    }

}


