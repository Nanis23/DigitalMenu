package com.MenuBackend.MenuBackend.entity;

import com.MenuBackend.MenuBackend.enums.UserRoles;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String username;

    private String password;

    private String email;

    private String profilePic;

    @Enumerated(EnumType.STRING)
    private UserRoles role;
}
