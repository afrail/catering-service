package com.catering.service.auth.model;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.catering.service.common.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;

/**
 * @version 1.0.0
 * @Project catering-service-base-sceleton
 * @Author Afrail Hossain
 * @Since 1 January 2023
 */


@Data
@Entity
@Table(name = "USER_INFO")
public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "IS_ACTIVE", columnDefinition = "boolean default true")
	private boolean active = true;
	

	@SuppressWarnings("unused")
	private static final Logger LOG = LoggerFactory.getLogger(UserInfo.class);
	
	@Column(name = "USERNAME", length=50, nullable = false, unique = true)
	private String username;
	
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	
	@Column(name = "EMAIL", length=100)
	private String email;
	
	@Column(name = "MOBILE", length=20)
	private String mobile;

	@Column(name = "EMPLOYEE_CODE", length=50)
	private String employeeCode;

	@Column(name = "NAME", length=100)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<UserRoleAssign> roles;

	@Column(name = "IS_ACCOUNT_EXPIRED", columnDefinition = "boolean default false")
	private Boolean accountExpired = false;

	@Column(name = "IS_CREDENTIALS_EXPIRED", columnDefinition = "boolean default false")
	private Boolean credentialsExpired = false;

	@Column(name = "IS_ACCOUNT_LOCKED", columnDefinition = "boolean default false")
	private Boolean accountLocked = false;
	
	
}
