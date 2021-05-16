package com.ticketStore.utils;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {
	private static final String SALT = "E1F53135E559C253";
	
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12, new SecureRandom(SALT.getBytes()));
	}
	
	@Bean
	public static String randomPasswordGenerate() {
		String SALTALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder saltBuilder = new StringBuilder();
		Random random = new Random();
		
		while(saltBuilder.length() < 18) {
			int idx = (int) (SALTALPHABET.length()*random.nextFloat());
			saltBuilder.append(SALTALPHABET.charAt(idx));
		}
		
		return saltBuilder.toString();
	}
}
