package com.MenuBackend.MenuBackend.controller;


import com.MenuBackend.MenuBackend.DTO.AuthenticationRequestDTO;
import com.MenuBackend.MenuBackend.DTO.AuthenticationResponseDTO;
import com.MenuBackend.MenuBackend.Utils.JWTUtil;
import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.repository.UserRepository;
import com.MenuBackend.MenuBackend.services.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    private final UserRepository userRepository;

    @PostMapping("/login")
    public AuthenticationResponseDTO createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO) throws BadCredentialsException
            , DisabledException
            , UsernameNotFoundException {
        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(), authenticationRequestDTO.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect username or password!");
        }

        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequestDTO.getUsername());
        Optional<User> optionalUser = userRepository.findFirstByUsername(userDetails.getUsername());

        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponseDTO authenticationResponseDTO = new AuthenticationResponseDTO();

        if (optionalUser.isPresent()) {
            authenticationResponseDTO.setJwtToken(jwt);
            authenticationResponseDTO.setUserId(optionalUser.get().getUid());
            authenticationResponseDTO.setUserRole(optionalUser.get().getRole().name());
        }

        return authenticationResponseDTO;
    }


}
