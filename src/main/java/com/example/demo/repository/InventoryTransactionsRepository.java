package com.example.demo.repository;

import com.example.demo.model.InventoryTransactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface InventoryTransactionsRepository extends JpaRepository<InventoryTransactions, Long> {
    List<InventoryTransactions> findByTransactionDateBetween(Date startDate, Date endDate);
    List<InventoryTransactions> findByProductProductId(Long productId);
}
