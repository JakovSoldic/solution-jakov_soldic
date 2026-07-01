package com.abysalto.backend_project.product.controller;

import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

//    @GetMapping("/{productId}")
//    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable Long productId){
//        return ResponseEntity.ok(productService.getProductById(productId));
//    }
    
}
