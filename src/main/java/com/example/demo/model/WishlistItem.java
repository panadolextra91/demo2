package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "wishlist_item")
public class WishlistItem {
    @Id
    @Column(name = "wishlist_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistItemId;

    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "subtotal_price", nullable = false)
    private Double subtotalPrice;

    public WishlistItem() {}

    // Getters and Setters
    public Long getWishlistItemId() { return wishlistItemId; }
    public void setWishlistItemId(Long wishlistItemId) { this.wishlistItemId = wishlistItemId; }
    public Wishlist getWishlist() { return wishlist; }
    public void setWishlist(Wishlist wishlist) { this.wishlist = wishlist; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public Double getSubtotalPrice() { return subtotalPrice; }
    public void setSubtotalPrice(Double subtotalPrice) { this.subtotalPrice = subtotalPrice; }
}
