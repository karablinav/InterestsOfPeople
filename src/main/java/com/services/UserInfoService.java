package com.services;

import com.dto.UserInfoDTO;
import javassist.NotFoundException;

import java.util.List;

public interface UserInfoService {

    List<UserInfoDTO> getAllUsers();

    UserInfoDTO findById(Long id) throws NotFoundException;

    void save(UserInfoDTO newUserInfo);

    void delete(Long id) throws NotFoundException;
}
