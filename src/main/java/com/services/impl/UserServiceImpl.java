package com.services.impl;

import com.dto.UserDTO;
import com.model.User;
import com.repositories.UserRepository;
import com.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final static String NOT_FOUND_ID = "User with id = %d not found";

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertUserToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDTO findById(Long id) throws NotFoundException {
        return userRepository.findById(id).map(this::convertUserToDto)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID, id)));
    }

    @Transactional
    @Override
    public void save(UserDTO newUser) {
        User user = convertDtoToUser(newUser);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void update(Long id, UserDTO updatedUser) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID,id)));
        user.setName(updatedUser.getName());
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID,id)));
        userRepository.delete(user);
    }

    private UserDTO convertUserToDto(User user){
        return UserDTO.builder().id(user.getId())
                .name(user.getName())
                .usersInfo(user.getUserInfo())
                .build();
    }

    private User convertDtoToUser(UserDTO userDTO){
        return User.builder().id(userDTO.getId())
                .name(userDTO.getName())
                .userInfo(userDTO.getUsersInfo())
                .build();
    }
}
