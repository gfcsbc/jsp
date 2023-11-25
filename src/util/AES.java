package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * This program generates a AES key, retrieves its raw bytes, and then
 * reinstantiates a AES key from the key bytes. The reinstantiated key is used
 * to initialize a AES cipher for encryption and decryption.
 */

public class AES {
	// 加密
	public static byte[] Encrypt(byte[] sSrc, String sKey, String offset)
			throws Exception {
		if (sKey == null) {
			System.out.print("Key为空null");
			return null;
		}
		// 判断Key是否为16位
		if (sKey.length() != 16) {
			System.out.print("Key长度不是16位");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "算法/模式/补码方式"
		IvParameterSpec iv = new IvParameterSpec(offset.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强度
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		// cipher.
		byte[] encrypted = cipher.doFinal(sSrc);

		return encrypted;
		// return new
		// BASE64Encoder().encode(encrypted);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
	}

	// 解密
	public static byte[] Decrypt(byte[] sSrc, String sKey, String offset)
			throws Exception {
		try {
			// 判断Key是否正确
			if (sKey == null) {
				System.out.print("Key为空null");
				return null;
			}
			// 判断Key是否为16位
			if (sKey.length() != 16) {
				System.out.print("Key长度不是16位");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			// byte[] encrypted1 = new
			// BASE64Decoder().decodeBuffer(sSrc);//先用base64解密
			try {
				byte[] original = cipher.doFinal(sSrc);
				// String originalString = new String(original);
				return original;
			} catch (Exception e) {
				System.out.println(e.toString());
				return null;
			}
		} catch (Exception ex) {
			System.out.println(ex.toString());
			return null;
		}
	}

	public static String byteArray2Hex(byte[] src) {
		StringBuilder sb = new StringBuilder();
		for (byte by : src) {
			sb.append(by);
		}
		return sb.toString();

	}

	public static void main(String[] args) throws Exception {
		/*
		 * 加密用的Key 可以用26个字母和数字组成，最好不要用保留字符，虽然不会错，至于怎么裁决，个人看情况而定
		 * 此处使用AES-128-CBC加密模式，key需要为16位。
		 */

//		String cKey = SysConsts.AES_KEY;
//		// 需要加密的字串
//		String cSrc = "Email : arix04@xxx.com";
//		cSrc = "abcdeefghijkl"; // \n中国
//		System.out.println(cSrc);
//		// 加密
//		long lStart = System.currentTimeMillis();
//		byte[] enced = AES.Encrypt(cSrc.getBytes(), cKey);
//		// String enString = new BASE64Encoder().encode(enced);;
//		String base64 = LsdUtils.encodeToBase64(enced);
//		System.out.println("加密后的字串是：" + base64);
//
//		long lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("加密耗时：" + lUseTime + "毫秒");
//		// 解密
//		lStart = System.currentTimeMillis();
//		// decode from base64
//		byte[] ori = LsdUtils.decodeFromBASE64(base64);
//		byte[] DeString = AES.Decrypt(ori, cKey);
//		System.out.println("解密后的字串是：" + new String(DeString));
//		lUseTime = System.currentTimeMillis() - lStart;
//		System.out.println("解密耗时：" + lUseTime + "毫秒");
	}

}
