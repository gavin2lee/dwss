package com.gl.order.client.batch.job;

import org.quartz.JobExecutionContext;
import org.quartz.Trigger;
import org.quartz.Trigger.CompletedExecutionInstruction;
import org.quartz.TriggerListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTriggerListener implements TriggerListener {
    private static final Logger log = LoggerFactory.getLogger(SimpleTriggerListener.class);

    @Override
    public String getName() {
        return SimpleTriggerListener.class.getSimpleName();
    }

    @Override
    public void triggerFired(Trigger trigger, JobExecutionContext context) {
        log.info("triggerFired");

    }

    @Override
    public boolean vetoJobExecution(Trigger trigger, JobExecutionContext context) {
        log.info("vetoJobExecution");
        return true;
    }

    @Override
    public void triggerMisfired(Trigger trigger) {
        log.info("triggerMisfired");
    }

    @Override
    public void triggerComplete(Trigger trigger, JobExecutionContext context,
            CompletedExecutionInstruction triggerInstructionCode) {
        log.info("triggerComplete");
    }

}
