package com.gxy.demo.world.lintener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class CustomStepLinter implements StepExecutionListener {
    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("----------------------------------");
        System.out.println("this is before StepExecutionListener");
        System.out.println("----------------------------------");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        String res = stepExecution.getJobParameters().getString("res");
        if (res == null) {
            return ExitStatus.FAILED;
        } else if (res.equals("failed")) {
            return ExitStatus.FAILED;
        }
        return ExitStatus.COMPLETED;
    }
}

