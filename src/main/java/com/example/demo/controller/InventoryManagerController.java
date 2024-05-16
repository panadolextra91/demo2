package com.example.demo.controller;

import com.example.demo.model.InventoryManager;
import com.example.demo.service.InventoryManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-managers")
public class InventoryManagerController {

    @Autowired
    private InventoryManagerService inventoryManagerService;

    @GetMapping
    public ResponseEntity<List<InventoryManager>> getAllInventoryManagers() {
        return ResponseEntity.ok(inventoryManagerService.findAllInventoryManagers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryManager> getInventoryManagerById(@PathVariable Long id) {
        InventoryManager inventoryManager = inventoryManagerService.findInventoryManagerById(id);
        if (inventoryManager != null) {
            return ResponseEntity.ok(inventoryManager);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<InventoryManager> createInventoryManager(@RequestBody InventoryManager inventoryManager) {
        return ResponseEntity.ok(inventoryManagerService.saveInventoryManager(inventoryManager));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryManager> updateInventoryManager(@PathVariable Long id, @RequestBody InventoryManager inventoryManager) {
        InventoryManager existingInventoryManager = inventoryManagerService.findInventoryManagerById(id);
        if (existingInventoryManager != null) {
            inventoryManager.setImId(id);
            return ResponseEntity.ok(inventoryManagerService.saveInventoryManager(inventoryManager));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInventoryManager(@PathVariable Long id) {
        inventoryManagerService.deleteInventoryManager(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<InventoryManager>> searchInventoryManagersByName(@RequestParam String name) {
        return ResponseEntity.ok(inventoryManagerService.findInventoryManagersByName(name));
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<List<InventoryManager>> searchInventoryManagersByEmail(@RequestParam String email) {
        return ResponseEntity.ok(inventoryManagerService.findInventoryManagersByEmail(email));
    }

    @DeleteMapping("/bulkDelete")
    public ResponseEntity<Void> bulkDeleteInventoryManagers(@RequestBody List<Long> inventoryManagerIds) {
        inventoryManagerService.bulkDeleteInventoryManagers(inventoryManagerIds);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updateContact")
    public ResponseEntity<Void> updateInventoryManagerContact(@PathVariable Long id, @RequestParam String phone, @RequestParam String email) {
        inventoryManagerService.updateInventoryManagerContact(id, phone, email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/updatePassword")
    public ResponseEntity<Void> updateInventoryManagerPassword(@PathVariable Long id, @RequestParam String newPassword) {
        inventoryManagerService.updateInventoryManagerPassword(id, newPassword);
        return ResponseEntity.noContent().build();
    }
}
