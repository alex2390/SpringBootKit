package pan.springbootkit.constant;

/**
 * Time constant
 *
 * Created by panzhangbao on 2019-03-07 13:41:54
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class TimeConstant {
	/**
	 * 秒
	 */
	public static final Long SECOND = 1L;
	/**
	 * 半分钟
	 */
	public static final Long HALF_MINUTE = 30 * SECOND;
	/**
	 * 1 分钟
	 */
	public static final Long MINUTE = 2 * HALF_MINUTE;
	/**
	 * 半小时
	 */
	public static final Long HALF_HOUR = 30 * MINUTE;
	/**
	 * 1 小时
	 */
	public static final Long HOUR = 2 * HALF_HOUR;
	/**
	 * 半天
	 */
	public static final Long HALF_DAY = 12 * HOUR;
	/**
	 * 1 天
	 */
	public static final Long DAY = 2 * HALF_DAY;
	/**
	 * 1 周
	 */
	public static final Long WEEK = 7 * DAY;
	/**
	 * 半月
	 */
	public static final Long HALF_MONTH = 15 * DAY;
	/**
	 * 1 月
	 */
	public static final Long MONTH = 2 * HALF_MONTH;
	/**
	 * 半年
	 */
	public static final Long HALF_YEAR = 6 * MONTH;
	/**
	 * 1 年，365 天
	 */
	public static final Long YEAR = 365 * DAY;
}
