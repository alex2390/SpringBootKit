package pan.springbootkit.timer.quartz;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * quartz 定时器 controller
 *
 * Created by panzhangbao on 2019-09-08 10:20:09
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/timer/quartz")
public class QuartzController {

	@PostMapping
	public String insert() {
		QuartzManager.addJob(WechatTokenQuartzJob.JOB_NAME,
				WechatTokenQuartzJob.JOB_GROUP_NAME,
				WechatTokenQuartzJob.TRIGGER_NAME,
				WechatTokenQuartzJob.TRIGGER_GROUP_NAME,
				WechatTokenQuartzJob.class,
				WechatTokenQuartzJob.TASK_CRON_DEFAULT,
				true);

		return "SUCCESS";
	}

	@DeleteMapping
	public String delete() {
		QuartzManager.removeJob(WechatTokenQuartzJob.JOB_NAME,
				WechatTokenQuartzJob.JOB_GROUP_NAME,
				WechatTokenQuartzJob.TRIGGER_NAME,
				WechatTokenQuartzJob.TRIGGER_GROUP_NAME);

		return "SUCCESS";
	}
}
