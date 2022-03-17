package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.CategoryDTO;
import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse save(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setName(categoryDTO.getName());
        if (categoryDTO.getParentId() != null) {
            Optional<Category> optionalCategory = categoryRepository.findById(categoryDTO.getParentId());
            category.setParentCategory(optionalCategory.get());
        } else {
            category.setParentCategory(null);
        }
        categoryRepository.save(category);
        return new ApiResponse("Saved!", true);
    }

    public ApiResponse edit(Integer id, CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        if (categoryOptional.isEmpty()) return new ApiResponse("NOT", false);

        Category edit = categoryOptional.get();
        if (categoryDTO.getName() != null) edit.setName(categoryDTO.getName());

        if (categoryDTO.getParentId() != null) {
            Optional<Category> byId = categoryRepository.findById(categoryDTO.getParentId());
            edit.setParentCategory(byId.get());
        }

        categoryRepository.save(edit);
        return new ApiResponse("EDIT!", true);
    }

}
