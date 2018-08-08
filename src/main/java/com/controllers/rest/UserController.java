package com.controllers.rest;

import com.dto.UserDTO;
import com.model.User;
import com.model.UsersInfo;
import com.services.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
        }
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getSectorById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveSector(@Valid @RequestBody UserDTO newUsers) {
        User user = userService.save(newUsers);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateSector(@PathVariable(value = "id") Long id, @Valid @RequestBody UserDTO updatedUsers)
            throws NotFoundException {
        User user = userService.update(id, updatedUsers);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UsersInfo> deleteSector(@PathVariable(value = "id") Long id) throws NotFoundException {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
