package com.services;

import com.dto.UserInfoDTO;
import com.model.UsersInfo;
import javassist.NotFoundException;

import java.util.List;

public interface UserInfoService {

    List<UsersInfo> getAllUsers();

    UsersInfo findById(Long id) throws NotFoundException;

    UsersInfo save(UserInfoDTO newUserInfo);

    void delete(Long id) throws NotFoundException;
}
