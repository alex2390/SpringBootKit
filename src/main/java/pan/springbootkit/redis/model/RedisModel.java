package pan.springbootkit.redis.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Redis model
 *
 * Created by panzhangbao on 2019-08-12 00:01:01
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
public class RedisModel implements Serializable {

	private static final long serialVersionUID = 6586303937119695053L;

	/**
	 * key
	 */
	private String k;

	/**
	 * value
	 */
	private String v;

	/**
	 * 过期时间，单位：秒
	 */
	private Long ttl;
}
