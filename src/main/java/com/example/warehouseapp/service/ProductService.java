package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.ProductDTO;
import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.repository.CategoryRepository;
import com.example.warehouseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;


public ApiResponse add(Product product){
    Product save = productRepository.save(product);
    return new ApiResponse("saved",true,save);
  }


    public ApiResponse edit(Integer id, ProductDTO productDTO) {
        Optional<Product>optionalProduct=productRepository.findById(id);
        Product product=optionalProduct.get();

        Optional<Category>optionalCategory=categoryRepository.findById(productDTO.getCategoryId());
        Category category=optionalCategory.get();

        product.setName(productDTO.getName());
        product.setCategory(category);

     productRepository.save(product);
     return new ApiResponse("Updated",true);
    }
}
