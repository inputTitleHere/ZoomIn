package com.kh.zoomin.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class ZoominMvcUtils {

	public static String getEncryptedPassword(String rawPassword, String salt) {
		String encryptedPassword = null;
		
		try {
			//암호화방식 sha-512 / salt 
			//알고리즘으로 비밀번호 대입해서 확인해줄것... 
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = rawPassword.getBytes("utf-8");
			byte[] saltBytes = salt.getBytes("utf-8");
			md.update(saltBytes); 
			byte[] encryptedBytes = md.digest(input);
			
			//인코딩 
			Encoder encoder = Base64.getEncoder();
			encryptedPassword = encoder.encodeToString(encryptedBytes);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return encryptedPassword;
	}


}
