package com.MenuBackend.MenuBackend.services.User;

import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.enums.UserRoles;
import com.MenuBackend.MenuBackend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User createUser (User user) {
        return userRepository.save(user);
    }

    @PostConstruct
    public void createUserAdmin () {

        User adminAccount= userRepository.findByRole(UserRoles.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setUsername("Admin");
            user.setEmail("admin@admin.com");
            user.setPassword("admin123");

            UserRoles role = UserRoles.ADMIN;
            user.setRole(role);

            user.setProfilePic("img.jpg");
            userRepository.save(user);

            System.out.println("Admin account created successfully");
        }

    }
}
