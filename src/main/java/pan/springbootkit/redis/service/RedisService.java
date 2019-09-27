package pan.springbootkit.redis.service;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * Redis service
 *
 * Created by panzhangbao on 2019-08-11 23:21:04
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public interface RedisService {

	/**
	 * 获取 redisTemplate
	 *
	 * @param
	 * @return org.springframework.data.redis.core.RedisTemplate
	 * @date 2019-08-12 00:55
	 * @author panzhangbao
	 */
	RedisTemplate getRedisTemplate();

	/**
	 * set	如果不存在此 k，则 set
	 *
	 * @param k
	 * @param v
	 * @return java.lang.Boolean
	 * @date 2019-08-11 23:23
	 * @author panzhangbao
	 */
	Boolean setIfAbsent(String k, String v);

	/**
	 * set
	 *
	 * @param k
	 * @param v
	 * @return void
	 * @date 2019-08-11 23:25
	 * @author panzhangbao
	 */
	void set(String k, String v);

	/**
	 * set	如果不存在此 k，则 set
	 *
	 * @param k
	 * @param v
	 * @param ttl
	 * @return java.lang.Boolean
	 * @date 2019-08-11 23:28
	 * @author panzhangbao
	 */
	Boolean setIfAbsent(String k, String v, Long ttl);

	/**
	 * set
	 *
	 * @param k
	 * @param v
	 * @param ttl 过期时间，单位：秒
	 * @return java.lang.Boolean
	 * @date 2019-08-11 23:24
	 * @author panzhangbao
	 */
	void set(String k, String v, Long ttl);

	/**
	 * get
	 *
	 * @param k
	 * @return java.lang.String
	 * @date 2019-08-11 23:24
	 * @author panzhangbao
	 */
	String get(String k);

	/**
	 * 获取过期时间
	 *
	 * @param k
	 * @return java.lang.Long
	 * @date 2019-09-27 09:43
	 * @author panzhangbao
	 */
	Long getExpiredTime(String k);
}
