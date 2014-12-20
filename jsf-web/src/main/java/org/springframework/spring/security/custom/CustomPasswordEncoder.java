package org.springframework.spring.security.custom;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder{

	public String encode(CharSequence rawPassword) {
		return DigestUtils.sha1Hex(rawPassword.toString());
	}

	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		String passwordDigest = encode(rawPassword);
		if(passwordDigest.equals(encodedPassword)){
			return true;
		}else{
			return false;
		}
	}
}
