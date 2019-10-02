package pan.springbootkit.generalmapper.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 封装的基类 枚举
 *
 * Created by panzhangbao on 2018-12-25 13:03:30
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
@Getter
@AllArgsConstructor
public enum BaseResultEnum {
	SYSTEM_ERROR(-1, "系统错误"),
	SUCCESS(0, "成功"),
	FAILURE(1, "失败"),
	TOKEN_EXPIRED(2, "token 过期，请重新登录"),
	HAVE_NO_AUTH(3, "没有权限"),
	ILLEGAL_PARAMETER(4, "参数不合法"),
	;

	private Integer value;
	private String mark;
}
