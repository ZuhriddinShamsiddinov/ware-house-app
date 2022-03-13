package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.CategoryDTO;
import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;
    
    public ApiResponse add(Category category) {
        Category save = categoryRepository.save(category);

        return new ApiResponse("Saved",true,save);

    }

}
