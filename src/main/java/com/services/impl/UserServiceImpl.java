package com.services.impl;

import com.dto.UserDTO;
import com.model.User;
import com.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl {

    private final static String NOT_FOUND_ID = "User with id = %d not found";

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findById(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new NotFoundException(String.format(NOT_FOUND_ID, id));
        }
    }

    public User save(UserDTO newUser) {
        User user = User.builder().name(newUser.getName()).build();
        return userRepository.save(user);
    }

    public User update(Long id, UserDTO updatedUser) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            user.get().setName(updatedUser.getName());
            return userRepository.save(user.get());
        } else {
            throw new NotFoundException(String.format(NOT_FOUND_ID, id));
        }
    }

    public void delete(Long id) throws NotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
        } else
            throw new NotFoundException(String.format(NOT_FOUND_ID, id));
    }


}
