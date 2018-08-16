package com.services.impl;

import com.dto.UserInfoDTO;
import com.model.User;
import com.model.UserInfo;
import com.repositories.UserInfoRepository;
import com.repositories.UserRepository;
import com.services.UserInfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserInfoServiceImpl implements UserInfoService{

    private final static String NOT_FOUND_ID = "UserInfo with id = %d not found";

    private  final UserInfoRepository userInfoRepository;

    private final UserRepository userRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserRepository userRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public List<UserInfoDTO> getAllUsers() {
        return userInfoRepository.findAll().stream()
                .map(this::convertUserToDto).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserInfoDTO findById(Long id) throws NotFoundException {
        return userInfoRepository.findById(id)
                .map(this::convertUserToDto)
                .orElseThrow(()-> new NotFoundException(String.format(NOT_FOUND_ID, id)));
    }

    @Transactional
    @Override
    public void save(UserInfoDTO newUserInfo){
       Optional<UserInfo> usersInfo = Optional.ofNullable(userInfoRepository.findByUserId(newUserInfo.getUser().getId()));
        if (usersInfo.isPresent()) {
            userRepository.save(newUserInfo.getUser());
            UserInfo infoUser = usersInfo.get();
            infoUser.setCheckbox(newUserInfo.isCheckbox());
            infoUser.setUser(newUserInfo.getUser());
            infoUser.setSectors(newUserInfo.getSectors());
            infoUser.setCheckbox(newUserInfo.isCheckbox());
            userInfoRepository.save(usersInfo.get());
        } else {
            User newUser = User.builder().name(newUserInfo.getUser().getName()).build();
            UserInfo info = UserInfo.builder()
                    .sectors(newUserInfo.getSectors())
                    .user(newUser)
                    .checkbox(newUserInfo.isCheckbox()).build();
            newUser.setUserInfo(info);
            userRepository.save(newUser);
             userInfoRepository.save(info);
        }
    }

    @Transactional
    @Override
    public void delete(Long id) throws NotFoundException {
       UserInfo userInfo = userInfoRepository.findById(id)
               .orElseThrow(() -> new NotFoundException(String.format(NOT_FOUND_ID,id)));
       userInfoRepository.delete(userInfo);
    }

    private UserInfoDTO convertUserToDto(UserInfo userInfo){
        return UserInfoDTO.builder()
                .id(userInfo.getId())
                .user(userInfo.getUser())
                .sectors(userInfo.getSectors())
                .checkbox(userInfo.isCheckbox())
                .build();
    }

    private UserInfo convertDtoToUser(UserInfoDTO userInfoDTO){
        return UserInfo.builder()
                .id(userInfoDTO.getId())
                .user(userInfoDTO.getUser())
                .sectors(userInfoDTO.getSectors())
                .checkbox(userInfoDTO.isCheckbox())
                .build();
    }
}
