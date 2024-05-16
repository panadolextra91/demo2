package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Wishlist;
import com.example.demo.model.WishlistItem;
import com.example.demo.repository.WishlistItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistItemService {

    @Autowired
    private WishlistItemRepository wishlistItemRepository;
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private ProductService productService;

    @Transactional
    public List<WishlistItem> findAllWishlistItems() {
        return wishlistItemRepository.findAll();
    }

    @Transactional
    public WishlistItem findWishlistItemById(Long id) {
        return wishlistItemRepository.findById(id).orElse(null);
    }

    @Transactional
    public WishlistItem saveWishlistItem(WishlistItem wishlistItem) {
        return wishlistItemRepository.save(wishlistItem);
    }

    @Transactional
    public void deleteWishlistItem(Long id) {
        wishlistItemRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllWishlistItems() {
        wishlistItemRepository.deleteAll();
    }

    @Transactional
    public List<WishlistItem> findWishlistItemsByWishlistId(Long wishlistId) {
        return wishlistItemRepository.findByWishlistWishlistId(wishlistId);
    }

    @Transactional
    public List<WishlistItem> findWishlistItemsByProductId(Long productId) {
        return wishlistItemRepository.findByProductProductId(productId);
    }

    @Transactional
    public void bulkDeleteWishlistItems(List<Long> wishlistItemIds) {
        wishlistItemRepository.deleteAllById(wishlistItemIds);
    }

    @Transactional
    public void updateWishlistItemQuantity(Long wishlistItemId, Integer quantity) {
        WishlistItem wishlistItem = wishlistItemRepository.findById(wishlistItemId).orElse(null);
        if (wishlistItem != null) {
            wishlistItem.setQuantity(quantity);
            wishlistItem.setSubtotalPrice(wishlistItem.getProduct().getProductPrice() * quantity);
            wishlistItemRepository.save(wishlistItem);
        }
    }
}
