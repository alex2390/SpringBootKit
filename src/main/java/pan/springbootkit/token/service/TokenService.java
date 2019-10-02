package pan.springbootkit.token.service;

/**
 * token service
 *
 * Created by panzhangbao on 2019-10-02 10:17:02
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public interface TokenService {
	/**
	 * 根据 id 创建 token
	 *
	 * @param id
	 * @return java.lang.String
	 * @date 2019-10-02 10:10
	 * @author panzhangbao
	 */
	String createToken(Integer id);

	/**
	 * 根据 token 校验 token
	 *
	 * @param token
	 * @return java.lang.Boolean
	 * @date 2019-10-02 10:10
	 * @author panzhangbao
	 */
	Boolean checkToken(String token);

	/**
	 * 删除 token
	 *
	 * @param id
	 * @return void
	 * @date 2019-10-02 10:16
	 * @author panzhangbao
	 */
	void deleteToken(Integer id);
}
