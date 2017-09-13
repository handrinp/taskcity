package com.taskcity.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

import com.taskcity.data.dto.UserDTO;

public class CryptoUtils {
	private static final String SALT_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int SALT_LENGTH = 16;

	public static String generateSalt() {
		StringBuilder salt = new StringBuilder();
		Random rand = new Random();

		for (int i = 0; i < SALT_LENGTH; ++i) {
			salt.append(SALT_CHARS.charAt(rand.nextInt(SALT_CHARS.length())));
		}

		return salt.toString();
	}

	public static String hash(String password, String salt) {
		String hash = null;
		String saltedPassword = salt + password;

		try {
			byte[] hashBytes = MessageDigest.getInstance("SHA-256")
					.digest(saltedPassword.getBytes(StandardCharsets.UTF_8));
			hash = Base64.getEncoder()
					.encodeToString(hashBytes);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hash;
	}

	public static boolean verifyPassword(UserDTO user, String password) {
		return user.hasPassword() && user.getHash()
				.equals(hash(password, user.getSalt()));
	}
}
