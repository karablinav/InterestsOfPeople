package com.services;

import com.dto.UserDTO;
import com.model.User;
import javassist.NotFoundException;

import java.util.List;

public interface UserService {

    List<User> getAllUsers() throws NotFoundException;

    User findById(Long id) throws NotFoundException;

    User save(UserDTO newUser);

    User update(Long id, UserDTO updateUser) throws NotFoundException;

    void delete(Long id) throws NotFoundException;
}
