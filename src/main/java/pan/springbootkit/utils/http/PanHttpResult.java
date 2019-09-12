package pan.springbootkit.utils.http;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Http 返回结果 实体类
 *
 * Created by panzhangbao on 2019-09-07 01:15:17
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Data
@NoArgsConstructor
 class PanHttpResult<T> {
	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 数据
	 */
	private String data;

	/**
	 * 系统错误
	 *
	 * @param
	 * @return pan.springbootkit.utils.http.PanHttpResult
	 * @date 2019-09-07 23:20
	 * @author panzhangbao
	 */
	public static PanHttpResult SYSTEM_ERROR() {
		PanHttpResult panHttpResult = new PanHttpResult();
		panHttpResult.code = 500;
		panHttpResult.data = null;

		return panHttpResult;
	}
}
