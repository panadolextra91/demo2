package com.example.demo.controller;

import com.example.demo.model.InventoryTransactions;
import com.example.demo.service.InventoryTransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/inventory-transactions")
public class InventoryTransactionsController {

    @Autowired
    private InventoryTransactionsService inventoryTransactionsService;

    @GetMapping
    public ResponseEntity<List<InventoryTransactions>> getAllInventoryTransactions() {
        return ResponseEntity.ok(inventoryTransactionsService.findAllInventoryTransactions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryTransactions> getInventoryTransactionById(@PathVariable Long id) {
        InventoryTransactions inventoryTransaction = inventoryTransactionsService.findInventoryTransactionById(id);
        if (inventoryTransaction != null) {
            return ResponseEntity.ok(inventoryTransaction);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventoryTransactions> createInventoryTransaction(@RequestBody InventoryTransactions inventoryTransaction) {
        return ResponseEntity.ok(inventoryTransactionsService.saveInventoryTransaction(inventoryTransaction));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryTransactions> updateInventoryTransaction(@PathVariable Long id, @RequestBody InventoryTransactions inventoryTransaction) {
        InventoryTransactions existingInventoryTransaction = inventoryTransactionsService.findInventoryTransactionById(id);
        if (existingInventoryTransaction != null) {
            inventoryTransaction.setTransactionId(id);
            return ResponseEntity.ok(inventoryTransactionsService.saveInventoryTransaction(inventoryTransaction));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryTransaction(@PathVariable Long id) {
        inventoryTransactionsService.deleteInventoryTransaction(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByDate")
    public ResponseEntity<List<InventoryTransactions>> searchTransactionsByDate(
            @RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date startDate,
            @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'") Date endDate) {
        return ResponseEntity.ok(inventoryTransactionsService.findTransactionsByDateRange(startDate, endDate));
    }


    @GetMapping("/filterByProduct")
    public ResponseEntity<List<InventoryTransactions>> filterTransactionsByProduct(@RequestParam Long productId) {
        return ResponseEntity.ok(inventoryTransactionsService.findTransactionsByProductId(productId));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteInventoryTransactions(@RequestBody List<Long> transactionIds) {
        inventoryTransactionsService.bulkDeleteInventoryTransactions(transactionIds);
        return ResponseEntity.noContent().build();
    }
}
