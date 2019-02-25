package com.github.byrage.springbatchsample.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class SimpleJobConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public SimpleJobConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {

        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job simpleJob() {

        return jobBuilderFactory.get("simpleJob")
                                .start(simpleStep1(null))
                                .next(simpleStep2(null))
                                .build();
    }

    @Bean
    @JobScope
    public Step simpleStep1(@Value("#{jobParameters[requestDate]}") String requestDate) {

        return stepBuilderFactory.get("simpleStep1")
                                 .tasklet((contribution, chunkContext) -> {
                                     log.info(">>>> This is simpleStep1");
                                     log.info(">>>> requestDate={}", requestDate);
                                     return RepeatStatus.FINISHED;
                                 }).build();
    }

    @Bean
    @JobScope
    public Step simpleStep2(@Value("#{jobParameters[requestDate]}") String requestDate) {

        return stepBuilderFactory.get("simpleStep2")
                                 .tasklet((contribution, chunkContext) -> {
                                     log.info(">>>> This is simpleStep2");
                                     log.info(">>>> requestDate={}", requestDate);
                                     return RepeatStatus.FINISHED;
                                 })
                                 .build();
    }
}
