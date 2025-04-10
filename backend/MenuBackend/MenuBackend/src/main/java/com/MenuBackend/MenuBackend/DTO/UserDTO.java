package com.MenuBackend.MenuBackend.DTO;

import com.MenuBackend.MenuBackend.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private long id;

    private String username;

    private String email;

    private String profilePic;

    private UserRoles role;
}
