package pan.springbootkit.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期 util
 *
 * Created by panzhangbao on 2019-07-13 01:23:19
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class PanDateUtil {

	/**
	 * 获取时间间隔 天数
	 *
	 * @param beginDate
	 * @param endDate
	 * @return java.lang.String
	 * @date 2019-07-09 16:24
	 * @author panzhangbao
	 */
	public static Long getIntevalDays(Date beginDate, Date endDate) {
		/**
		 * 参数合法性校验
		 */
		if (null == beginDate || null == endDate) {
			return null;
		}
		// 截止时间必须大于等于开始时间
		if (endDate.before(beginDate)) {
			return null;
		}

		// 这样得到的差值是微秒级别
		long diff = endDate.getTime() - beginDate.getTime();
		
		return diff / (1000 * 60 * 60 * 24);
	}

	/**
	 * 将时间戳转换为时间
	 */
	public static String timestampToDate(String s){
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long lt = new Long(s);
		Date date = new Date(lt);
		res = simpleDateFormat.format(date);
		return res;
	}

	/**
	 * 将时间转换为时间戳
	 */
	public static String dateToTimestamp(String s) {
		String res;
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = simpleDateFormat.parse(s);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long ts = date.getTime();
		res = String.valueOf(ts);
		return res;
	}


	/**
	 * UTC 转 日期
	 *
	 * @param utcDateString UTC 日期字符串 2018-12-29T10:41:44.651Z
	 * @return java.util.Date
	 * @date 2018/12/29 09:59
	 * @author panzhangbao
	 */
	public static Date utcToDate(String utcDateString) {
		// UTC是本地时间
		utcDateString = utcDateString.replace("Z", " UTC");
		// UTC 格式化
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS Z");

		try {
			return format.parse(utcDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 日期转 UTC
	 *
	 * @param date 日期字符串
	 * @return java.lang.String
	 * @date 2018/12/29 10:30
	 * @author panzhangbao
	 */
	public static String dateToUTC(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		return format.format(date);
	}

	/**
	 *  获取当前时间
	 * @return
	 */
	public static String getNowDateTime() {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return format.format(today);
	}

	public static String getToday() {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(today);
	}

	/**
	 * 年月日时分秒
	 */
	public static String getTodayYMDHMS() {
		Date today = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		return format.format(today);
	}

	/**
	 * 年月日时分秒
	 */
	public static String getYMDHMS(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
		return format.format(date);
	}

	/**
	 *  获取过去或未来 任意天内的日期
	 *
	 * @param intervalDay   间隔天数（可正负）
	 * @return
	 */
	public static String getOneDaysDate(Integer intervalDay) {
		if (intervalDay == null) {
			return getOneDaysDate(0);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + intervalDay);

		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(today);
	}

	public static String getOneDaysDateAfterSeconds(Integer seconds) {
		if (seconds == null) {
			return getOneDaysDate(0);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + seconds);

		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		return format.format(today);
	}

	/**
	 *  获取过去或未来 任意天内的日期
	 *
	 * @param intervalDay   间隔天数（可正负）
	 * @return
	 */
	public static String getOneDaysDateYMD(Integer intervalDay) {
		if (intervalDay == null) {
			return getOneDaysDate(0);
		}

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + intervalDay);

		Date today = calendar.getTime();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		return format.format(today);
	}

	/**
	 * 字符串转日期
	 *
	 * @param date
	 * @param format
	 * @return java.util.Date
	 * @date 2019-07-13 01:58
	 * @author panzhangbao
	 */
	public static  Date stringToDate(String date ,String format){
		/**
		 * 参数校验
		 */
		if (StringUtils.isBlank(date) || StringUtils.isBlank(format)) {
			return null;
		}

		try {
			return new SimpleDateFormat(format).parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 日期转字符串
	 *
	 * @param date
	 * @param format
	 * @return java.lang.String
	 * @date 2019-07-13 01:55
	 * @author panzhangbao
	 */
	public static  String dateToString(Date date ,String format){
		/**
		 * 参数校验
		 */
		if (null == date || StringUtils.isBlank(format)) {
			return null;
		}

		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * 日期转字符串
	 *
	 * @param date
	 * @return java.lang.String
	 * @date 2019-07-13 01:57
	 * @author panzhangbao
	 */
	public static  String dateToString(Date date){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

}
