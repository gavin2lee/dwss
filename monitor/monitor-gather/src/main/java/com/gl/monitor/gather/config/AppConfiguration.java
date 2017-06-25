package com.gl.monitor.gather.config;

import java.util.HashMap;
import java.util.Map;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import com.gl.monitor.gather.job.ComputerInfoGatherJob;
import com.gl.monitor.gather.job.ComputerInfoGatherJobFactory;
import com.gl.monitor.gather.job.SimpleComputerInfoGatherJob;
import com.gl.monitor.gather.service.ComputerInfoInternalSender;
import com.gl.monitor.gather.worker.ComputerInfoSender;

@Configuration
@ComponentScan(basePackages = { "com.gl.monitor.gather" })
public class AppConfiguration {

	@Bean
	public JobDetailFactoryBean getJobDetailFactoryBean() {
		JobDetailFactoryBean factoryBean = new JobDetailFactoryBean();
		factoryBean.setJobClass(ComputerInfoGatherJob.class);
		Map<String, Object> jobDataAsMap = new HashMap<String, Object>();
		jobDataAsMap.put("timeout", 10);

		return factoryBean;
	}

	@Bean
	public JobDetail getJobDetail() {
		return getJobDetailFactoryBean().getObject();
	}
	
	@Bean
	public ComputerInfoGatherJob getComputerInfoGatherJob(){
		ComputerInfoGatherJob job = new ComputerInfoGatherJob();
		job.setTimeout(10);
		return job;
	}
	
	@Bean
	public ComputerInfoGatherJobFactory getComputerInfoGatherJobFactory(){
		ComputerInfoGatherJobFactory f = new ComputerInfoGatherJobFactory();
		ComputerInfoGatherJob jobDetail = getComputerInfoGatherJob();
		jobDetail.setGather(f.getGather());
		
		f.setJobDetail(jobDetail);
		return f;
	}
	
	@Bean
	public SharedObjectsHolder getObjectHolder(){
		SharedObjectsHolder holder = new SharedObjectsHolder();
		return holder;
	}
	
	@Bean
	public ComputerInfoInternalSender getComputerInfoInternalSender(){
		SharedObjectsHolder holder = getObjectHolder();
		ComputerInfoInternalSender sender = new ComputerInfoInternalSender(holder.getInfoQueue());
		
		return sender;
	}
	
	@Bean
	public SimpleComputerInfoGatherJob getSimpleComputerInfoGatherJob(){
		SimpleComputerInfoGatherJob job = new SimpleComputerInfoGatherJob();
		job.setTimeout(100);
		
		return job;
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean getMethodInvokingJobDetailFactoryBean(){
		MethodInvokingJobDetailFactoryBean fb = new MethodInvokingJobDetailFactoryBean();
		fb.setTargetObject(getSimpleComputerInfoGatherJob());
		fb.setTargetMethod("execute");
		
		return fb;
	}
	
	@Bean
	public JobDetail getJobDetailFromMethod(){
		return getMethodInvokingJobDetailFactoryBean().getObject();
	}

	@Bean
	public SimpleTriggerFactoryBean getSimpleTriggerFactoryBean() {
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(getJobDetailFromMethod());
		trigger.setStartDelay(1000);
		trigger.setRepeatInterval(5000);
		return trigger;
	}
	

	@Bean
	public CronTriggerFactoryBean getCronTriggerFactoryBean() {
		CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
		trigger.setJobDetail(getJobDetailFromMethod());
		trigger.setCronExpression("0 0 6 * * ?");

		return trigger;
	}
	
	@Bean
	public SimpleTrigger getSimpleTrigger(){
		return getSimpleTriggerFactoryBean().getObject();
	}
	
	@Bean
	public CronTrigger getCronTrigger(){
		return getCronTriggerFactoryBean().getObject();
	}

	@Bean
	public SchedulerFactoryBean getSchedulerFactoryBean() {
		SchedulerFactoryBean scheduler = new SchedulerFactoryBean();
		scheduler.setTriggers(getSimpleTrigger(), getCronTrigger());

		return scheduler;
	}
	
	@Bean
	public ComputerInfoSender getComputerInfoSender(){
		return new ComputerInfoSender();
	}
}
