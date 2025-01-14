package com.catering.service.auth.utill;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.catering.service.auth.model.Role;
import com.catering.service.auth.model.UserRoleAssign;
import com.catering.service.helper.Authority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.catering.service.auth.model.UserInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @version 1.0.0
 * @Project catering-service-base-sceleton
 * @Author Afrail Hossain
 * @Since 1 January 2023
 */
public class CustomUserDetails implements UserDetails {

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(CustomUserDetails.class);

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String email;
	@JsonIgnore
	private String password;

	private Set<UserRoleAssign> userRoles;

	public CustomUserDetails(Integer id, String username, String email, String password, Set<UserRoleAssign> userRoles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userRoles = userRoles;
	}

	public static CustomUserDetails build(UserInfo user) {

		return new CustomUserDetails(user.getId(), user.getUsername(), user.getEmail(),
				user.getPassword(),user.getRoles());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorites = new HashSet<>();
		userRoles.forEach(ur -> authorites.add(new Authority(ur.getRole().getName())));

		return authorites;
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		CustomUserDetails user = (CustomUserDetails) o;
		return Objects.equals(id, user.id);
	}
}
