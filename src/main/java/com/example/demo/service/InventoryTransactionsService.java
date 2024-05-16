package com.example.demo.service;

import com.example.demo.model.InventoryTransactions;
import com.example.demo.repository.InventoryTransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class InventoryTransactionsService {

    @Autowired
    private InventoryTransactionsRepository inventoryTransactionsRepository;

    @Transactional
    public List<InventoryTransactions> findAllInventoryTransactions() {
        return inventoryTransactionsRepository.findAll();
    }

    @Transactional
    public InventoryTransactions findInventoryTransactionById(Long id) {
        return inventoryTransactionsRepository.findById(id).orElse(null);
    }

    @Transactional
    public InventoryTransactions saveInventoryTransaction(InventoryTransactions inventoryTransaction) {
        return inventoryTransactionsRepository.save(inventoryTransaction);
    }

    @Transactional
    public void deleteInventoryTransaction(Long id) {
        inventoryTransactionsRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllInventoryTransactions() {
        inventoryTransactionsRepository.deleteAll();
    }

    @Transactional
    public List<InventoryTransactions> findTransactionsByDateRange(Date startDate, Date endDate) {
        return inventoryTransactionsRepository.findByTransactionDateBetween(startDate, endDate);
    }

    @Transactional
    public List<InventoryTransactions> findTransactionsByProductId(Long productId) {
        return inventoryTransactionsRepository.findByProductProductId(productId);
    }

    @Transactional
    public void bulkDeleteInventoryTransactions(List<Long> transactionIds) {
        inventoryTransactionsRepository.deleteAllById(transactionIds);
    }
}
