package com.MenuBackend.MenuBackend.repository;

import com.MenuBackend.MenuBackend.entity.Category;
import com.MenuBackend.MenuBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoriesByCatName(String catName);
}
