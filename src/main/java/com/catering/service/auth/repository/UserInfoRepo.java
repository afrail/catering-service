/**
 * 
 */
package com.catering.service.auth.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.catering.service.auth.model.UserInfo;


/**
 * @version 1.0.0
 * @Project catering-service-base-sceleton
 * @Author Afrail Hossain
 * @Since 1 January 2023
 */

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{
	Optional<UserInfo> findByUsername(String username);
	List<UserInfo> findByActive(boolean active);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	List<UserInfo> findByEmployeeCodeAndActive(String code, boolean active);
}
