package pan.springbootkit.timer.sping;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 *  Spring 自带的定时器
 *
 * Created by panzhangbao on 2018/8/9 22:42
 * Copyright ©2018  panzhangbao All rights reserved.
 **/
//@Component
//@Configurable
//@EnableScheduling
public class SpringScheduledTasks {

	/**
	 * 定时器
	 *
	 * 	 cornexpression表达式详解：
	 *
	 * 	 　　完整字段：[秒] [分] [小时] [日] [月] [周] [年]
	 *
	 * 	 　　字段　　　　　　允许值　　　　　　　　　　　　允许特殊字符
	 *
	 * 	 　　秒　　　　　　　0-59　　　　　　　　　　　　　　, - * /
	 * 	 　　分　　　　　　　0-59　　　　　　　　　　　　　　, - * /
	 * 	 　　小时　　　　　　0-23　　　　　　　　　　　　　　, - * /
	 * 	 　　日　　　　　　　1-31　　　　　　　　　　　　　　, - * ? / L W C
	 * 	 　　月　　　　　　　1-12或JAN-DEC　　　　　　　　  , - * /
	 * 	 　　周　　　　　　　1-7或SUN-SAT　　　　　　　　　 , - *  ? / L C #
	 * 	 　　年　　　　　　　留空或1970-2099　　　　　　　 　, - * /
	 *
	 *
	 * 	 * 所有值
	 * 	 ？ 不确定值
	 * 	 - 区间
	 * 	 , 多个值
	 * 	 / 递增触发	5/15表示从第5秒开始，每隔15秒触发。
	 * 	 L 表示最后的意思。 日上表示最后一天。星期上表示星期六或7。 L前加数据，表示该数据的最后一个。
	 * 	 　　　　 	星期上设置6L表示最后一个星期五。  6表示星期五
	 * 	 W表示离指定日期最近的工作日触发。15W离该月15号最近的工作日触发。
	 * 	 #表示每月的第几个周几。 6#3表示该月的第三个周五。
	 */

	 /**
     *  m1. 30 s 执行一次
     */
    @Scheduled(fixedRate = 1000 * 30)
    public void reportCurrentTime(){
//        log.info ("每30秒执行一次：" +new SimpleDateFormat ("HH:mm:ss").format (new Date()));
    }

    /**
     *  m2. 每秒执行一次
     */
    @Scheduled(cron = "0/1 * * * * * ")
    public void reportCurrentByCron(){
//        log.info ("每秒执行一次：" + new SimpleDateFormat ("HH:mm:ss").format (new Date ()));
    }

}
