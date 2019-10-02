package pan.springbootkit.redis;

import pan.springbootkit.constant.TimeConstant;

/**
 * redis 常量
 *
 * Created by panzhangbao on 2019-10-02 09:51:14
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class RedisConstant {
	/**
	 * 小数点
	 */
	public static String DOT = ".";

	/**
	 * token id key
	 */
	public static String TOKEN_ID_KEY = "token.id.";

	/**
	 * token token key
	 */
	public static String TOKEN_TOKEN_KEY = "token.token.";

	/**
	 * token 过期时间 1 天
	 */
	public static Long TOKEN_EXPIRED_TIME = TimeConstant.DAY;
}
