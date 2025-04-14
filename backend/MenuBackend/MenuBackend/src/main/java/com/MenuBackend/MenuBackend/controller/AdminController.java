package com.MenuBackend.MenuBackend.controller;

import com.MenuBackend.MenuBackend.DTO.RegisterUserDTO;
import com.MenuBackend.MenuBackend.DTO.UserDTO;
import com.MenuBackend.MenuBackend.services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registrationRequest(@RequestBody RegisterUserDTO registerUserDTO) {
        if (userService.userUsernameExists(registerUserDTO.getUsername())) {
            return new ResponseEntity<>("Username already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        UserDTO createdUserDTO = userService.createUser(registerUserDTO);

        if (createdUserDTO == null) {
            return new ResponseEntity<>("User not created  try again!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdUserDTO, HttpStatus.CREATED);
    }
}
