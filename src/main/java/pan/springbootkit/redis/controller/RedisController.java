package pan.springbootkit.redis.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;
import pan.springbootkit.constant.*;
import pan.springbootkit.redis.model.RedisModel;
import pan.springbootkit.redis.service.RedisService;

import javax.annotation.Resource;

/**
 * Redis controller
 *
 * Created by panzhangbao on 2019-08-11 23:47:22
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@RestController
@RequestMapping("api/redis")
public class RedisController {
	@Resource
	private RedisService redisService;

	/**
	 * set
	 *
	 * @param redisModel
	 * @return java.lang.Boolean
	 * @date 2019-08-11 23:23
	 * @author panzhangbao
	 */
	@PostMapping("set")
	Boolean set(@RequestBody RedisModel redisModel) {
		if (null == redisModel || StringUtils.isBlank(redisModel.getK()) || StringUtils.isBlank(redisModel.getV())) {
			return false;
		}

		return redisService.setIfAbsent(redisModel.getK(), redisModel.getV(), TimeConstant.MINUTE);
	}

	/**
	 * get
	 *
	 * @param k
	 * @return java.lang.String
	 * @date 2019-08-11 23:24
	 * @author panzhangbao
	 */
	@GetMapping("get")
	String get(@RequestParam("k") String k) {
		return redisService.get(k);
	}

}
