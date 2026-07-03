package com.abysalto.backend_project.product.controller;

import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.service.ProductService;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    
    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private ProductListDTO dto;
    private ProductDetailDTO detailDTO;
    
    public ProductControllerTest() {
    }

    @BeforeEach
    public void setUp() {
        dto = new ProductListDTO();
        dto.setTitle("Test Product");
        dto.setPrice(BigDecimal.valueOf(5.00));

        detailDTO = new ProductDetailDTO();
        detailDTO.setDescription("Full description");
    }

    @Test
    public void testGetAllProducts() {
        System.out.println("getAllProducts");
        Mockito.when(productService.getAllProducts()).thenReturn(List.of(dto));
        
        ProductController instance = this.productController;
        ResponseEntity<List<ProductListDTO>> result = instance.getAllProducts();
        
        assertEquals(200, result.getStatusCode().value());
        assertEquals(1, result.getBody().size());
        assertEquals("Test Product", result.getBody().get(0).getTitle());

    }


    @Test
    public void testGetProductById() {
        System.out.println("getProductById");
        Mockito.when(productService.getProductById(1L)).thenReturn(detailDTO);
        
        ProductController instance = this.productController;
        ResponseEntity<ProductDetailDTO> result = instance.getProductById(1L);
        
        assertEquals(200, result.getStatusCode().value());
        assertEquals("Full description", result.getBody().getDescription());
    }


    @Test
    public void testSearchByTitle() {
        System.out.println("searchByTitle");
        Mockito.when(productService.getByTitle("Test Product")).thenReturn(List.of(dto));

        ProductController instance = this.productController;
        ResponseEntity<List<ProductListDTO>> result = instance.searchByTitle("Test Product");

        assertEquals(200, result.getStatusCode().value());
        assertEquals(1, result.getBody().size());
        assertEquals("Test Product", result.getBody().get(0).getTitle());
    }

    @Test
    public void testFilterProducts() {
        String category = "beauty";
        BigDecimal price = BigDecimal.valueOf(5.00);

        Mockito.when(productService.filterProducts("beauty", BigDecimal.valueOf(5.00))).thenReturn(List.of(dto));

        ResponseEntity<List<ProductListDTO>> result = productController.filterProducts(category, price);

        assertEquals(200, result.getStatusCode().value());
        assertEquals(1, result.getBody().size());
        assertEquals("Test Product", result.getBody().get(0).getTitle());
    }
    
}
