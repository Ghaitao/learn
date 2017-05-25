package com.ght.learn.quartz.hello;

import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzExample {
	public static void main(String[] args) throws Exception {
		/*QuartzExample qe=new QuartzExample();
		qe.run();*/
		try {  
            JobDetail jobDetail=JobBuilder.newJob(HelloJob.class)
            							  .withIdentity("myjob", "job-group")
            							  .usingJobData("name","高海涛")
            							  .build();  
            CronTrigger cronTrigger=TriggerBuilder.newTrigger()
            												.withIdentity("cronTrigger", "trigger-group")
            												.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?")).build();  
            System.out.println(cronTrigger);
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger","job-group")
            											 .startNow()
            											 .withSchedule(
            													 SimpleScheduleBuilder
            													 .simpleSchedule()
            													 .withIntervalInSeconds(5)
            													 .withRepeatCount(3)).build();
            SchedulerFactory sFactory=new StdSchedulerFactory();  
            Scheduler scheduler=sFactory.getScheduler();  
            //scheduler.scheduleJob(jobDetail, cronTrigger);  
            scheduler.scheduleJob(jobDetail, trigger);  
            scheduler.start();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

	public void run() throws Exception{
		System.out.println("asdsad");
		Logger log=LoggerFactory.getLogger(QuartzExample.class);
		log.info("--------Initializing 初始化--------");
		
		SchedulerFactory sf=new StdSchedulerFactory();
		
		Scheduler sched=sf.getScheduler();
		log.info("---------Initializing complete 初始化完成------------");
		
		Date runTime=DateBuilder.evenMinuteDate(new Date());
		log.info("Scheduling job");
		
		JobDetail job=JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();
		Trigger trigger=TriggerBuilder.newTrigger().withIdentity("trigger1", "group1").startAt(runTime).build();
		
		sched.scheduleJob(job, trigger);
		System.out.println(job.getKey()+"will run at:"+runTime);
		log.info(job.getKey()+"will run at:"+runTime);
		
		sched.start();
		log.info("------- Started Scheduler -----------------");  
		  
	    log.info("------- Waiting 65 seconds... -------------"); 
	    try {  
	        // wait 65 seconds to show job  
	        Thread.sleep(65L * 1000L);  
	        // executing...  
	      } catch (Exception e) {  
	        //  
	      }  
	    
	      // shut down the scheduler  
	      log.info("------- Shutting Down ---------------------");  
	      // 8、通过Scheduler销毁内置的Trigger和Job  
	      sched.shutdown(true);  
	      log.info("------- Shutdown Complete -----------------");
		
	}
}
