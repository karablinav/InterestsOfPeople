package com.controllers.rest;

import com.dto.UsersInfoDTO;
import com.model.UsersInfo;
import com.services.UserInfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserInfoController {

    private UserInfoService userInfoService;

    @Autowired
    public void setUserInfoService(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/usersinfo")
    public ResponseEntity<List<UsersInfo>> getAllUsers() {
        List<UsersInfo> users = userInfoService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
        }
    }

    @GetMapping("/usersinfo/{id}")
    public ResponseEntity<UsersInfo> getSectorById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(userInfoService.findById(id));
    }

    @PostMapping("/usersinfo")
    public ResponseEntity<UsersInfo> saveSector(@Valid @RequestBody UsersInfoDTO newUsers) throws NotFoundException {
        UsersInfo usersInfo = userInfoService.save(newUsers);
        return ResponseEntity.status(HttpStatus.CREATED).body(usersInfo);
    }

    @PutMapping("/usersinfo/{id}")
    public ResponseEntity<UsersInfo> updateSector(@PathVariable(value = "id") Long id, @Valid @RequestBody UsersInfoDTO updatedUsers)
            throws NotFoundException {
        UsersInfo usersInfo = userInfoService.save(updatedUsers);
        return ResponseEntity.status(HttpStatus.OK).body(usersInfo);
    }

    @DeleteMapping("/usersinfo/{id}")
    public ResponseEntity<UsersInfo> deleteSector(@PathVariable(value = "id") Long id) throws NotFoundException {
        userInfoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
