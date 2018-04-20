package quartz;

import org.quartz.*;

import java.util.Date;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.dateOf;
import static org.quartz.DateBuilder.futureDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.JobKey.jobKey;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * @Author:LXF
 * @Date:Created in 15:52 2018/3/19
 * @Description:
 */
public class QuartzConsole {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory factory=new org.quartz.impl.StdSchedulerFactory();
        Scheduler scheduler=factory.getScheduler();//通过SchedulerFactory实例化Scheduler对象
        if (scheduler.checkExists(jobKey("simpleTriggerJob", "group1"))) {// 检查调度器中是否已经存在任务
            scheduler.deleteJob(jobKey("simpleTriggerJob", "group1"));// 调度器中存在任务则删除任务
        }
        scheduler.start();
        String corns[] = {"01,07,09 25 17 27 3 ?","2 21 17 27 3 ?","0 22 17 27 03 ?"};
        int i = 0;
        for (String corn : corns){
            i++;
            JobDetail job =newJob(HelloJob.class)//指定Job的运行类
                    .withIdentity("simpleTriggerJob"+i,"group1")
                    .build();// name "myJob", group "group1"两个变量作为一个job的key

            Trigger trigger=newTrigger()
                    .withIdentity("simpleTrigger"+i,"group1")
                    .withSchedule(cronSchedule(corn))
                    .forJob("simpleTriggerJob"+i,"group1")
                    .build();
            scheduler.scheduleJob(job,trigger);
        }












        /* JobDetail job=newJob(HelloJob.class)//指定Job的运行类
                .withIdentity("simpleTriggerJob","group1")
                .build();// name "myJob", group "group1"两个变量作为一个job的key
         JobDetail job2=newJob(HelloJob.class)//指定Job的运行类
                .withIdentity("simpleTriggerJob2","group1")
                .build();// name "myJob", group "group1"两个变量作为一个job的key
        Trigger trigger=newTrigger()
                .withIdentity("simpleTrigger","group1")
                .withSchedule(cronSchedule(corn))
                .forJob("simpleTriggerJob","group1")
                .build();
        Trigger trigger2=newTrigger()
                .withIdentity("simpleTrigger2","group1")
                .withSchedule(cronSchedule(corn))
                .forJob("simpleTriggerJob2","group1")
                .build();
        scheduler.scheduleJob(job,trigger);
        scheduler.scheduleJob(job2,trigger2);*/
    }


}
