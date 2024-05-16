package com.example.demo.service;

import com.example.demo.model.Customer;
import com.example.demo.model.Wishlist;
import com.example.demo.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;
    @Autowired
    private CustomerService customerService;

    @Transactional
    public List<Wishlist> findAllWishlists() {
        return wishlistRepository.findAll();
    }

    @Transactional
    public Wishlist findWishlistById(Long id) {
        return wishlistRepository.findById(id).orElse(null);
    }

    @Transactional
    public Wishlist saveWishlist(Wishlist wishlist) {
        return wishlistRepository.save(wishlist);
    }

    @Transactional
    public void deleteWishlist(Long id) {
        wishlistRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllWishlists() {
        wishlistRepository.deleteAll();
    }

    @Transactional
    public Wishlist findOrCreateWishlistByCustomerId(Integer customerId) {
        Customer customer = customerService.findCustomerById(customerId);
        if (customer == null) {
            return null; // Or handle appropriately
        }
        return wishlistRepository.findByCustomer(customer).orElseGet(() -> {
            Wishlist newWishlist = new Wishlist();
            newWishlist.setCustomer(customer);
            return saveWishlist(newWishlist);
        });
    }

    @Transactional
    public List<Wishlist> findWishlistsByCustomerId(Integer customerId) {
        return wishlistRepository.findByCustomerCustomerId(customerId);
    }

    @Transactional
    public void bulkDeleteWishlists(List<Long> wishlistIds) {
        wishlistRepository.deleteAllById(wishlistIds);
    }
}
