package com.quest.etna.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class StringUtils {
	
	public static String hashBcrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public static boolean verifyHash(String passwordToCheck, String passwordhashed) {
		return BCrypt.checkpw(passwordToCheck, passwordhashed);
	}

}
