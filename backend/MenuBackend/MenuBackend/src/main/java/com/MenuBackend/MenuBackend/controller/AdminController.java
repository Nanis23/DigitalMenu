package com.MenuBackend.MenuBackend.controller;

import com.MenuBackend.MenuBackend.DTO.CategoryDTO;
import com.MenuBackend.MenuBackend.DTO.UserActionsDTO;
import com.MenuBackend.MenuBackend.DTO.UserDTO;
import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.services.Category.CategoryService;
import com.MenuBackend.MenuBackend.services.User.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;



//    @GetMapping("/userList")
//    public ResponseEntity<?> getAllUsers() {
//        return ResponseEntity.ok(userService.getAllUsers());
//    }
//
//    @GetMapping("/user/{uid}")
//    public ResponseEntity<UserDTO> getUserById(@PathVariable Long uid) {
//        UserDTO userDTO = userService.getUserById(uid);
//        return ResponseEntity.ok(userDTO);
//    }


//    Veprimet CRUD me Category


}
