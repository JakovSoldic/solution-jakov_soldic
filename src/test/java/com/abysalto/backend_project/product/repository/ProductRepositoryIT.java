package com.abysalto.backend_project.product.repository;

import com.abysalto.backend_project.product.entity.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

@DataJpaTest
public class ProductRepositoryIT {

    @Autowired
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
        product.setTitle("Mascara");
        product.setPrice(BigDecimal.valueOf(5.00));
        product.setCategory("beauty");
        product.setDescription("Test description longer than one hundred characters so it does not crash the mapper");
        productRepository.save(product);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    public void testFindByTitleContainingIgnoreCase() {
        List<Product> result = productRepository.findByTitleContainingIgnoreCase("mascara");
        assertEquals(1, result.size());
        assertEquals("Mascara", result.get(0).getTitle());
    }

    @Test
    public void testFindByCategory() {
        List<Product> result = productRepository.findByCategory("beauty");
        assertEquals(1, result.size());
        assertEquals("beauty", result.get(0).getCategory());
    }

    @Test
    public void testFindByPrice() {
        List<Product> result = productRepository.findByPrice(BigDecimal.valueOf(5.00));
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByCategoryAndPrice() {
        List<Product> result = productRepository.findByCategoryAndPrice("beauty", BigDecimal.valueOf(5.00));
        assertEquals(1, result.size());
        assertEquals("beauty", result.get(0).getCategory());
    }

}
