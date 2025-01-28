package com.catering.service.auth.service;

import com.catering.service.auth.model.UserInfo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserInfoService {

    UserInfo save(UserInfo obj);
    UserInfo update(UserInfo obj);
    UserInfo delete(UserInfo obj);
    List<UserInfo> getAll();
    List<UserInfo> getAllActive();
    Page<UserInfo> getPageableList(int page, int size);
}
