/* 
 * 日期：2008-10-26
 *  
 * 版权所有：浙江浙大网新众合轨道交通工程有限公司
 */
package com.insigma.afc.datasource;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Security;

/**
 * 双向的加密解密工具类
 */
public class DESUtil {
	// 定义加密算法,可用DES,DESede,Blowfish
	private static String Algorithm = "DES";
	/**
	 * 密钥
	 */
	private static String key = "*:@1$7!a";
	static {
		Security.addProvider(new com.sun.crypto.provider.SunJCE());
	}

	/**
	 * 使用默认密钥加密字符串数据
	 * 
	 * @param cleartext
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String encrypt(String cleartext) throws Exception {

		SecretKey skey = new SecretKeySpec(key.getBytes(), Algorithm);
		Cipher cipher = Cipher.getInstance(Algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, skey);
		byte[] cipherByte = cipher.doFinal(cleartext.getBytes());
		// 转码，不转码为iso-8859-1会出一些问题
		return URLEncoder.encode(new String(cipherByte, "ISO-8859-1"), "ISO-8859-1");

	}

	/**
	 * 使用密钥解密字节码数据,用来解密的密钥与加密密钥相同（单钥密码体制）
	 * 
	 * @param ciphertext
	 *            String
	 * @throws Exception
	 * @return String
	 */
	public static String decrypt(String ciphertext) throws Exception {
		// 解码
		ciphertext = URLDecoder.decode(ciphertext, "ISO-8859-1");
		SecretKey skey = new SecretKeySpec(key.getBytes(), Algorithm);
		Cipher cipher = Cipher.getInstance(Algorithm);
		cipher.init(Cipher.DECRYPT_MODE, skey);
		byte[] clearByte = cipher.doFinal(ciphertext.getBytes("ISO-8859-1"));
		return new String(clearByte);
	}
}
