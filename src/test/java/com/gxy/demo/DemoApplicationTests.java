package com.gxy.demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
class DemoApplicationTests {
    @Autowired
    @Qualifier("worldJob")
    private Job job;
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("continent", "Asia")
                    .addDate("date", new Date())
                    .toJobParameters();
            jobLauncher.run(job, jobParameters);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
