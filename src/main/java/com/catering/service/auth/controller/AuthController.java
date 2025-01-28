package com.catering.service.auth.controller;

import javax.validation.Valid;

import com.catering.service.auth.model.Role;
import com.catering.service.auth.model.UserRoleAssign;
import com.catering.service.auth.repository.RoleRepository;
import com.catering.service.auth.repository.UserInfoRepo;
import com.catering.service.auth.repository.UserRoleAssignRepository;
import com.catering.service.auth.utill.AuthTokenUtils;
import com.catering.service.auth.utill.CustomUserDetails;
import com.catering.service.request.LoginRequest;
import com.catering.service.request.SignupRequest;
import com.catering.service.response.ApiConstants;
import com.catering.service.response.CommonResponse;
import com.catering.service.response.LoginResponse;
import com.catering.service.response.MessageResponse;
import com.catering.service.auth.model.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


/**
 * @version 1.0.0
 * @Project catering-service
 * @Author Afrail Hossain
 * @Since 27 January 2025
 */

@AllArgsConstructor
@RestController
@RequestMapping(ApiConstants.AUTH_END_POINT)
public class AuthController {
	
	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);
	
	private final AuthenticationManager authenticationManager;
    private final UserInfoRepo userRepository;
    private final PasswordEncoder encoder;
	private final AuthTokenUtils authTokenUtils;
	private final UserRoleAssignRepository roleRepository;


	@PostMapping("signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwtToken = authTokenUtils.generateJwtToken(authentication);

			CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
			
			/* get user role */
			
			/* get approval team */
			
			/* get password history */
			
			/* get password policy */
//			AppUser appUser = appUserRepository.findById(userDetails.getId()).get();
			
			/* create response */
			CommonResponse res  = new CommonResponse();
			res.setStatus(true);
			res.setData(new LoginResponse(jwtToken, userDetails.getId()));
			return ResponseEntity.ok(res);
			
		}catch (Exception e) {
			e.printStackTrace();
			CommonResponse res  = new CommonResponse();
			res.setStatus(false);
			res.setMessage("Wrong email or password");
			return ResponseEntity.ok(res);
		}
		

	}

/*	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (Objects.nonNull(signUpRequest.getEmail()) && userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}






		// Create new user's account
		UserInfo user = new UserInfo();
        user.setUsername(signUpRequest.getUsername());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
		Set<UserRoleAssign> userRoleAssign = new HashSet<>();

		UserInfo userInfo = userRepository.save(user);
		UserRoleAssign assign = new UserRoleAssign();
		for(Role role : signUpRequest.getRoles()) {
			assign.setRole(role);
			assign.setUser(userInfo);
			userRoleAssign.add(assign);
		}
		roleRepository.saveAll(userRoleAssign);


		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}*/
	

	
}
