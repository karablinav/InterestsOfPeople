package com.services;

import com.dto.UserDTO;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUsers() throws NotFoundException;

    UserDTO findById(Long id) throws NotFoundException;

    void save(UserDTO newUser);

    void update(Long id, UserDTO updateUser) throws NotFoundException;

    void delete(Long id) throws NotFoundException;
}
