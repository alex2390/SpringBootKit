package pan.springbootkit.utils.algorithm.encryptdecrypt;

import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;

/**
 * 加密解密 MD5 util
 *
 * Created by panzhangbao on 2019-09-12 14:02:02
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
public class PanAlgorithmMD5Util {
	/**
	 * 盐值
	 */
	public static final String solt = "afjiefe*#^@wuehe823d@#@%#&@wefffewf";

	/**
	 * MD5加码 生成32位md5码
	 *
	 * @param inStr
	 * @return java.lang.String
	 * @date 2019-09-12 13:53
	 * @author panzhangbao
	 */
	public static String string2MD5(String inStr){
		MessageDigest md5 = null;
		try{
			md5 = MessageDigest.getInstance("MD5");
		}catch (Exception e){
			log.info(e.toString());
			e.printStackTrace();
			return "";
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++) {
			byteArray[i] = (byte) charArray[i];
		}

		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++){
			int val = ((int) md5Bytes[i]) & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();

	}

	/**
	 * 加密解密算法 执行一次加密，两次解密
	 *
	 * @param inStr
	 * @return java.lang.String
	 * @date 2019-09-12 13:54
	 * @author panzhangbao
	 */
	public static String convertMD5(String inStr){

		char[] a = inStr.toCharArray();
		for (int i = 0; i < a.length; i++){
			a[i] = (char) (a[i] ^ 't');
		}
		String s = new String(a);
		return s;

	}

	/**
	 * 测试主函数
	 */
	public static void main(String args[]) {
		String s = "123456" + solt;
		log.info("原始：" + s);
		log.info("MD5后：" + string2MD5(string2MD5(s)));
		log.info("加密的：" + convertMD5(s));
		log.info("解密的：" + convertMD5(convertMD5(s)));
	}
}
