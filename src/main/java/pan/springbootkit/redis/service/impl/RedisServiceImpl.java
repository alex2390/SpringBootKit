package pan.springbootkit.redis.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import pan.springbootkit.redis.service.RedisService;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * Redis serviceImpl
 *
 * Created by panzhangbao on 2019-08-11 23:29:55
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Service
public class RedisServiceImpl implements RedisService {
	@Resource
	private RedisTemplate redisTemplate;

	/**
	 * 获取 redisTemplate
	 *
	 * @param
	 * @return org.springframework.data.redis.core.RedisTemplate
	 * @date 2019-08-12 00:55
	 * @author panzhangbao
	 */
	@Override
	public RedisTemplate getRedisTemplate() {
		return redisTemplate;
	}

	/**
	 * set	如果不存在此 k，则 set
	 *
	 * @param k
	 * @param v
	 * @return java.lang.Boolean
	 * @date 2019-08-11 23:23
	 * @author panzhangbao
	 */
	@Override
	public Boolean setIfAbsent(String k, String v) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();

		return ops.setIfAbsent(k, v);
	}

	/**
	 * set
	 *
	 * @param k
	 * @param v
	 * @return void
	 * @date 2019-08-11 23:25
	 * @author panzhangbao
	 */
	@Override
	public void set(String k, String v) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		ops.set(k, v);
	}

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
	@Override
	public Boolean setIfAbsent(String k, String v, Long ttl) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		String oldV = ops.get(k);
		if (StringUtils.isBlank(oldV)) {
			ops.set(k, v, ttl, TimeUnit.SECONDS);

			return true;
		}

		return false;
	}

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
	@Override
	public void set(String k, String v, Long ttl) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();
		ops.set(k, v, ttl, TimeUnit.SECONDS);
	}

	/**
	 * get
	 *
	 * @param k
	 * @return java.lang.String
	 * @date 2019-08-11 23:24
	 *
	 * @author panzhangbao
	 */
	@Override
	public String get(String k) {
		ValueOperations<String, String> ops = redisTemplate.opsForValue();

		return ops.get(k);
	}

	/**
	 * 获取过期时间
	 *
	 * @param k
	 * @return java.lang.Long
	 * @date 2019-09-27 09:43
	 * @author panzhangbao
	 */
	@Override
	public Long getExpiredTime(String k) {
		Long ttl = redisTemplate.getExpire(k, TimeUnit.SECONDS);
		if (null == ttl || ttl <= 0) {
			return 0L;
		}

		return ttl;
	}
}
