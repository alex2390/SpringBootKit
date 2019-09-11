package pan.springbootkit.generalmapper.base;

/**
 * 封装的基类 枚举
 *
 * Created by panzhangbao on 2018-12-25 13:03:30
 * Copyright © 2018 panzhangbao. All rights reserved.
 */
public enum BaseResultEnum {
	SYSTEM_ERROR(-1, "系统错误"),
	SUCCESS(0, "成功"),
	FAILURE(1, "失败"),
	NEEDED_PARAMETER(2, "参数必填"),
	ILLEGAL_PARAMETER(3, "参数不合法"),
	;

	private Integer value;
	private String mark;

	BaseResultEnum(Integer value, String mark){
		this.value = value;
		this.mark = mark;

	}

	public Integer getValue() {
		return value;
	}
	public String getMark() {
		return mark;
	}
}
