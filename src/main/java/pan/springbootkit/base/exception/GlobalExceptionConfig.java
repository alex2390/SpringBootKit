package pan.springbootkit.base.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pan.springbootkit.base.BaseResult;

import java.text.ParseException;

/**
 * 统一异常处理
 *
 * Created by panzhangbao on 2019-10-02 11:33:19
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
@ControllerAdvice
@ResponseBody
public class GlobalExceptionConfig {

	@ExceptionHandler
	public BaseResult handleException(Exception e) {
		log.error(e.toString());

		/**
		 * 空指针异常
		 */
		if (NullPointerException.class.equals(e.getClass())) {
			return BaseResult.systemError("空指针异常");
		}

		/**
		 * 数组下标越界异常
		 */
		if (ArrayIndexOutOfBoundsException.class.equals(e.getClass())) {
			return BaseResult.systemError("数组下标越界异常");
		}

		/**
		 * 数字格式化异常
		 */
		if (NumberFormatException.class.equals(e.getClass())) {
			return BaseResult.systemError("数字格式化异常");
		}

		/**
		 * 解析异常
		 */
		if (ParseException.class.equals(e.getClass())) {
			return BaseResult.systemError("解析异常");
		}

		/**
		 * 类型转换异常
		 */
		if (ClassCastException.class.equals(e.getClass())) {
			return BaseResult.systemError("类型转换异常");
		}

		/**
		 * 数学运算异常
		 */
		if (ArithmeticException.class.equals(e.getClass())) {
			return BaseResult.systemError("数学运算异常");
		}

		return BaseResult.systemError("");
	}
}