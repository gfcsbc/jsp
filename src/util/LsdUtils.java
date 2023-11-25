package util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;
import java.util.Random;
import java.util.zip.*;


public class LsdUtils {
	public static void main(String[] args) {
		String password = "admin";
		password = LsdUtils.getMd5String(password);
		System.out.println(password);
	}

	public static byte[] zipIt(byte[] buf) {

		String filename = String.valueOf(System.currentTimeMillis()) + ".xml";// new
																				// java.util.Date().toString();
		return zipIt(buf, filename);
	}

	public static byte[] zipIt(byte[] buf, String filename) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(byteStream);
		// String filename = new java.util.Date().toString();
		try {
			zos.putNextEntry(new ZipEntry(filename));
			zos.write(buf);
			zos.flush();
			zos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return byteStream.toByteArray();
	}

	public static byte[] unzipIt(byte[] buf) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
		ZipInputStream zis = new ZipInputStream(byteStream);
		BufferedInputStream bis = new BufferedInputStream(zis);
		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();

		try {
			ZipEntry ze;
			int x;
			while ((ze = zis.getNextEntry()) != null) {
				// System.out.println("lsdUtils:"+ze);
				while ((x = bis.read()) != -1) {
					byteOutStream.write(x);
				}
			}
			bis.close();
			byteOutStream.close();
		} catch (IOException e) {

			e.printStackTrace();
			return null;
		}

