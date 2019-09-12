package pan.springbootkit.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 手机号 util
 *
 * Created by panzhangbao on 2019-09-12 13:30:50
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
public class PanPhoneNumberUtil {

	/**
	 * 获取加密的手机号
	 *
	 * @param phoneNumber
	 * @return java.lang.String
	 * @date 2018/11/7 11:31
	 * @author panzhangbao
	 */
	public static String getSecretedPhoneNumber(String phoneNumber) {
		/**
		 * 参数合法性校验
		 */
		if (StringUtils.isBlank(phoneNumber)) {
			return "";
		}
		if (phoneNumber.length() != 11) {
			return "";
		}
		if (checkPhone(phoneNumber)) {
			return "";
		}

		// 手机号中间四位数加 *
		StringBuffer phoneNumBuffer = new StringBuffer()
				.append(phoneNumber.substring(0, 3))
				.append("****")
				.append(phoneNumber.substring(7, 11));

		return phoneNumBuffer.toString();
	}

	/**
	 * 校验手机号
	 *
	 *  2019年1月16日已知
	 中国电信号段
	 133,149,153,173,174,177,180,181,189,199
	 中国联通号段
	 130,131,132,145,146,155,156,166,175,176,185,186
	 中国移动号段
	 134(0-8),135,136,137,138,139,147,148,150,151,152,157,158,159,165,178,182,183,184,187,188,198
	 上网卡专属号段（用于上网和收发短信，不打打电话）
	 如中国联通的是145
	 虚拟运营商
	 电信：1700,1701,1702
	 移动：1703,1705,1706
	 联通：1704,1707,1708,1709,171
	 卫星通信： 1349 <br>　　　　　未知号段：141、142、143、144、154
	 *
	 *
	 * @param phoneNumber
	 * @return java.lang.Boolean
	 * @date 2019/1/16 21:44
	 * @author panzhangbao
	 */
	public static Boolean checkPhone(String phoneNumber) {
		/**
		 * 参数合法想校验
		 */
		if (StringUtils.isBlank(phoneNumber)) {
			return false;
		}

		String regex = "^[1](([3|5|8][\\d])|([4][5,6,7,8,9])|([6][5,6])|([7][3,4,5,6,7,8])|([9][8,9]))[\\d]{8}$";

		return phoneNumber.matches(regex);
	}
}
