package com.example.demo.controller;

import com.example.demo.model.Wishlist;
import com.example.demo.service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlists")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<List<Wishlist>> getAllWishlists() {
        return ResponseEntity.ok(wishlistService.findAllWishlists());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Wishlist> getWishlistById(@PathVariable Long id) {
        Wishlist wishlist = wishlistService.findWishlistById(id);
        if (wishlist != null) {
            return ResponseEntity.ok(wishlist);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Wishlist> createWishlist(@RequestBody Wishlist wishlist) {
        return ResponseEntity.ok(wishlistService.saveWishlist(wishlist));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Wishlist> updateWishlist(@PathVariable Long id, @RequestBody Wishlist wishlist) {
        Wishlist existingWishlist = wishlistService.findWishlistById(id);
        if (existingWishlist != null) {
            wishlist.setWishlistId(id);
            return ResponseEntity.ok(wishlistService.saveWishlist(wishlist));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWishlist(@PathVariable Long id) {
        wishlistService.deleteWishlist(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/filterByCustomer")
    public ResponseEntity<List<Wishlist>> filterWishlistsByCustomer(@RequestParam int customerId) {
        return ResponseEntity.ok(wishlistService.findWishlistsByCustomerId(customerId));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteWishlists(@RequestBody List<Long> wishlistIds) {
        wishlistService.bulkDeleteWishlists(wishlistIds);
        return ResponseEntity.noContent().build();
    }
}
