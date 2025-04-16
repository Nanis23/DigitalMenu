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

    private final CategoryService categoryService;

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

//    Veprimet CRUD me Category

    @PostMapping("/createCategory")
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDTO) {
        if (categoryService.categoryNameExist(categoryDTO.getCatName())) {
            return new ResponseEntity<>("Category already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        CategoryDTO createdCategoryDTO = categoryService.createCategory(categoryDTO);

        if (createdCategoryDTO == null) {
            return new ResponseEntity<>("Category not created  try again!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdCategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{catId}")
    public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable Long catId) {
        try {
            CategoryDTO updatedCategoryDTO = categoryService.updateCategory(catId, categoryDTO);
            return new ResponseEntity<>(updatedCategoryDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteCategory/{catId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long catId) {
        categoryService.deleteCategory(catId);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/categoryList")
    public ResponseEntity<?> getAllCategories() {
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @GetMapping("/category/{catId}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long catId) {
        CategoryDTO categoryDTO = categoryService.getCategoryById(catId);
        return ResponseEntity.ok(categoryDTO);
    }
}