		return byteOutStream.toByteArray();
	}

	public static String getZipFileFilename(byte[] buf) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);
		ZipInputStream zis = new ZipInputStream(byteStream);

		String filename = null;
		try {
			ZipEntry ze;
			while ((ze = zis.getNextEntry()) != null) {
				// System.out.println("lsdUtils:"+ze);
				filename = ze.getName();
				break;
			}
		} catch (IOException e) {

			e.printStackTrace();
		}

		return filename;
	}

	public static byte[] gzipIt(byte[] buf) {
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream();

		try {
			GZIPOutputStream zos = new GZIPOutputStream(byteStream);
			zos.write(buf);
			zos.flush();
			zos.close();

			byteStream.close();
		} catch (IOException e) {

			e.printStackTrace();
		}

		return byteStream.toByteArray();
	}

	public static byte[] ungzipIt(byte[] buf) {
		ByteArrayInputStream byteStream = new ByteArrayInputStream(buf);

		ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
		// ByteArrayInputStream byteIn = new ByteArrayInputStream();
		try {
			// BufferedReader in2 = new BufferedReader( new
			// InputStreamReader(new GZIPInputStream(byteStream)));
			GZIPInputStream zis = new GZIPInputStream(byteStream);
			final int ONCE_READ_COUNT = 5;
			byte[] cont = new byte[ONCE_READ_COUNT];
			int startPos = 0;
			int gotBytes;
			while (zis.available() > 0) {
				// byteOutStream.write(zis.read());
				gotBytes = zis.read(cont, 0, ONCE_READ_COUNT);
				if (gotBytes > 0) {
					byteOutStream.write(cont, 0, gotBytes);
					startPos += gotBytes;
					if (gotBytes != ONCE_READ_COUNT) {
						break;
					}
				} else {
					break;
				}
			}
			byteOutStream.close();
			zis.close();
			// byteOutStream.close();
			// String s;
			// String s1 = new String(byteOutStream.toByteArray());
			// System.out.println(s1);
			// while ((s = in2.readLine()) != null){
			//
			// System.out.println(s);
			// }

		} catch (IOException e) {

			e.printStackTrace();
		}

		return byteOutStream.toByteArray(); // byteOutStream.toByteArray();
	}

	public static String getRandomStr(int len) {
		StringBuilder sb = new StringBuilder();
		final String contents = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rd = new Random();
		final int size = contents.length();
		for (int i = 0; i < len; ++i) {
			sb.append(contents.charAt(rd.nextInt(size)));

		}
		rd = null;
		return sb.toString();
	}

	public static MessageDigest getDigest(String algorithm) {
		try {
			return MessageDigest.getInstance(algorithm);
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
			return null;
		}
	}

	public static byte[] getMD5HashValue(byte[] content) {
		MessageDigest hash = getDigest("MD5");
		return hash.digest(content);
	}

	public static byte[] getMD5HashValueByChar(String inStr) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		char[] charArray = inStr.toCharArray();
		byte[] byteArray = new byte[charArray.length];

		for (int i = 0; i < charArray.length; i++)
			byteArray[i] = (byte) charArray[i];

		byte[] md5Bytes = md5.digest(byteArray);

		return md5Bytes;
	}

	public static byte[] getPasswordMd5Hash(String pw) {
		MessageDigest hash = getDigest("MD5");
		return hash.digest(pw.getBytes());
	}

	public static String getMd5String(String str) {
		byte[] temp = getMD5HashValueByChar(str);
		String b = byteArray2Hex(temp);
		return b;
	}

	public static String encodeToBase64(byte[] ori) {
		Encoder encoder = Base64.getMimeEncoder();
		return new String(encoder.encode(ori));
	}

	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		Decoder decoder=Base64.getMimeDecoder();
		try {
			byte[] b = decoder.decode(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}

	public static byte[] decodeFromBASE64(String s) {
		if (s == null)
			return null;

		Decoder decoder=Base64.getMimeDecoder();
		try {
			byte[] b = decoder.decode(s);
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	public static java.util.Date getDateFromStr(String dateStr, String format) {
		// default foramt is yyyy-MM-dd
		if (format == null) {
			format = "yyyy-MM-dd";
		}
		java.util.Date parsedDate = null;
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		try {
			parsedDate = fmt.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return parsedDate;
	}

	public static java.util.Date getDateAndTimeFromStr(String dateStr,
			String format) {
		// default foramt is yyyy-MM-dd
		if (format == null) {
			format = "yyyy-MM-dd HH:mm:ss";
		}
		java.util.Date parsedDate = null;
		SimpleDateFormat fmt = new SimpleDateFormat(format);
		try {
			parsedDate = fmt.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return parsedDate;
	}

	public static void makeDirs(String path) {
		File f = new File(path);
		f.mkdirs();
	}

	public static String formatDateToStr(java.util.Date date, String fmt) {
		if (date == null) {
			return "";
		}
		if (fmt == null) {
			fmt = "yyyy-MM-dd";
		}
		SimpleDateFormat df = new SimpleDateFormat(fmt);
		return df.format(date);
	}

	// ????
	public static byte[] aesEncrypt(byte[] sSrc, String sKey, String offset)
			throws Exception {
		if (sKey == null) {
			System.out.print("Key???null");
			return null;
		}
		// ?��?Key????16��
		if (sKey.length() != 16) {
			System.out.print("Key???????16��");
			return null;
		}
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");// "??/??/?????"
		IvParameterSpec iv = new IvParameterSpec(offset.getBytes());// ???CBC??????????????iv??????????????????
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		// cipher.
		byte[] encrypted = cipher.doFinal(sSrc);

		return encrypted;
		// return new
		// BASE64Encoder().encode(encrypted);//??????BASE64??????????????2?��???????��?
	}

	// ????
	public static byte[] aesDecrypt(byte[] sSrc, String sKey, String offset)
			throws Exception {
		try {
			// ?��?Key??????
			if (sKey == null) {
				System.out.print("Key???null");
				return null;
			}
			// ?��?Key????16��
			if (sKey.length() != 16) {
				System.out.print("Key???????16��");
				return null;
			}
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(offset.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			// byte[] encrypted1 = new
			// BASE64Decoder().decodeBuffer(sSrc);//????base64????
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
			int v = by & 0xff;
			if (v < 16) {
				sb.append(0);
			} else {
				sb.append(Integer.toHexString(v));
			}
		}

		return sb.toString();

	}

	public static byte[] hexStrToByteArray(String s) throws Exception {
		int len = s.length();
		if (len % 2 != 0) {
			throw new Exception("length must be even");
		}
		int bytesLen = len / 2;
		s = s.toUpperCase();
		byte[] by = new byte[bytesLen];
		char c;
		int val = 0;
		for (int i = 0; i < len; ++i) {
			c = s.charAt(i);
			if (c <= 'F' && c >= 'A') {
				val += (byte) (c - 'A' + 10);
			} else if (c <= '9' && c >= '0') {
				val += (byte) (c - '0');
			} else {
				throw new Exception("one char wrong");
			}
			if (i % 2 == 0) {
				val <<= 4;

			} else {
				by[i / 2] = (byte) val;
				val = 0;
			}
		}
		return by;
	}

}
