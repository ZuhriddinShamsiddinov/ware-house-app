package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.ProductDTO;
import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.repository.CategoryRepository;
import com.example.warehouseapp.repository.MeasurementRepository;
import com.example.warehouseapp.repository.ProductRepository;
import com.example.warehouseapp.service.AttachmentService;
import com.example.warehouseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;


import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    MeasurementRepository measurementRepository;
    @Autowired
    AttachmentService attachmentService;


    @GetMapping
    public String getProduct(Model model) {
        model.addAttribute("listProduct", productRepository.findAllByActiveTrue());
        return "product/product";
    }

    @GetMapping("/add")
    public String getAddPage(Model model) {
        model.addAttribute("CategorytList", categoryRepository.findAllByActiveTrue());
        model.addAttribute("measurementList", measurementRepository.findAllByActiveTrue());
        return "product/product-add";
    }

    @PostMapping("/add")
    public String addPage(@ModelAttribute ProductDTO productDTO) {
        productService.save(productDTO);
        return "redirect:/product";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        Product product = optionalProduct.get();
        product.setActive(false);
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping("/edit{id}")
    public String getEditProduct(Model model, @PathVariable Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) return "error";

        model.addAttribute("product", product);
        model.addAttribute("categoryList", categoryRepository.findAllByActiveTrue());
        model.addAttribute("measurementList", measurementRepository.findAllByActiveTrue());
        return "product/product-edit";
    }

    @PostMapping("/edit{id}")
    public String editSave(@PathVariable Integer id, @ModelAttribute ProductDTO productDTO) {
        ApiResponse response = productService.edit(id, productDTO);
        System.out.println(response);
        return "redirect:/product";

    }

    @PostMapping("/uploadFiles")
    public List<UUID> uploadFile(MultipartHttpServletRequest request) {
        return attachmentService.upload(request);
    }

    @GetMapping("/download/{id}")
    public void download(@PathVariable UUID id, HttpServletResponse response) {
        attachmentService.download(id, response);
    }


}
