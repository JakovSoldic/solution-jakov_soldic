package com.abysalto.backend_project.product.service;

import com.abysalto.backend_project.mapper.ProductMapper;
import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    
    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }
    
    public List<ProductListDTO> getAllProducts() {
        return productRepository.findAll().stream().map(productMapper::toListDTO).toList();
    }
    
    public ProductDetailDTO getProductById(Long productId) {
        return productMapper.toDetailDTO(productRepository.findById(productId).orElse(null));
    }
    
    public List<ProductListDTO> filterProducts(String category,
                                               BigDecimal price) {
        
        if (category != null && price != null) {
            return productRepository.findByCategoryAndPrice(category, price).stream().map(productMapper::toListDTO).toList();                                 
        } else if (category != null) {
            return productRepository.findByCategory(category).stream().map(productMapper::toListDTO).toList();
        } else if (price != null) {
            return productRepository.findByPrice(price).stream().map(productMapper::toListDTO).toList();
        } else {
            return getAllProducts();
        }
        
    }
    
    public List<ProductListDTO> getByTitle(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title).stream().map(productMapper::toListDTO).toList();
    }
}
