package Utilities;

import org.codehaus.plexus.util.Base64;

public class EncodingAndDecodingStrings {
	
	public static String decodePassword() {
		String str = "admin";
		byte[] encodedString=Base64.encodeBase64(str.getBytes());
		byte[] decodedString=Base64.decodeBase64(encodedString);
		return (new String(encodedString));
	}

	public static void main(String[] args) {
		/*
		 * String str = "admin"; byte[]
		 * encodedString=Base64.encodeBase64(str.getBytes());
		 * System.out.println("Encoded password :"+new String(encodedString));
		 * 
		 * 
		 * byte[] decodedString=Base64.decodeBase64(encodedString);
		 * System.out.println("Decoded password :"+new String(decodedString));
		 * 
		 * 
		 * System.out.println("decoded password is :"+decodePassword(encodedString));
		 */
	}

}
