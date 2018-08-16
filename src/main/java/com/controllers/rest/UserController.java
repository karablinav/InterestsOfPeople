package com.controllers.rest;

import com.dto.UserDTO;
import com.services.UserService;
import com.services.impl.UserServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return !users.isEmpty()
                ? ResponseEntity.status(OK).body(users)
                : ResponseEntity.status(NO_CONTENT).body(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long id)
            throws NotFoundException {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PostMapping("/users")
    public ResponseEntity saveUser(@Valid @RequestBody UserDTO newUsers) {
        userService.save(newUsers);
        return ResponseEntity.status(CREATED).build();
    }

    @PutMapping("/users/{id}")
    public ResponseEntity updateUser(@PathVariable(value = "id") Long id,
                                             @Valid @RequestBody UserDTO updatedUsers)
            throws NotFoundException {
        userService.update(id, updatedUsers);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable(value = "id") Long id) throws NotFoundException {
        userService.delete(id);
        return ResponseEntity.status(OK).build();
    }


}
