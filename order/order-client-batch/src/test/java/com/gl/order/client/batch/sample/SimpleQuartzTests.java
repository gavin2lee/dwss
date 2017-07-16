package com.gl.order.client.batch.sample;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

import com.gl.order.client.batch.job.SimpleJob;
import com.gl.order.client.batch.job.SimpleJobListener;
import com.gl.order.client.batch.job.SimpleTriggerListener;

@Ignore
public class SimpleQuartzTests {

    @Test
    public void testSimpleQuartz() {
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

            scheduler.start();

            JobDetail job = JobBuilder.newJob(SimpleJob.class).withIdentity("SimpleJob3", "test").build();
            job.getJobDataMap().put("Author", "Gavin Li");

            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("simpleTrigger3", "test").startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                    .build();

            scheduler.scheduleJob(job, trigger);
            scheduler.getListenerManager().addJobListener(new SimpleJobListener());
            scheduler.getListenerManager().addTriggerListener(new SimpleTriggerListener());

            Thread.sleep(6000 * 1000);
            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
