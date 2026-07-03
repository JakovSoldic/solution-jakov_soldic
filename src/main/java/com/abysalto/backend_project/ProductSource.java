package com.abysalto.backend_project;

import com.abysalto.backend_project.product.entity.Product;
import java.util.List;

public interface ProductSource {
    List<Product> fetchProducts();
}