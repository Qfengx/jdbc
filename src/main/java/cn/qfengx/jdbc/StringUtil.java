package cn.qfengx.jdbc;

import java.security.MessageDigest;
import sun.misc.BASE64Encoder;

public class StringUtil {
	
	public static String stringToMD5(String str) {
		String newStr = "";
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			BASE64Encoder base64en = new BASE64Encoder();
			newStr = base64en.encode(md5.digest(str.getBytes("utf-8")));
		} catch (Exception e) {
			System.out.println("MD5加密失败");
			e.printStackTrace();
		} finally {
			return newStr;
		}
	}
}
