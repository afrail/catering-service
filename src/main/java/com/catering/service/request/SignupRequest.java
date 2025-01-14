package com.catering.service.request;

import com.catering.service.auth.model.Role;
import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @Project   candle
 * @Author    Md. Afrail Hossain
 * @Since     jan 21, 2024
 * @version   1.0.0
 */
@Data
public class SignupRequest {


	public String username;
	public String email;
	public String password;
	public Set<Role> roles;

}
