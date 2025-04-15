package com.MenuBackend.MenuBackend.controller;

import com.MenuBackend.MenuBackend.DTO.UserActionsDTO;
import com.MenuBackend.MenuBackend.DTO.UserDTO;
import com.MenuBackend.MenuBackend.services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @GetMapping("/userList")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/user/{uid}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long uid) {
        UserDTO userDTO = userService.getUserById(uid);
        return ResponseEntity.ok(userDTO);
    }
    @PostMapping("/registerUser")
    public ResponseEntity<?> registrationRequest(@RequestBody UserActionsDTO registerUserDTO) {
        if (userService.userUsernameExists(registerUserDTO.getUsername())) {
            return new ResponseEntity<>("Username already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDTO createdUserDTO = userService.createUser(registerUserDTO);

        if (createdUserDTO == null) {
            return new ResponseEntity<>("User not created  try again!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateUser/{uid}")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserActionsDTO updateUserDTO, @PathVariable Long uid) {
        try {
            UserDTO updatedUser= userService.updateUser(uid, updateUserDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteUser/{uid}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long uid){
        userService.deleteUser(uid);
        return ResponseEntity.ok(null);
    }

}
