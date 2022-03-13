package com.example.warehouseapp.controller;

import com.example.warehouseapp.dto.ApiResponse;
import com.example.warehouseapp.dto.ProductDTO;
import com.example.warehouseapp.entity.Product;
import com.example.warehouseapp.repository.CategoryRepository;
import com.example.warehouseapp.repository.ProductRepository;
import com.example.warehouseapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.nio.file.OpenOption;
import java.util.Optional;

@Controller
@RequestMapping("/product")
public class ProductController {


    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;
    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping
    public String getProduct(Model model){
      model.addAttribute("listProduct",productRepository.findByActiveTrue());
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

  @GetMapping("/delete{id}")
    public String delete(Model model, @PathVariable Integer id){
     productRepository.deleteById(id);
     return "redirect:/product";
  }

  @GetMapping("/edit{id}")
    public String getEditProduct(Model model,@PathVariable Integer id){
      Optional<Product>product=productRepository.findById(id);
   if(!product.isEmpty())return "Error";

   model.addAttribute("edited",product.get());
   model.addAttribute("categoryList",categoryRepository.findByActiveTrue());
   return "product/product-edit";
  }
@PostMapping("/edit{id}")
    public String editSave(@PathVariable Integer id, @ModelAttribute ProductDTO productDTO){
   ApiResponse response = productService.edit(id,productDTO);
    System.out.println(response);
    return "redirect:/product";

}


}
