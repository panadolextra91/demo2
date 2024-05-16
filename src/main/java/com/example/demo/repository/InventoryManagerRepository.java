package com.example.demo.repository;

import com.example.demo.model.InventoryManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryManagerRepository extends JpaRepository<InventoryManager, Long> {
    List<InventoryManager> findByUserNameContaining(String userName);
    List<InventoryManager> findByEmailContaining(String email);
}
