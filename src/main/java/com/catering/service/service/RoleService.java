package com.catering.service.service;

import com.catering.service.auth.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAll();

    List<Role> getAllActive();
}
