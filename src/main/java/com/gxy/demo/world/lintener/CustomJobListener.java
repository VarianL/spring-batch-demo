package com.gxy.demo.world.lintener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class CustomJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("----------------------------------");
        System.out.println("this is before job JobExecutionListener");
        System.out.println("----------------------------------");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("----------------------------------");
        System.out.println("this is after job JobExecutionListener");
        System.out.println("----------------------------------");
    }
}
