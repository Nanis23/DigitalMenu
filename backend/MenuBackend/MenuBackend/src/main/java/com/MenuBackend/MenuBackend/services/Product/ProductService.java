package com.MenuBackend.MenuBackend.services.Product;

import com.MenuBackend.MenuBackend.DTO.ProductDTO;
import com.MenuBackend.MenuBackend.entity.Category;
import com.MenuBackend.MenuBackend.entity.Product;
import com.MenuBackend.MenuBackend.repository.CategoryRepository;
import com.MenuBackend.MenuBackend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDTO mapToDTO(Product product) {
        ProductDTO productDTO= new ProductDTO();


        productDTO.setProdId(product.getProdId());
        productDTO.setProdName(product.getProdName());
        productDTO.setProdDescription(product.getProdDescription());
        productDTO.setProdPrice(product.getProdPrice());
        productDTO.setProdPicture(product.getProdPicture());
        productDTO.setProdAvailability(product.getProdAvailability());
       if (product.getCategory() != null){
           productDTO.setCatId(product.getCategory().getCatId());
           productDTO.setCatName(product.getCategory().getCatName());
       }
        return productDTO;
    }

    public ProductDTO createProduct(ProductDTO productDTO) {
        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCatId());

        if (optionalCategory.isPresent()) {
            Product product = new Product();

            product.setProdName(productDTO.getProdName());
            product.setProdDescription(productDTO.getProdDescription());
            product.setProdPrice(productDTO.getProdPrice());
            product.setProdPicture(productDTO.getProdPicture());
            product.setProdAvailability(productDTO.getProdAvailability());
            product.setCategory(optionalCategory.get());

            Product createdProduct = productRepository.save(product);

            return mapToDTO(createdProduct);
        } else {
            throw new RuntimeException("Category not found with ID: " + productDTO.getCatId());
        }
    }

    public void deleteProduct(Long prodId){ productRepository.deleteById(prodId);}

    public ProductDTO updateProduct (Long prodId, ProductDTO productDTO){
        Product product= productRepository.findById(prodId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (productDTO.getProdName()!= null){
            product.setProdName(productDTO.getProdName());
        }

        if (productDTO.getProdDescription()!= null){
            product.setProdDescription(productDTO.getProdDescription());
        }

        if (productDTO.getProdPrice() != null){
            product.setProdPrice(productDTO.getProdPrice());
        }

        if (productDTO.getProdPicture()!= null){
            product.setProdPicture(productDTO.getProdPicture());
        }

        if (productDTO.getProdAvailability()!= null){
            product.setProdAvailability(productDTO.getProdAvailability());
        }

        if (productDTO.getCatId()!= null){
            Category category= categoryRepository.findById(productDTO.getCatId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            product.setCategory(category);
        }

        return mapToDTO(productRepository.save(product));
    }

    public List<ProductDTO> getAllProducts(){
        return productRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());

    }

    public ProductDTO getProductById(Long prodId){
        Optional<Product> optionalProduct = productRepository.findById(prodId);
        return optionalProduct.map(this::mapToDTO).orElse(null);
    }

    public boolean productNameExist(String prodName) {
        return productRepository.findProductByProdName(prodName).isPresent();
    }

}
