package com.example.demo.repository;

import com.example.demo.model.Customer;
import com.example.demo.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    Optional<Wishlist> findByCustomer(Customer customer);
    List<Wishlist> findByCustomerCustomerId(Integer customerId); // Use Integer
}
