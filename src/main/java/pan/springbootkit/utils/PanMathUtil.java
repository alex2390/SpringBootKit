package pan.springbootkit.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 数学 util
 *
 * Created by panzhangbao on 2019-09-12 13:14:49
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class PanMathUtil {

	/**
	 * 地球半径（单位：m）
	 */
	private static Double EARTH_RADIUS = 6378137.0;

	private static Double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 *  计算两个经纬度之间的距离
	 *
	 * @param longitude1 经度1
	 * @param latitude1 纬度1
	 * @param longitude2 经度2
	 * @param latitude2 纬度2
	 * @return  单位：米
	 */
	public static Double getDistance(Double longitude1,Double latitude1, Double longitude2, Double latitude2){
		/**
		 * 参数合法性校验
		 */
		if (null == longitude1 || null == latitude1 || null == longitude2 || null == latitude2) {
			return null;
		}

		Double a = rad(latitude1) - rad(latitude2);
		Double b = rad(longitude1) - rad(longitude2);

		Double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(rad(latitude1)) * Math.cos(rad(latitude2)) * Math.pow(Math.sin(b / 2), 2)));

		s = Double.valueOf(Math.round(s * EARTH_RADIUS));

		return s;
	}

	/**
	 * 十进制转十六进制
	 *
	 * @param n	具体的十进制数
	 * @param length 生成多少位十六进制数
	 * @return java.lang.String
	 * @date 2019-08-12 09:56
	 * @author panzhangbao
	 */
	public static String intToHexPrefix(Integer n, Integer length) {
		/**
		 * 参数合法性校验
		 */
		if (null == n) {
			return null;
		}
		if (null == length || length <= 0) {
			return null;
		}

		String result =  String.format("%x", n);
		if (result.length() < length) {
			result = PanStringUtil.appendPrefixContent(result, "0", length - result.length());
		}

		return result;
	}

	/**
	 * 十进制转十六进制
	 *
	 * @param n 具体的十进制数
	 * @param length 生成多少位十六进制数
	 * @return java.lang.String
	 * @date 2019/4/8 09:22
	 * @author panzhangbao
	 */
	public static String intToHexSuffix(Integer n, Integer length) {
		if (null == n) {
			return null;
		}
		if (null == length || length <= 0) {
			return null;
		}

		String result =  String.format("%x", n);
		if (result.length() < length) {
			result = PanStringUtil.appendSuffixContent(result, "0", length - result.length());
		}

		return result;
	}

	/**
	 * 十进制转十六进制
	 *
	 * @param n 十进制数
	 * @return java.lang.String
	 * @date 2019/4/8 09:22
	 * @author panzhangbao
	 */
	public static String intToHex(Integer n) {
		/**
		 * 参数合法性校验
		 */
		if (null == n) {
			return null;
		}

		return String.format("%x", n);
	}

	/**
	 * 十六进制转十进制
	 *
	 * @param hex
	 * @return java.lang.Integer
	 * @date 2019/4/8 09:49
	 * @author panzhangbao
	 */
	public static Integer hexToInt(String hex) {
		if (StringUtils.isBlank(hex)) {
			return null;
		}

		// 去除前缀为 0 的 十六进制
		if (hex.length() > 1 && hex.startsWith("0")) {
			hex = hex.substring(1);
		}

		return Integer.valueOf(String.valueOf(Long.parseLong(hex, 16)));
	}

	/**
	 * 字符串转化成为16进制字符串
	 *
	 * @param s
	 * @return
	 */
	public static String stringToHexString(String s) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(s)) {
			return null;
		}

		String str = "";
		for (int i = 0; i < s.length(); i++) {
			int ch = (int) s.charAt(i);
			String s4 = Integer.toHexString(ch);
			str = str + s4;
		}

		return str;
	}

	/**
	 * 判断是否为整数
	 *
	 * @param s 传入的字符串
	 * @return 是整数返回true,否则返回false
	 */
	public static Boolean isInteger(String s) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(s)) {
			return false;
		}

		String regex = "^[-\\+]?[\\d]*$";

		return s.matches(regex);
	}

	/**
	 * 判断是否为十六进制数
	 *
	 * @param s
	 * @return java.lang.Boolean
	 * @date 2019-09-12 13:41
	 * @author panzhangbao
	 */
	public static Boolean isHex(String s) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(s)) {
			return false;
		}

		String regex = "^[A-Fa-f0-9]+$";

		return s.matches(regex);
	}
}
