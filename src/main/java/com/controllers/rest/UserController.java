package com.controllers.rest;

import com.dto.UserDTO;
import com.services.impl.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserController {

    private final UserServiceImpl userServiceImpl;

    @Autowired
    public UserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userServiceImpl.getAllUsers();
        return !users.isEmpty()
                ? ResponseEntity.status(HttpStatus.OK).body(users)
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id) throws NotFoundException {
        return ResponseEntity.ok(userServiceImpl.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody UserDTO newUsers) {
        userServiceImpl.save(newUsers);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody UserDTO updatedUsers)
            throws NotFoundException {
        userServiceImpl.update(id, updatedUsers);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(value = "id") Long id) throws NotFoundException {
        userServiceImpl.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }


}
