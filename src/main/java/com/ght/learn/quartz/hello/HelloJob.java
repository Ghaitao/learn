package com.ght.learn.quartz.hello;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {

	//private static Logger _log=LoggerFactory.getLogger(HelloJob.class);
	
	/**
	 * Job,需要一个公有的构造函数,否则factory无法创建
	 */
	public HelloJob(){
		
	}
	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		/*System.out.println(HelloJob.class.getName());
		_log.info("Hello quartz"+new Date());*/
		JobDataMap jobDataMap=arg0.getJobDetail().getJobDataMap();
		System.out.println("hello "+jobDataMap.getString("name")+", "+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
        //输出1-10  
       /* for(int i=1;i<=10;i++){  
            System.out.println(" | "+i+" ");  
            try {  
                Thread.sleep(1000);  
            } catch (InterruptedException e) {  
                // TODO: handle exception  
            }  
        }  */
        /*System.out.println("<-See  I did it.");  
        JobDataMap properties=arg0.getJobDetail().getJobDataMap();  
        System.out.println("Previous fire time: "+arg0.getPreviousFireTime());  
        System.out.println("curent file time: "+arg0.getFireTime());  
        System.out.println("next fire time: "+arg0.getNextFireTime());  */
	}

}
