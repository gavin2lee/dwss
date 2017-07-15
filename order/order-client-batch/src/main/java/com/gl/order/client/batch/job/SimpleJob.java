package com.gl.order.client.batch.job;

import java.util.concurrent.atomic.AtomicLong;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleJob implements Job{
    private static final Logger log = LoggerFactory.getLogger(SimpleJob.class);
    private AtomicLong count = new AtomicLong();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        log.info("EXECUTE start >>> ");
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        for(String k : jobDataMap.keySet()){
            log.info(String.format("%s - %s - %s", k, jobDataMap.get(k), count.incrementAndGet()));
        }
        log.info("EXECUTE end <<< ");
    }
    
    

}
