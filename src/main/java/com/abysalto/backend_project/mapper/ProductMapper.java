package com.abysalto.backend_project.mapper;

import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.entity.Product;

public class ProductMapper {
    
    public ProductListDTO toListDTO(Product product) {
        ProductListDTO dto = new ProductListDTO();
        dto.setThumbnail(product.getThumbnail());
        dto.setTitle(product.getTitle());
        dto.setPrice(product.getPrice());
        dto.setShortDescription(product.getDescription().substring(0, 100));
        return dto;
    }
    
    public ProductDetailDTO toDetailDTO(Product product) {
        ProductDetailDTO dto = new ProductDetailDTO();
        dto.setDescription(product.getDescription());
        return dto;
    }
}
