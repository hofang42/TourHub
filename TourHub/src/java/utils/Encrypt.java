package utils;

import java.security.MessageDigest;
import org.apache.tomcat.util.codec.binary.Base64;

public class Encrypt {
	// SHA-256 encryption
	public static String toSHA256(String str) {
		String salt = "a12312398@!!12..9#azxmcnm;!!@"; // Add salt to complicate password
		String result = null;

		str = str + salt;
		try {
			byte[] dataBytes = str.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			result = Base64.encodeBase64String(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(toSHA256("123456"));
	}
}
