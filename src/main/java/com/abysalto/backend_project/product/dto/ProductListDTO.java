package com.abysalto.backend_project.product.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class ProductListDTO {
    private String thumbnail;
    private String title;
    private BigDecimal price;
    private String shortDescription;
}
