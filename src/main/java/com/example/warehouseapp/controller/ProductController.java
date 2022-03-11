package com.example.warehouseapp.controller;

import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.repository.ProductRepository;
import com.example.warehouseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;


    @GetMapping
    public String getProduct(Model model){
      model.addAttribute("listProduct",productRepository.findAll());
      return "product/product";
    }

   @GetMapping("/add")
    public String getAddPage(){
     return "product/product-add";
}

  @PostMapping("/add")
    public String addPage(Model model, @ModelAttribute Product product){
     productService.add(product);
     return "redirect:/product";
  }

}
