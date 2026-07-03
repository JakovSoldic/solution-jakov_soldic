package com.abysalto.backend_project.product.controller;

import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Endpoints za upravljanje proizvodima")
public class ProductController {
    private ProductService productService;
    
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @GetMapping
    @Operation(summary = "Get all products", description = "Returns a list of products with basic info")
    public ResponseEntity<List<ProductListDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/{productId}")
    @Operation(summary = "Get one product", description = "Returns description of a found product by its ID")
    public ResponseEntity<ProductDetailDTO> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("/search")
    @Operation(summary = "Search by title")
    public ResponseEntity<List<ProductListDTO>> searchByTitle(@RequestParam String title) {
        return ResponseEntity.ok(productService.getByTitle(title));
    }

    @GetMapping("/filter")
    @Operation(summary = "Filter products", description = "Filtering products by category and price")
    public ResponseEntity<List<ProductListDTO>> filterProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) BigDecimal price) {
        return ResponseEntity.ok(productService.filterProducts(category, price));
    }
    
}
