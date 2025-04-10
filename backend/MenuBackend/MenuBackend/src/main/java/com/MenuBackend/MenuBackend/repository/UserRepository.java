package com.MenuBackend.MenuBackend.repository;

import com.MenuBackend.MenuBackend.entity.User;
import com.MenuBackend.MenuBackend.enums.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRole(UserRoles role);
}
