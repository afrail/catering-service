package com.catering.service.auth.repository;

import com.catering.service.auth.model.UserRoleAssign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleAssignRepository extends JpaRepository<UserRoleAssign, Integer> {
}
