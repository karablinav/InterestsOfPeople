package com.controllers.rest;

import com.dto.UserInfoDTO;
import com.services.UserInfoService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class UserInfoController {

    private final UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/usersinfo")
    public ResponseEntity<List<UserInfoDTO>> getAllUsers() {
        List<UserInfoDTO> userInfoDTOList = userInfoService.getAllUsers();
        return !userInfoDTOList.isEmpty()
                ? ResponseEntity.status(OK).body(userInfoDTOList)
                : ResponseEntity.status(NO_CONTENT).body(userInfoDTOList);
    }

    @GetMapping("/usersinfo/{id}")
    public ResponseEntity<UserInfoDTO> getSectorById(@PathVariable(value = "id") Long id)
            throws NotFoundException {
        return ResponseEntity.ok(userInfoService.findById(id));
    }

    @PostMapping("/usersinfo")
    public ResponseEntity saveSector(@Valid @RequestBody UserInfoDTO newUsers) {
        userInfoService.save(newUsers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/usersinfo/{id}")
    public ResponseEntity updateSector(@PathVariable(value = "id") Long id,
                                       @Valid @RequestBody UserInfoDTO updatedUsers) {
        userInfoService.save(updatedUsers);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/usersinfo/{id}")
    public ResponseEntity deleteSector(@PathVariable(value = "id") Long id)
            throws NotFoundException {
        userInfoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
