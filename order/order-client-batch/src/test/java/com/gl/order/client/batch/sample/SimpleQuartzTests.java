package com.gl.order.client.batch.sample;

import org.junit.Assert;
import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleQuartzTests {

    @Test
    public void testSimpleQuartz() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("SimpleJob", "test").build();
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger", "test").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                    .build();
            
            
            scheduler.scheduleJob(job, trigger);
            
            Thread.sleep(6000);
            scheduler.shutdown();
        } catch (SchedulerException | InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }
}
