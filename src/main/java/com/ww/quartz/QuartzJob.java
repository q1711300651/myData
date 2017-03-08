package com.ww.quartz;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

/**
 * Created by ww on 17/1/3.
 */
public class QuartzJob implements Job {

    Logger log = Logger.getLogger( QuartzJob.class );

//    public void execute(){
//        long ms = System.currentTimeMillis();
//        System.out.println("\t\t" + new Date(ms));
//        log.info("Hello quartz");
//    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        long ms = System.currentTimeMillis();
        System.out.println("\t\t" + new Date(ms));
        log.info("Hello quartz");
    }
}
