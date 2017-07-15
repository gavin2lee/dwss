package com.gl.order.client.batch.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJobListener implements JobListener {
    private static final Logger log = LoggerFactory.getLogger(SimpleJobListener.class);

    @Override
    public String getName() {
        return "SimpleJobListener";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext context) {
        Job job = context.getJobInstance();
        log.info(String.format("%s to be executed", job.getClass().getSimpleName()));
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext context) {
        Job job = context.getJobInstance();
        log.info(String.format("%s vetoed ", job.getClass().getSimpleName()));
    }

    @Override
    public void jobWasExecuted(JobExecutionContext context, JobExecutionException jobException) {
        Job job = context.getJobInstance();
        log.info(String.format("%s was executed", job.getClass().getSimpleName()));
    }

}
