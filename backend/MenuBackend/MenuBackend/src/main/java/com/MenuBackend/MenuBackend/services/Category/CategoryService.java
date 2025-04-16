package com.MenuBackend.MenuBackend.services.Category;

import com.MenuBackend.MenuBackend.DTO.CategoryDTO;
import com.MenuBackend.MenuBackend.entity.Category;
import com.MenuBackend.MenuBackend.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        Category category = new Category();

        category.setCatName(categoryDTO.getCatName());

        Category createdCategory = categoryRepository.save(category);
        CategoryDTO catDTO = new CategoryDTO();
        catDTO.setCatId(createdCategory.getCatId());

        return mapToDTO(createdCategory);
    }

    public CategoryDTO mapToDTO(Category category) {
        CategoryDTO catDTO= new CategoryDTO();
        catDTO.setCatId(category.getCatId());
        catDTO.setCatName(category.getCatName());
        return catDTO;
    }
}
