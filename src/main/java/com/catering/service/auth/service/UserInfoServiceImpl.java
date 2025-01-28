package com.catering.service.auth.service;

import com.catering.service.auth.model.UserInfo;
import com.catering.service.auth.repository.UserInfoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoRepo userInfoRepo;
    @Override
    public UserInfo save(UserInfo obj) {
        return userInfoRepo.save(obj);
    }

    @Override
    public UserInfo update(UserInfo obj) {
        return userInfoRepo.save(obj);
    }

    @Override
    public UserInfo delete(UserInfo obj) {
        userInfoRepo.delete(obj);
        return obj;
    }

    @Override
    public List<UserInfo> getAll() {
        return List.of();
    }

    @Override
    public List<UserInfo> getAllActive() {
        return List.of();
    }

    @Override
    public Page<UserInfo> getPageableList(int page, int size) {
        return null;
    }
}
