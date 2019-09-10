package pan.springbootkit.timer.quartz;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;

/**
 * 微信 token Quartz 定时器
 *
 * Created by panzhangbao on 2019-09-08 10:18:18
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
public class TestQuartzJob implements Job {

	/**
	 * 任务名称
	 */
	public static String JOB_NAME = "job.name.test";
	/**
	 * 任务组名
	 */
	public static String JOB_GROUP_NAME = "job.group.name.test";

	/**
	 * 动态任务触发器名称
	 */
	public static String TRIGGER_NAME = "trigger.name.test";
	/**
	 * 动态任务触发器组名
	 */
	public static String TRIGGER_GROUP_NAME = "trigger.group.name";

	/**
	 * 默认定时时间
	 */
	public static String TASK_CRON_DEFAULT = "0/1 * * * * ?";

	/**
	 * 定时任务执行方法
	 *
	 * @param jobExecutionContext
	 */
	@Override
	public void execute(JobExecutionContext jobExecutionContext){
		log.info(this.getClass() + " 定时器开始执行了");
	}
}
