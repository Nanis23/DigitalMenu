package com.MenuBackend.MenuBackend.controller;

import com.MenuBackend.MenuBackend.DTO.ProductDTO;
import com.MenuBackend.MenuBackend.services.Category.CategoryService;
import com.MenuBackend.MenuBackend.services.Product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final ProductService productService;

    @PostMapping("/createProduct")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO){

        if (productService.productNameExist(productDTO.getProdName())) {
            return new ResponseEntity<>("Product already exists!", HttpStatus.NOT_ACCEPTABLE);
        }

        ProductDTO createdProductDTO = productService.createProduct(productDTO);

        if (createdProductDTO == null) {
            return new ResponseEntity<>("Product not created  try again!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(createdProductDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateProduct/{prodId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long prodId, @RequestBody ProductDTO productDTO){
        try {
            ProductDTO updatedProductDTO = productService.updateProduct(prodId, productDTO);
            return new ResponseEntity<>(updatedProductDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/deleteProduct/{prodId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long prodId){
        productService.deleteProduct(prodId);
        return ResponseEntity.ok(null);
    }


    @GetMapping("/productsList")
    public ResponseEntity<?> getAllProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long prodId){
        ProductDTO productDTO = productService.getProductById(prodId);
        return ResponseEntity.ok(productDTO);
    }

}
