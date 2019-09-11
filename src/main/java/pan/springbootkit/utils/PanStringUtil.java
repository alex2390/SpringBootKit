package pan.springbootkit.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * String util
 *
 * Created by panzhangbao on 2019-09-11 08:57:56
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class PanStringUtil {

	/**
	 * 字符串转列表
	 *
	 * @param s
	 * @return java.util.List<java.lang.String>
	 * @date 2019-09-11 09:11
	 * @author panzhangbao
	 */
	public static List<String> stringToList(String s) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(s)) {
			return null;
		}

		return stringToList(s, null);
	}

	/**
	 * 字符串转列表
	 *
	 * @param s
	 * @param regex 分割规则，默认为逗号
	 * @return java.util.List<java.lang.String>
	 * @date 2019-09-11 09:11
	 * @author panzhangbao
	 */
	public static List<String> stringToList(String s, String regex) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(s)) {
			return null;
		}

		/**
		 * 默认逗号隔开
		 */
		if (StringUtils.isBlank(regex)) {
			regex = ",";
		}

		/**
		 * 去除首尾空格
		 */
		String blankString = " ";
		while (s.startsWith(blankString)) {
			s = s.substring(1);
		}
		while (s.endsWith(blankString)) {
			s = s.substring(0, s.length() -1);
		}

		/**
		 * 返回结果列表
		 */
		List<String> resultList = new ArrayList<>();

		/**
		 * 只有单个元素
		 */
		if (!s.contains(regex)) {
			resultList.add(s);
		}

		String[] strings = s.split(regex);
		for (String e : strings) {
			resultList.add(e);
		}

		return resultList;
	}
}
