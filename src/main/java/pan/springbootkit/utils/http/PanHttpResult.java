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
public class PanHttpResult<T> {
	/**
	 * 状态码
	 */
	private Integer code;

	/**
	 * 数据
	 */
	private String data;
}
