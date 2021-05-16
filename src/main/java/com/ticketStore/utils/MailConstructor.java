package com.ticketStore.utils;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.ticketStore.domain.User;

@Component
public class MailConstructor {
	@Autowired
	private Environment env;

	public SimpleMailMessage constructResetTokenEmail(String contextPath, Locale locale, String token, User user,
			String password) {
		String url = contextPath + "/newUser?token=" + token;
		String message = "\nPlease click on this link to verify email and edit your personal info. Your password is: \n"
				+ password;
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(user.getEmail());
		email.setSubject("Ticket Store - New User Please Verify");
		email.setText(url + message);
		email.setFrom(env.getProperty("support.email"));
		return email;
	}
}
