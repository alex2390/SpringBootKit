//package pan.springbootkit.timer.quartz;
//
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * quartz 定时器 controller
// *
// * Created by panzhangbao on 2019-09-08 10:20:09
// * Copyright © 2019 panzhangbao. All rights reserved.
// */
//@RestController
//@RequestMapping("api/timer/quartz")
//public class QuartzController {
//
//	@PostMapping
//	public String insert() {
//
//		/**
//		 * 校验 Job 是否重复
//		 */
//		String result = QuartzManager.checkJob(TestQuartzJob.JOB_NAME,
//				TestQuartzJob.JOB_GROUP_NAME,
//				TestQuartzJob.TRIGGER_NAME,
//				TestQuartzJob.TRIGGER_GROUP_NAME);
//		if (StringUtils.isNotBlank(result)) {
//			return result;
//		}
//
//		QuartzManager.addJob(TestQuartzJob.JOB_NAME,
//				TestQuartzJob.JOB_GROUP_NAME,
//				TestQuartzJob.TRIGGER_NAME,
//				TestQuartzJob.TRIGGER_GROUP_NAME,
//				TestQuartzJob.class,
//				TestQuartzJob.TASK_CRON_DEFAULT,
//				true);
//
//		return "SUCCESS";
//	}
//
//	@DeleteMapping
//	public String delete() {
//		/**
//		 * 校验 Job 是否重复
//		 */
//		String result = QuartzManager.checkJob(TestQuartzJob.JOB_NAME,
//				TestQuartzJob.JOB_GROUP_NAME,
//				TestQuartzJob.TRIGGER_NAME,
//				TestQuartzJob.TRIGGER_GROUP_NAME);
//		if (StringUtils.isBlank(result)) {
//			return "暂无此 Job 信息，请进行其他操作！";
//		}
//
//		QuartzManager.removeJob(TestQuartzJob.JOB_NAME,
//				TestQuartzJob.JOB_GROUP_NAME,
//				TestQuartzJob.TRIGGER_NAME,
//				TestQuartzJob.TRIGGER_GROUP_NAME);
//
//		return "SUCCESS";
//	}
//}
