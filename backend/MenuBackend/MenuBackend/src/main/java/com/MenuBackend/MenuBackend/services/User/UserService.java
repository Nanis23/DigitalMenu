package com.MenuBackend.MenuBackend.services.User;

import com.MenuBackend.MenuBackend.DTO.UserActionsDTO;
import com.MenuBackend.MenuBackend.DTO.UserDTO;
import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.enums.UserRoles;
import com.MenuBackend.MenuBackend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @PostConstruct
    public void createUserAdmin() {

        User adminAccount = userRepository.findByRole(UserRoles.ADMIN);
        if (adminAccount == null) {
            User user = new User();
            user.setUsername("Admin");
            user.setEmail("admin@admin.com");
            user.setPassword(new BCryptPasswordEncoder().encode("admin12345"));

            UserRoles role = UserRoles.ADMIN;
            user.setRole(role);

            user.setProfilePic("img.jpg");
            userRepository.save(user);

            System.out.println("Admin account created successfully");
        }

    }


    public UserDTO createUser(UserActionsDTO registerUserDTO) {
        User user = new User();
        user.setUsername(registerUserDTO.getUsername());
        user.setEmail(registerUserDTO.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(registerUserDTO.getPassword()));
        user.setRole(UserRoles.USER);
        user.setProfilePic(registerUserDTO.getProfilePic());
        User createdUser = userRepository.save(user);

        UserDTO userDTO = new UserDTO();
        userDTO.setId(createdUser.getUid());
        //return userDTO;
        return mapToDTO(user);

    }

    public UserDTO mapToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getUid());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());

        dto.setProfilePic(user.getProfilePic());
        dto.setRole(user.getRole());
        return dto;
    }

    public void deleteUser(Long uid){
        userRepository.deleteById(uid);
    }



    public boolean userUsernameExists(String username) {
        return userRepository.findFirstByUsername(username).isPresent();
    }

    public UserDTO updateUser(Long uid, UserActionsDTO updateUserDTO) {
        User user= userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (updateUserDTO.getUsername() != null) {
            user.setUsername(updateUserDTO.getUsername());
        }

        if (updateUserDTO.getEmail() != null) {
            user.setEmail(updateUserDTO.getEmail());
        }

        if (updateUserDTO.getPassword() != null){
            user.setPassword(new BCryptPasswordEncoder().encode(updateUserDTO.getPassword()));
        }

        if (updateUserDTO.getProfilePic() != null){
            user.setProfilePic(updateUserDTO.getProfilePic());
        }

        User updatedUser= userRepository.save(user);

        return mapToDTO(updatedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }



}
