package com.MenuBackend.MenuBackend.DTO;

import lombok.Data;

@Data
public class AuthenticationResponseDTO {

    private String jwtToken;

    private String userRole;

    private Long userId;
}
