package com.example.demo.repository;

import com.example.demo.model.WishlistItem;
import com.example.demo.model.Wishlist;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistItemRepository extends JpaRepository<WishlistItem, Long> {
    List<WishlistItem> findByWishlistWishlistId(Long wishlistId);
    List<WishlistItem> findByProductProductId(Long productId);
    Optional<WishlistItem> findByWishlistAndProduct(Wishlist wishlist, Product product);
}
