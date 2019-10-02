package pan.springbootkit.token.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pan.springbootkit.redis.RedisConstant;
import pan.springbootkit.redis.service.RedisService;
import pan.springbootkit.token.service.TokenService;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * token serviceImpl
 *
 * Created by panzhangbao on 2019-10-02 09:28:06
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Service
public class TokenServiceImpl implements TokenService {

	@Resource
	private RedisService redisService;

	/**
	 * 根据 id 创建 token
	 *
	 * @param id
	 * @return java.lang.String
	 * @date 2019-10-02 10:10
	 * @author panzhangbao
	 */
	@Override
	public String createToken(Integer id) {
		if (null == id || 0 >= id) {
			return "";
		}

		String token = createToken();

		/**
		 * 查询 token id 对应的 token
		 */
		String idKey = new StringBuilder().append(RedisConstant.TOKEN_ID_KEY).append(id).toString();
		String idValue = redisService.get(idKey);
		if (StringUtils.isNotBlank(idValue)) {
			/**
			 * 如果有老 token，删之
			 */
			redisService.delete(new StringBuilder().append(RedisConstant.TOKEN_TOKEN_KEY).append(idValue).toString());
		}

		redisService.set(idKey, token, RedisConstant.TOKEN_EXPIRED_TIME);

		return token;
	}

	/**
	 * 创建 64 位字符串的 token
	 *
	 * @param
	 * @return java.lang.String
	 * @date 2019-10-02 09:46
	 * @author panzhangbao
	 */
	private String createToken(){
		StringBuilder s = new StringBuilder()
				.append(UUID.randomUUID().toString().replaceAll("-",""))
				.append(UUID.randomUUID().toString().replaceAll("-",""));

		return s.toString();
	}

	/**
	 * 根据 token 校验 token
	 *
	 * @param token
	 * @return java.lang.Boolean
	 * @date 2019-10-02 10:10
	 * @author panzhangbao
	 */
	@Override
	public Boolean checkToken(String token) {
		if (StringUtils.isBlank(token)) {
			return false;
		}

		String tokenKey = new StringBuilder().append(RedisConstant.TOKEN_TOKEN_KEY).append(token).toString();
		if (StringUtils.isBlank(redisService.get(tokenKey))) {
			return false;
		}

		return true;
	}

	/**
	 * 删除 token
	 *
	 * @param id
	 * @return void
	 * @date 2019-10-02 10:16
	 * @author panzhangbao
	 */
	@Override
	public void deleteToken(Integer id) {
		// id key
		String idKey = new StringBuilder().append(RedisConstant.TOKEN_ID_KEY).append(id).toString();

		/**
		 * 删除 token
		 */
		String idValue = redisService.get(idKey);
		if (StringUtils.isNotBlank(idValue)) {
			// token key
			String tokenKey = new StringBuilder().append(RedisConstant.TOKEN_TOKEN_KEY).append(idValue).toString();
			redisService.delete(tokenKey);
		}

		/**
		 * 删除 id
		 */
		redisService.delete(idKey);
	}
}
