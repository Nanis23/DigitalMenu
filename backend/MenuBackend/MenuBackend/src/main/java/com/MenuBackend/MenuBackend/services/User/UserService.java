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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public User createUser(UserActionsDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        user.setRole(UserRoles.USER);
        user.setProfilePic(userDto.getProfilePic());

        return userRepository.save(user);
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

    public User mapToEntity(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setProfilePic(userDTO.getProfilePic());
        user.setRole(userDTO.getRole());
        return user;
    }

    public void deleteUser(Long uid){
        userRepository.deleteById(uid);
    }



    public boolean userUsernameExists(String username) {
        return userRepository.findFirstByUsername(username).isPresent();
    }

    public UserDTO updateUser(Long uid, UserActionsDTO userDTO) {
        User user= userRepository.findById(uid)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (userDTO.getUsername() != null) {
            user.setUsername(userDTO.getUsername());
        }

        if (userDTO.getEmail() != null) {
            user.setEmail(userDTO.getEmail());
        }

        if (userDTO.getPassword() != null){
            user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        }

        if (userDTO.getProfilePic() != null){
            user.setProfilePic(userDTO.getProfilePic());
        }

        return mapToDTO(userRepository.save(user));


    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findFirstByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(Long uid) {
        Optional<User> optionalUser = userRepository.findById(uid);
        return optionalUser.map(this::mapToDTO).orElse(null);
    }


}
