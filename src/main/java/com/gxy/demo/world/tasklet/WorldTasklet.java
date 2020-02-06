package com.gxy.demo.world.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Scope;

@Scope("job")
public class WorldTasklet implements Tasklet {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("----------------------------------");
        System.out.println("this is worldTaskLet");
        System.out.println("----------------------------------");
        return RepeatStatus.FINISHED;
    }
}