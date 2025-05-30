package com.MenuBackend.MenuBackend.repository;

import com.MenuBackend.MenuBackend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByProdName(String prodName);
}
