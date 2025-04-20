package com.MenuBackend.MenuBackend.controller;

import com.MenuBackend.MenuBackend.DTO.ProductDTO;
import com.MenuBackend.MenuBackend.DTO.UserActionsDTO;
import com.MenuBackend.MenuBackend.DTO.UserDTO;
import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.services.Product.ProductService;
import com.MenuBackend.MenuBackend.services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<?> registrationRequest(@RequestBody UserActionsDTO userDTO) {
        if (userService.userUsernameExists(userDTO.getUsername())) {
            return new ResponseEntity<>("Username already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        User createdUserDTO = userService.createUser(userDTO);

        if (createdUserDTO == null) {
            return new ResponseEntity<>("User not created  try again!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>( userService.mapToDTO(createdUserDTO), HttpStatus.CREATED);
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
