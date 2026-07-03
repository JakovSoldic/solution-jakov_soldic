package com.abysalto.backend_project.product.service;

import com.abysalto.backend_project.mapper.ProductMapper;
import com.abysalto.backend_project.product.dto.ProductDetailDTO;
import com.abysalto.backend_project.product.dto.ProductListDTO;
import com.abysalto.backend_project.product.entity.Product;
import com.abysalto.backend_project.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    private Product product;
    private ProductListDTO dto;
    private ProductDetailDTO detailDTO;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setTitle("Test Product");
        product.setPrice(BigDecimal.valueOf(5.00));
        product.setCategory("beauty");

        dto = new ProductListDTO();
        dto.setTitle("Test Product");
        dto.setPrice(BigDecimal.valueOf(5.00));

        detailDTO = new ProductDetailDTO();
        detailDTO.setDescription("Full description");
    }

    @Test
    public void testGetAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(List.of(product));
        Mockito.when(productMapper.toListDTO(product)).thenReturn(dto);

        List<ProductListDTO> result = productService.getAllProducts();

        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getTitle());
    }

    @Test
    public void testGetProductById() {
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        Mockito.when(productMapper.toDetailDTO(product)).thenReturn(detailDTO);

        ProductDetailDTO result = productService.getProductById(1L);

        assertEquals("Full description", result.getDescription());
    }

    @Test
    public void testGetByTitle() {
        Mockito.when(productRepository.findByTitleContainingIgnoreCase("mascara")).thenReturn(List.of(product));
        Mockito.when(productMapper.toListDTO(product)).thenReturn(dto);

        List<ProductListDTO> result = productService.getByTitle("mascara");

        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getTitle());
    }

    @Test
    public void testFilterProducts() {
        Mockito.when(productRepository.findByCategoryAndPrice("beauty", BigDecimal.valueOf(5.00))).thenReturn(List.of(product));
        Mockito.when(productMapper.toListDTO(product)).thenReturn(dto);

        List<ProductListDTO> result = productService.filterProducts("beauty", BigDecimal.valueOf(5.00));

        assertEquals(1, result.size());
        assertEquals("Test Product", result.get(0).getTitle());
    }
}