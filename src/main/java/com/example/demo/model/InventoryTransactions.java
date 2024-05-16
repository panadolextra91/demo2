package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "Inventory_Transactions")
public class InventoryTransactions {
    @Id
    @Column(name = "transactionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "transactionDate")
    private Date transactionDate;
    @Column(name = "transactionType")
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    public InventoryTransactions() {}

    // Getters and Setters
    public Long getTransactionId() { return transactionId; }
    public void setTransactionId(Long transactionId) { this.transactionId = transactionId; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Date getTransactionDate() { return transactionDate; }
    public void setTransactionDate(Date transactionDate) { this.transactionDate = transactionDate; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
}
