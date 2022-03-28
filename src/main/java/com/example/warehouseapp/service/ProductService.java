package com.example.warehouseapp.service;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.ProductDTO;
import com.example.warehouseapp.entity.Attachment;
import com.example.warehouseapp.entity.Category;
import com.example.warehouseapp.entity.Measurement;
import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.repository.CategoryRepository;
import com.example.warehouseapp.repository.MeasurementRepository;
import com.example.warehouseapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;


    public ApiResponse save(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setCode(UUID.randomUUID().toString());
        Optional<Category> categoryOptional = categoryRepository.findById(productDTO.getCategoryId());
        product.setCategory(categoryOptional.get());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDTO.getMeasurementId());
        product.setMeasurement(optionalMeasurement.get());


        productRepository.save(product);
        return new ApiResponse("Save", true);
    }


    public ApiResponse edit(Integer id, ProductDTO productDTO) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();

        Optional<Category> optionalCategory = categoryRepository.findById(productDTO.getCategoryId());
        Category category = optionalCategory.get();

        product.setName(productDTO.getName());
        product.setCategory(category);

        productRepository.save(product);
        return new ApiResponse("Updated", true);
    }
}
