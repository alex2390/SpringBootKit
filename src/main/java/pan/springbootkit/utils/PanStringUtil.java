package pan.springbootkit.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

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
	 * 添加前缀内容
	 *
	 * @param s
	 * @param content
	 * @param length
	 * @return java.lang.String
	 * @date 2019-08-12 09:53
	 * @author panzhangbao
	 */
	public static String appendPrefixContent(String s, String content, Integer length) {

		if (null == length || length <= 0) {
			return null;
		}

		for (int i = 0; i < length; i ++) {
			s = content + s;
		}

		return s;
	}

	/**
	 * 添加后缀内容
	 *
	 * @param s
	 * @param content
	 * @param length
	 * @return java.lang.String
	 * @date 2019-08-12 09:56
	 * @author panzhangbao
	 */
	public static String appendSuffixContent(String s, String content, Integer length) {
		if (null == length || length <= 0) {
			return null;
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < length; i ++) {
			sb.append(content);
		}

		return s + sb.toString();
	}

	/**
	 *  字符串列表转字符串
	 *
	 * @author Jason Pan
	 * @generatedDate: 2018/10/9 17:25
	 * @param stringList 要转换的字符串列表
	 * @return
	 */
	public static String listToString(List<String> stringList) {
		return listToString(stringList, null);
	}

	/**
	 *  字符串列表转字符串
	 *
	 * @author Jason Pan
	 * @generatedDate: 2018/10/9 17:25
	 * @param stringList 要转换的字符串列表
	 * @return
	 */
	public static String listToString(List<String> stringList, String regex) {
		// 参数合法性校验
		if (CollectionUtils.isEmpty(stringList)) {
			return null;
		}
		if (StringUtils.isBlank(regex)) {
			regex = ",";
		}

		// List to String
		String result = "";
		for (String s : stringList) {
			result += s + regex;
		}
		result = result.substring(0, result.length() - 1);

		// 返回结果
		return result;
	}

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

	/**
	 * 过滤逗号
	 * @param s
	 * @return
	 */
	public static String filterCommaString(String s) {
		// 数据为空校验
		if (StringUtils.isEmpty(s)) {
			return null;
		}

		// 去除 并列逗号
		s = s.replace(",,", ",");

		// 去除 首逗号
		if (s.startsWith(",")) {
			s = s.substring(1, s.length() - 1);
		}

		// 去除 尾逗号
		if (s.endsWith(",")) {
			s = s.substring(0, s.length() - 1);
		}

		return s;
	}
}
