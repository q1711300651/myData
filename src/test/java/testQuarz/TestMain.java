package testQuarz;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.io.IOException;
import java.text.ParseException;

/**
 * Created by ww on 17/3/7.
 */
public class TestMain {
    @Test
    public void testQuarz() throws SchedulerException, ParseException, IOException {
        Scheduler scheduler = new StdSchedulerFactory().getDefaultScheduler();
        JobKey jobKey = new JobKey("job1", "group1");
        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity(jobKey).build();
        String cronExpression = "0/5 * * * * ?";
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();
        scheduler.getListenerManager().addJobListener(new HelloJobListener(), KeyMatcher.keyEquals(jobKey));
        scheduler.start();
        scheduler.scheduleJob(jobDetail, trigger);
//        System.in.read();
    }
}
