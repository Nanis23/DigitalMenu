package com.MenuBackend.MenuBackend.services.Category;

import com.MenuBackend.MenuBackend.DTO.CategoryDTO;
import com.MenuBackend.MenuBackend.entity.Category;
import com.MenuBackend.MenuBackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setCatName(categoryDTO.getCatName());

        Category createdCategory = categoryRepository.save(category);

        return mapToDTO(createdCategory);
    }

    public void deleteCategory(Long catId){ categoryRepository.deleteById(catId);}

    public CategoryDTO updateCategory (Long  catId, CategoryDTO categoryDTO){
        Category category= categoryRepository.findById(catId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        if (categoryDTO.getCatName()!= null){
            category.setCatName(categoryDTO.getCatName());
        }

        return mapToDTO(categoryRepository.save(category));
    }

    public List<CategoryDTO> getAllCategories(){
        return  categoryRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public CategoryDTO getCategoryById(Long catId){
        Optional<Category> optionalCategory = categoryRepository.findById(catId);
        return optionalCategory.map(this::mapToDTO).orElse(null);
    }

    public CategoryDTO mapToDTO(Category category) {
        CategoryDTO catDTO= new CategoryDTO();
        catDTO.setCatId(category.getCatId());
        catDTO.setCatName(category.getCatName());
        return catDTO;
    }

    public Category mapToEntity (CategoryDTO categoryDTO){
        Category category = new Category();
        category.setCatName(categoryDTO.getCatName());
        return category;
    }

    public boolean categoryNameExist(String catName) {
        return categoryRepository.findCategoriesByCatName(catName).isPresent();
    }
}
