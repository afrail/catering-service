package com.catering.service.auth.service;

import com.catering.service.auth.repository.UserInfoRepo;
import com.catering.service.auth.utill.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catering.service.auth.model.UserInfo;


/**
 * @version 1.0.0
 * @Project catering-service-base-sceleton
 * @Author Afrail Hossain
 * @Since 1 January 2023
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Autowired
	UserInfoRepo userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserInfo user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		if(!user.isActive()) {
			throw new UsernameNotFoundException("User is not active with username: " + username);
		}
		
		if(user.getAccountExpired()) {
			throw new UsernameNotFoundException("User is expired with username: " + username);
		}
		
		if(user.getAccountLocked()) {
			throw new UsernameNotFoundException("User is locked with username: " + username);
		}
		
		if(user.getCredentialsExpired()) {
			throw new UsernameNotFoundException("User credentials expired with username: " + username);
		}

		return CustomUserDetails.build(user);
	}
	
	
	
	

}
