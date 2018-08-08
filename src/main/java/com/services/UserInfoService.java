package com.services;

import com.dto.UsersInfoDTO;
import com.model.User;
import com.model.UsersInfo;
import com.repositories.UserInfoRepository;
import com.repositories.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserInfoService {

    private final static String NOT_FOUND_ID = "UserInfo with id = %d not found";

    private UserInfoRepository userInfoRepository;

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository usersService) {
        this.userRepository = usersService;
    }

    @Autowired
    public void setUserInfoRepository(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    public List<UsersInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    public UsersInfo findById(Long id) throws NotFoundException {
        Optional<UsersInfo> user = userInfoRepository.findById(id);
        return user.orElseThrow(()-> new NotFoundException(String.format(NOT_FOUND_ID, id)));
    }

    public UsersInfo save(UsersInfoDTO newUserInfo){
       Optional<UsersInfo> usersInfo = Optional.ofNullable(userInfoRepository.findByUserId(newUserInfo.getUser().getId()));
        if (usersInfo.isPresent()) {
            userRepository.save(newUserInfo.getUser());
            UsersInfo infoUser = usersInfo.get();
            infoUser.setCheckbox(newUserInfo.isCheckBox());
            infoUser.setUser(newUserInfo.getUser());
            infoUser.setSectors(newUserInfo.getSectors());
            infoUser.setCheckbox(newUserInfo.isCheckBox());
            return userInfoRepository.save(usersInfo.get());
        } else {
            User newUser = User.builder().name(newUserInfo.getUser().getName()).build();
            UsersInfo info = UsersInfo.builder()
                    .sectors(newUserInfo.getSectors())
                    .user(newUser)
                    .checkbox(newUserInfo.isCheckBox()).build();
            newUser.setUserInfo(info);
            userRepository.save(newUser);
            return userInfoRepository.save(info);
        }
    }
    public void delete(Long id) throws NotFoundException {
        Optional<UsersInfo> usersInfo = userInfoRepository.findById(id);
        if (usersInfo.isPresent()) {
            userInfoRepository.delete(usersInfo.get());
        } else
            throw new NotFoundException(String.format(NOT_FOUND_ID, id));
    }
}
