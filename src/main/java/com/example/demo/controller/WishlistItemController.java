package com.example.demo.controller;

import com.example.demo.model.WishlistItem;
import com.example.demo.service.WishlistItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist-items")
public class WishlistItemController {

    @Autowired
    private WishlistItemService wishlistItemService;

    @GetMapping
    public ResponseEntity<List<WishlistItem>> getAllWishlistItems() {
        return ResponseEntity.ok(wishlistItemService.findAllWishlistItems());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WishlistItem> getWishlistItemById(@PathVariable Long id) {
        WishlistItem wishlistItem = wishlistItemService.findWishlistItemById(id);
        if (wishlistItem != null) {
            return ResponseEntity.ok(wishlistItem);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<WishlistItem> createWishlistItem(@RequestBody WishlistItem wishlistItem) {
        return ResponseEntity.ok(wishlistItemService.saveWishlistItem(wishlistItem));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WishlistItem> updateWishlistItem(@PathVariable Long id, @RequestBody WishlistItem wishlistItem) {
        WishlistItem existingWishlistItem = wishlistItemService.findWishlistItemById(id);
        if (existingWishlistItem != null) {
            wishlistItem.setWishlistItemId(id);
            return ResponseEntity.ok(wishlistItemService.saveWishlistItem(wishlistItem));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlistItem(@PathVariable Long id) {
        wishlistItemService.deleteWishlistItem(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByWishlist")
    public ResponseEntity<List<WishlistItem>> searchWishlistItemsByWishlist(@RequestParam Long wishlistId) {
        return ResponseEntity.ok(wishlistItemService.findWishlistItemsByWishlistId(wishlistId));
    }

    @GetMapping("/searchByProduct")
    public ResponseEntity<List<WishlistItem>> searchWishlistItemsByProduct(@RequestParam Long productId) {
        return ResponseEntity.ok(wishlistItemService.findWishlistItemsByProductId(productId));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteWishlistItems(@RequestBody List<Long> wishlistItemIds) {
        wishlistItemService.bulkDeleteWishlistItems(wishlistItemIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateQuantity")
    public ResponseEntity<Void> updateWishlistItemQuantity(@PathVariable Long id, @RequestParam Integer quantity) {
        wishlistItemService.updateWishlistItemQuantity(id, quantity);
        return ResponseEntity.noContent().build();
    }
}
