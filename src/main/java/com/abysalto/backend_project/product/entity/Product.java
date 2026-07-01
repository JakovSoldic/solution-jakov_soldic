package com.abysalto.backend_project.product.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "category")
    private String category;

    @Column(name = "brand")
    private String brand;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "availability_status")
    private String availabilityStatus;

    @Column(name = "discount_percentage")
    private Double discountPercentage;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "return_policy")
    private String returnPolicy;

    @Column(name = "shipping_information")
    private String shippingInformation;

    @Column(name = "images", columnDefinition = "TEXT")
    private String images;

}
