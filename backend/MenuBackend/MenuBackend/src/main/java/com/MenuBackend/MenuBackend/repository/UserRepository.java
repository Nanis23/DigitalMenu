package com.MenuBackend.MenuBackend.repository;

import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findFirstByUsername(String username);
    User findByRole(UserRoles role);
}
