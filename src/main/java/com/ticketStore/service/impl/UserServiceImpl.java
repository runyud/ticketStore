package com.ticketStore.service.impl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ticketStore.domain.User;
import com.ticketStore.domain.UserBilling;
import com.ticketStore.domain.UserPayment;
import com.ticketStore.domain.security.PasswordResetToken;
import com.ticketStore.domain.security.UserRole;
import com.ticketStore.repository.PasswordResetTokenRepository;
import com.ticketStore.repository.RoleRepository;
import com.ticketStore.repository.UserPaymentRepository;
import com.ticketStore.repository.UserRepository;
import com.ticketStore.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository; 
	
	@Autowired
	private UserPaymentRepository userPaymentRepository;
	
	@Override
	public PasswordResetToken getPasswordResetToken(final String token) {
		return passwordResetTokenRepository.findByToken(token);
	}

	@Override
	public void createPasswordResetTokenForUser(final User user, final String token) {
		final PasswordResetToken resetToken = new PasswordResetToken(token, user);
		passwordResetTokenRepository.save(resetToken);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		User localUser = userRepository.findByUsername(user.getUsername());
		if(localUser != null) {
			LOGGER.info("user {} already exists.", user.getUsername());
		} else {
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			
			localUser = userRepository.save(user);
		}
		
		return localUser;
	}

	@Override
	public User save(User user) {
		return userRepository.save(user);
	}

	@Override
	public void updateUserBilling(UserBilling userBilling, UserPayment userPayment, User user) {
		userPayment.setUser(user);
		userPayment.setUserBilling(userBilling);
		userPayment.setDefaultPayment(true);
		userBilling.setUserPayment(userPayment);
		user.getUserPaymentList().add(userPayment);
		save(user);
	}

	@Override
	public void setUserDefaultPayment(Long userPaymentId, User user) {
List<UserPayment> userPaymentList = (List<UserPayment>) userPaymentRepository.findAll();
		
		for (UserPayment userPayment : userPaymentList) {
			if(userPayment.getId() == userPaymentId) {
				userPayment.setDefaultPayment(true);
				userPaymentRepository.save(userPayment);
			} else {
				userPayment.setDefaultPayment(false);
				userPaymentRepository.save(userPayment);
			}
		}
		
	}

}
