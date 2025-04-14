package com.MenuBackend.MenuBackend.DTO;

import lombok.Data;

@Data
public class RegisterUserDTO {

    private String username;

    private String email;

    private String password;

    private String profilePic;
}
