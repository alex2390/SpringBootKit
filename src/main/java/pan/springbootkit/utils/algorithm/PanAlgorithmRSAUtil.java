package pan.springbootkit.utils.algorithm;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 算法 util
 *
 * Created by panzhangbao on 2019-09-12 13:52:05
 * Copyright © 2019 panzhangbao. All rights reserved.
 */
@Slf4j
public class PanAlgorithmRSAUtil {

	public static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDMI/RlL0086rZawRfHafDJaYZ3jMNTXSjKiz3Bs2JIWdso224sHlSYI78mFeN4WMfXAa+9kCC8mfOQJ4SAHkxkIzFvIwVz56Ndj5krsCpyqNIp8z8Mv4/g/RYPF0ywhQnzT4caDBORj+kYPmUDI76JGnfGDEBoNcD2ymLt4UOItwIDAQAB";
	public static final String privateKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAMwj9GUvTTzqtlrBF8dp8MlphneMw1NdKMqLPcGzYkhZ2yjbbiweVJgjvyYV43hYx9cBr72QILyZ85AnhIAeTGQjMW8jBXPno12PmSuwKnKo0inzPwy/j+D9Fg8XTLCFCfNPhxoME5GP6Rg+ZQMjvokad8YMQGg1wPbKYu3hQ4i3AgMBAAECgYBsaE+aCGOZDP/n9BDfqkffffWVuR/lPlyyKv2RxuRTjbxP1y+LqFO+M4WIASm6OFNvv4qHPWnVHmB01jeMHgrsU6ZFTScAYZIraKRh+HMnwIEqGVf2G8JWfQP38ZA9PzDDzNJVgHu2o82mkT/O12pY/SQPZUQOorhtN/GCRD5fyQJBAO7vCZ+bWUxBnKGWrSXONMVaeTLfC9zs5njWu5pnaDip3RbhqTkYtAtqUrs0yhREIvONzFE7EY9yb7+fqDBM920CQQDauLaQAzVqCw9E6U+vm8z+9rYcKovdn8br06lF+gqjyk2Rieuzxok5lS58eEkwmXHFOQQKc5u6gUg+aprwKnYzAkAeoNO5+v8CcHK0oA0ICJvpJITeyYrKwi91FAmubE0G3ndx5HF92TXTNMjwKH8zA9z7HZUwYIMKwV+jz8KOln8tAkAuLC5GCpddEoafqo0q6j4FWPmqyoSKPa+VmzIx1o4mc4+aeL4sTm4rnMWaex4gR1RQKTAkMLGgVqh0nLammqAnAkB+asIGOS1RlXPz005ZkuGGhzcZLn2VMqNDTcAlLuywNyRTAFERsz+udoz5BpYSsgTYFwkPFhZwXGvG1/U/tuOw";

	/**
	 * 随机生成密钥对
	 * @throws NoSuchAlgorithmException
	 */
	public static void genKeyPair(){
		// KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
		KeyPairGenerator keyPairGen = null;
		try {
			keyPairGen = KeyPairGenerator.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}

		// 初始化密钥对生成器，密钥大小为96-1024位
		keyPairGen.initialize(1024,new SecureRandom());
		// 生成一个密钥对，保存在keyPair中
		KeyPair keyPair = keyPairGen.generateKeyPair();
		// 得到私钥
		RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
		// 得到公钥
		RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
		String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
		// 得到私钥字符串
		String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));

		log.info("\npublicKey: " + publicKeyString + "\nprivateKeyString: " + privateKeyString);
	}

	/**
	 * RSA公钥加密
	 *
	 * @param str
	 *            加密字符串
	 * @param publicKey
	 *            公钥
	 * @return 密文
	 */
	public static String encrypt( String str, String publicKey ){
		//base64编码的公钥
		byte[] decoded = Base64.decodeBase64(publicKey);
		RSAPublicKey pubKey = null;
		try {
			pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// RSA加密
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		String outStr = null;
		try {
			outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return outStr;
	}

	/**
	 * RSA私钥解密
	 *
	 * @param str
	 *            加密字符串
	 * @param privateKey
	 *            私钥
	 * @return 铭文
	 * @throws Exception
	 *             解密过程中的异常信息
	 */
	public static String decrypt(String str, String privateKey){
		//64位解码加密后的字符串
		byte[] inputByte = new byte[0];
		try {
			inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// base64编码的私钥
		byte[] decoded = Base64.decodeBase64(privateKey);
		RSAPrivateKey priKey = null;
		try {
			priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
		} catch (InvalidKeySpecException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// RSA解密
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("RSA");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		}
		try {
			cipher.init(Cipher.DECRYPT_MODE, priKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		String outStr = null;
		try {
			outStr = new String(cipher.doFinal(inputByte));
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return outStr;
	}

	public static void main(String args[]) {
//		genKeyPair();

		String s = "3333";
		log.info("\n加密前，s=" + s);
		s = encrypt(s, publicKey);
		log.info("\n加密后，s=" + s);
		log.info("\n解密后，s=" + decrypt(s, privateKey));
	}
}
