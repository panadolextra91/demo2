package com.example.demo.repository;

import com.example.demo.model.Suppliers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliersRepository extends JpaRepository<Suppliers, Long> {
    List<Suppliers> findBySuppliersNameContaining(String suppliersName);
}
