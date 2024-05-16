package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    @Column(name = "productID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "productPrice")
    private Double productPrice;
    @Column(name = "productName")
    private String productName;
    @Column(name = "stock")
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "area_id", nullable = false)
    private Area area;

    @ManyToOne
    @JoinColumn(name = "supplierId", nullable = false)
    private Suppliers supplier;

    public Product() {}

    // Getters and Setters
    public Long getProductId() { return productId; }
    public void setProductId(Long productId) { this.productId = productId; }
    public Double getProductPrice() { return productPrice; }
    public void setProductPrice(Double productPrice) { this.productPrice = productPrice; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public Integer getStock() { return stock; }
    public void setStock(Integer stock) { this.stock = stock; }
    public Area getArea() { return area; }
    public void setArea(Area area) { this.area = area; }
    public Suppliers getSupplier() { return supplier; }
    public void setSupplier(Suppliers supplier) { this.supplier = supplier; }
}
